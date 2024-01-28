import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Appointment } from 'src/app/model/appointment.model';
import { CurrentUser } from 'src/app/model/current-user';
import { User } from 'src/app/model/user.model';
import { AppointmentService } from 'src/app/services/appointment.service';
import { AuthService } from 'src/app/services/auth.service';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-user-appointment-history',
  templateUrl: './user-appointment-history.component.html',
  styleUrls: ['./user-appointment-history.component.css']
})
export class UserAppointmentHistoryComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private router: Router,
    private appointmentService: AppointmentService,
    public datePipe: DatePipe,
    private userService: UserServiceService,
  ) {
    this.pastAppointments = new MatTableDataSource<Appointment>([]);
  }
  user: CurrentUser | undefined;
  pastAppointments: any;
  displayedColumns: string[] = [
    'date',
    'duration',
    'adminName',
  ];
  subscription: Subscription;

  ngOnInit(): void {
    this.LoadPastAppointments(this.authService.currentUser.getValue().id);
  }
  
  LoadPastAppointments(userId: number): void{
    this.appointmentService
      .getAllPastAppointments(userId)
      .subscribe({
        next: (result: Appointment[]) => {
          this.pastAppointments.data = result;
        },
      });
  }
}
