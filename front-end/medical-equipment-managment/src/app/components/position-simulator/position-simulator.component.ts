import { Component, OnInit, ViewChild } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { environment } from 'src/app/env/environment';
import { MapComponent } from 'src/app/shared/maps/map/map.component';
import { Subscription, interval, take } from 'rxjs';

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

  private intervalSubscription: Subscription;

  private serverUrl = 'http://localhost:8084/socket'
  private stompClient: any;
  
  isLoaded: boolean = false;
  isCustomSocketOpened = false;
  selectedTime: number = 1;

  messages: string[] = [];

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
    }
  }

  startSimulation(){
    this.placeA(this.locationALat, this.locationALon);
    this.placeB(this.locationBLat, this.locationBLon);

    this.clearInterval();

    // Start the simulation with positions between A and B
    const positions = this.calculateIntermediaryPositions(5);
    this.intervalSubscription = interval(this.selectedTime * 1000)  
      .pipe(
        take(positions.length)  
      )
      .subscribe((index) => {
        const position = positions[index];
        this.carLat = this.locationALat;
        this.carLon = this.locationALon;
        this.placeCar(position.lat, position.lon);
      });
  }

  onTimeSelected(event: any): void {
    this.selectedTime = parseInt(event.target.value, 10);
    console.log('Selected Time:', this.selectedTime);
  }




  private placeA(lat: number, lon: number) {
    this.mapComponent.reverseSearchA(lat, lon).subscribe({
      error: (error) => {
        console.error('Error:', error);
      },
    });
  }

  private placeB(lat: number, lon: number) {
    this.mapComponent.reverseSearchB(lat, lon).subscribe({
      error: (error) => {
        console.error('Error:', error);
      },
    });
  }

  private placeCar(lat: number, lon: number) {
    this.mapComponent.reverseSearchCar(lat, lon).subscribe({
      error: (error) => {
        console.error('Error:', error);
      },
    });
  }

  private calculateIntermediaryPositions(count: number): { lat: number, lon: number }[] {
    const positions = [];
    for (let i = 0; i <= count; i++) {
      const t = i / count;
      const lat = (1 - t) * this.locationALat + t * this.locationBLat;
      const lon = (1 - t) * this.locationALon + t * this.locationBLon;
      positions.push({ lat, lon });
    }
    return positions;
  }

  private clearInterval() {
    if (this.intervalSubscription) {
      this.intervalSubscription.unsubscribe();
    }
  }

}
