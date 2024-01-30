import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { timeout } from 'rxjs';
import { Appointment } from 'src/app/model/appointment.model';
import { CurrentUser } from 'src/app/model/current-user';
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
  
  @ViewChild(MatSort) appointmentSorter = new MatSort();

  ngOnInit(): void {
    this.LoadPastAppointments(this.authService.currentUser.getValue().id);
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.pastAppointments.sort = this.appointmentSorter;
    this.pastAppointments.sortingDataAccessor = (item: any, property: any) => {
      switch (property) {
        case 'date': return this.datePipe.transform(item.dateTime, "dd-MM-YYYY HH:mm");
        default: return item[property];
      }
    };
    }, 250);
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
