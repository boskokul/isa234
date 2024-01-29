import { Component, OnInit } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { environment } from 'src/app/env/environment';

@Component({
  selector: 'app-position-simulator',
  templateUrl: './position-simulator.component.html',
  styleUrls: ['./position-simulator.component.css']
})
export class PositionSimulatorComponent implements OnInit {
  private serverUrl = 'http://localhost:8084/socket'
  private stompClient: any;
  
  isLoaded: boolean = false;
  isCustomSocketOpened = false;

  messages: string[] = [];
  message: string = "";
  ngOnInit(): void {
    this.initializeWebSocketConnection()
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
      this.message = message.body;
    }
  }

}
