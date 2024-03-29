import { DatePipe } from '@angular/common';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Appointment } from 'src/app/model/appointment.model';
import { CurrentUser } from 'src/app/model/current-user';
import { UserUpdate } from 'src/app/model/user-update.model';
import { User } from 'src/app/model/user.model';
import { AppointmentService } from 'src/app/services/appointment.service';
import { AuthService } from 'src/app/services/auth.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { ReservationCancel } from '../../model/reservation-cancel';
import { ReservationService } from 'src/app/services/reservation-service';
import { AppointmentInfo } from 'src/app/model/appointment-info.model';
import { MatDialog } from '@angular/material/dialog';
import { QRcodeComponent } from '../qrcode/qrcode.component';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
  providers: [DatePipe],
})
export class UserProfileComponent implements OnInit, OnDestroy {
  user: User;
  userBackup: User;
  reservation: ReservationCancel = { appointmentId: -100, userId: -100 };
  editable: boolean;
  loggedInUserData: CurrentUser | undefined;
  subscription: Subscription;
  displayedColumns: string[] = [
    'date',
    'duration',
    'adminName',
    'reservationStatus',
    'cancelReservation',
    'reservationQrcode',
  ];
  reservedAppointments: any;
  @ViewChild(MatSort) appointmentSort = new MatSort();

  constructor(
    private service: UserServiceService,
    private authService: AuthService,
    private appointmentService: AppointmentService,
    private reservationService: ReservationService,
    public datePipe: DatePipe,
    public dialog: MatDialog,
  ) {
    this.reservedAppointments = new MatTableDataSource<AppointmentInfo>([]);
  }

  ngOnInit(): void {
    this.loadUser();
    this.LoadUserAppointments();
  }

  ngAfterViewInit() {
    this.reservedAppointments.sort = this.appointmentSort;
    this.reservedAppointments.sortingDataAccessor = (item: any, property: any) => {
      switch (property) {
        case 'date': return this.datePipe.transform(item.dateTime, "dd-MM-YYYY HH:mm");
        default: return item[property];
      }
    };
  }

  loadUser() {
    this.subscription = this.authService.currentUser.subscribe((user) => {
      this.loggedInUserData = user;
    });
    setTimeout(() => {
      this.service.getLoggedUser(this.loggedInUserData!.id).subscribe({
        next: (result: User) => {
          this.userBackup = result;
          this.user = Object.assign({}, this.userBackup);
        },
        error: (err: any) => {
          console.log(err);
        },
      });
    }, 100);
  }
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
  updateUser() {
    let userUpdate: UserUpdate = {
      id: this.user.id,
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      city: this.user.city,
      country: this.user.country,
      phoneNumber: this.user.phoneNumber,
      profession: this.user.profession,
      companyInformation: this.user.companyInformation,
    };
    this.service.updateUser(userUpdate).subscribe({
      next: (result: User) => {
        this.userBackup = result;
        this.user = Object.assign({}, this.userBackup);
      },
      error: (err: any) => {
        console.log(err);
      },
    });
  }

  toggleEdit(): void {
    if (this.editable) {
      this.user = Object.assign({}, this.userBackup);
    }
    this.editable = !this.editable;
  }
  LoadUserAppointments(): void {
    this.appointmentService
      .getAppointmentsForUser(this.loggedInUserData!.id)
      .subscribe({
        next: (result: Appointment[]) => {
          this.reservedAppointments.data = result;
        },
      });
  }
  cancelAppointment(appointmentId: any) {
    if(confirm("Are you sure you want to cancel this reservation")){
    this.reservation.userId = this.user.id;
    this.reservation.appointmentId = appointmentId;
    this.reservationService.CancelReservation(this.reservation).subscribe({
      next: (result: any) => {
        this.reservedAppointments.data = this.reservedAppointments.data.filter(
          (a: any) => a.id != appointmentId
        );
        this.user.penalPoints = result
        alert('Appointment successfully cancelled but you got penal points');
      },
    });
  }
  }
  showQrcode(qrcontent: string){
    console.log(qrcontent);
    const dialogRef = this.dialog.open(QRcodeComponent, {
      data: qrcontent,
      width: '300px',
      height: '350px',
      panelClass: 'custom-dialog',
    });
    dialogRef.afterClosed().subscribe((item) => {});
  }
}
