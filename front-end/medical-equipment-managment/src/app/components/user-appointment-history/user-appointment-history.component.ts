import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CurrentUser } from 'src/app/model/current-user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-user-appointment-history',
  templateUrl: './user-appointment-history.component.html',
  styleUrls: ['./user-appointment-history.component.css']
})
export class UserAppointmentHistoryComponent implements OnInit {
  constructor(private authService: AuthService, private router: Router){}
  user: CurrentUser | undefined
  ngOnInit(): void {
    this.authService.currentUser.subscribe(user => {
      this.user = user;
      if(this.user.email == ''){
        this.user = undefined
      }
    });
    //Load past appointments
  }
}
