import { Component, OnInit, ViewChild } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { environment } from 'src/app/env/environment';
import { MapComponent } from 'src/app/shared/maps/map/map.component';
import { Subscription, interval, take } from 'rxjs';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-position-simulator',
  templateUrl: './position-simulator.component.html',
  styleUrls: ['./position-simulator.component.css']
})
export class PositionSimulatorComponent implements OnInit {
  @ViewChild(MapComponent) mapComponent: MapComponent;

  carLat: number;
  carLon: number;

  locationALat: number;
  locationALon: number;
  locationBLat: number;
  locationBLon: number;
  longitude: number;
  latitude: number;

  private serverUrl = 'http://localhost:8084/socket'
  private stompClient: any;
  
  isLoaded: boolean = false;
  isCustomSocketOpened = false;
  selectedTime: number = 3;

  messages: string[] = [];
  message: string[] = [];

  constructor(private service: AdminService){}
  ngOnInit(): void {
    this.initializeWebSocketConnection();
    this.locationALat = 45.24803507374321;
    this.locationALon = 19.83929253133869;
    this.locationBLat = 45.23984389699702;
    this.locationBLon = 19.843656098804175;
    this.carLat = this.locationALat;
    this.carLon = this.locationALon;
  }

  initializeWebSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function () {
      that.isLoaded = true;
      that.openGlobalSocket()
    });
  }
  
  openGlobalSocket() {
    if (this.isLoaded) {
      this.stompClient.subscribe("/socket-publisher", (message: { body: string; }) => {
        this.handleResult(message);
      });
    }
  }

  handleResult(message: { body: string; }) {
    if (message.body) {
      let messageResult: string = message.body;
      this.messages.push(messageResult);
      console.log(this.messages)
      this.message = message.body.split(',');
      this.placeCar(parseFloat(this.message[0]), parseFloat(this.message[1]))
    }
  }

  onTimeSelected(event: any): void {
    this.selectedTime = parseInt(event.target.value, 10);
    console.log('Selected Time:', this.selectedTime);
  }

  activateSimulator(){
    console.log(this.selectedTime.toString()+"!!!!!")
    this.service.activateSimulator(this.selectedTime.toString()).subscribe({
      error: (error) => {
        console.error('Error:', error);
      },
    })
  }

  private placeCar(lat: number, lon: number) {
    this.mapComponent.reverseSearchCar(lat, lon).subscribe({
      error: (error) => {
        console.error('Error:', error);
      },
    });
  }

}
