import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import jsQR from 'jsqr';
import { DateTime } from 'luxon';
import { Subscription } from 'rxjs';
import { Appointment } from 'src/app/model/appointment.model';
import { CompanyAdmin } from 'src/app/model/company-admin.model';
import { CurrentUser } from 'src/app/model/current-user';
import { Equipment } from 'src/app/model/equipment';
import { ResEquipment } from 'src/app/model/resEquipment.model';
import { Reservation } from 'src/app/model/reservation.model';
import { AdminService } from 'src/app/services/admin.service';
import { AppointmentService } from 'src/app/services/appointment.service';
import { AuthService } from 'src/app/services/auth.service';
import { ReservationService } from 'src/app/services/reservation-service';
import { OtherCompanyAdminsComponent } from '../other-company-admins/other-company-admins.component';
import { ResEquipmentDetailsComponent } from '../res-equipment-details/res-equipment-details.component';

@Component({
  selector: 'app-reserved-equipment',
  templateUrl: './reserved-equipment.component.html',
  styleUrls: ['./reserved-equipment.component.css'],
  providers: [DatePipe],
})
export class ReservedEquipmentComponent implements OnInit {
  admin: CompanyAdmin = {
    id: 0,
    firstName: '',
    lastName: '',
    password: '',
    email: '',
    city: '',
    country: '',
    phoneNumber: '',
    companyId: 0,
  };
  subscription: Subscription;
  user: CurrentUser | undefined;
  public decodedData: string | null = null;
  public imageDataUrl: string | null = null;
  reservedAppointments: any;
  pastCanceledAppointments: any;
  displayedColumns: string[] = [
    'date and time',
    'Users name',
    'Users email',
    'Status',
    'cancelReservation',
    'showEquipment',
  ];
  displayedColumnsRC: string[] = [
    'date and time',
    'Users name',
    'Users email',
    'Status',
  ];
  theStatus: string = 'NotFinalized';
  dataQR: number;
  constructor(
    private authService: AuthService,
    private adminService: AdminService,
    private appointmentService: AppointmentService,
    private reservationService: ReservationService,
    public datePipe: DatePipe,
    public dialog: MatDialog
  ) {
    this.reservedAppointments = new MatTableDataSource<Reservation>([]);
    this.pastCanceledAppointments = new MatTableDataSource<Reservation>([]);
  }

  ngOnInit(): void {
    this.subscription = this.authService.currentUser.subscribe((user) => {
      this.user = user;
      console.log(this.user.id);
    });
    setTimeout(() => {
      this.loadAdmin();
    }, 100);
  }

  loadAdmin() {
    this.adminService.getAdmin(this.user!.id).subscribe({
      next: (result: CompanyAdmin) => {
        this.admin = result;
        this.loadReservations();
      },
    });
  }

  loadReservations() {
    this.reservationService.getFutureReservations(this.admin.id).subscribe({
      next: (result: Reservation[]) => {
        this.reservedAppointments.data = result;
        this.loadPastOrCanceledReservations();
      },
    });
  }

  loadPastOrCanceledReservations() {
    this.reservationService
      .getPastOrCanceledReservations(this.admin.id)
      .subscribe({
        next: (result: Reservation[]) => {
          this.pastCanceledAppointments.data = result;
        },
      });
  }

  setReservationDone(reservation: Reservation) {
    let currentDT = DateTime.now().toISO();
    let currentTime = currentDT.toLocaleString().split('T')[1];
    let currentHour = currentTime.split(':')[0];
    let currentMinutes = currentTime.split(':')[1];
    let currentDate = currentDT.toLocaleString().split('T')[0];
    let currentYear = currentDate.split('-')[0];
    let currentMonth = currentDate.split('-')[1];
    let currentDay = currentDate.split('-')[2];
    console.log(
      currentDT,
      currentDate,
      currentHour,
      currentMinutes,
      currentYear,
      currentMonth,
      currentDay
    );
    let resTime = reservation.dateTime.toLocaleString().split('T')[1];
    let resHour = resTime.split(':')[0];
    let resMinutes = resTime.split(':')[1];
    let resDate = reservation.dateTime.toLocaleString().split('T')[0];
    let resYear = resDate.split('-')[0];
    let resMonth = resDate.split('-')[1];
    let resDay = resDate.split('-')[2];
    console.log(
      reservation.dateTime,
      resDate,
      resHour,
      resMinutes,
      resYear,
      resMonth,
      resDay
    );
    if (currentYear == resYear) {
      if (currentMonth == resMonth) {
        if (currentDay == resDay)
          if (currentHour == resHour) {
            if (currentMinutes > resMinutes) {
              alert('This one is past due, can not be picked up anymore!');
              return;
            }
          }
      }
    }
    if (currentYear == resYear) {
      if (currentMonth == resMonth) {
        if (currentDay == resDay)
          if (currentHour > resHour) {
            alert('This one is past due, can not be picked up anymore!');
            return;
          }
      }
    }
    this.reservationService.SetResDone(reservation).subscribe({
      next: (result: Reservation[]) => {
        this.loadReservations();
      },
    });
  }

  showEquipment(reservation: Reservation) {
    this.reservationService.getEquipment(reservation.id).subscribe({
      next: (result: ResEquipment[]) => {
        //console.log(result);
        const dialogRef = this.dialog.open(ResEquipmentDetailsComponent, {
          data: {
            equipment: result,
          },
          width: '650px',
          height: '450px',
          panelClass: 'custom-dialog',
        });
      },
    });
  }
  otherAdmins() {}

  onFileSelected(event: any): void {
    const input = event.target;

    if (input.files && input.files[0]) {
      const reader = new FileReader();

      reader.onload = (e) => {
        const dataUrl = e.target?.result as string;
        this.imageDataUrl = dataUrl;
        console.log(this.imageDataUrl);
      };

      reader.readAsDataURL(input.files[0]);
    }
  }

  decodeImage(): void {
    if (this.imageDataUrl) {
      const img = new Image();
      img.onload = () => {
        const canvas = document.createElement('canvas');
        canvas.width = img.width;
        canvas.height = img.height;
        const context = canvas.getContext('2d');
        context!.drawImage(img, 0, 0);

        const imageData = context!.getImageData(0, 0, img.width, img.height);
        console.log(imageData);
        const code = jsQR(imageData.data, imageData.width, imageData.height);

        if (code) {
          this.decodedData = code.data;
          console.log('Decoded id is ' + this.decodedData);
          console.log('Found QR code', code);

          this.dataQR = parseInt(this.decodedData, 10);
          const foundReservation = this.findById(this.dataQR);
          if (foundReservation) {
            alert('Reservation scanned!!!');
            this.setReservationDone(foundReservation);
          } else {
            alert('Reservation is expired or does not exist!');
          }
        } else {
          console.log("No QR code found or couldn't decode.");
        }
      };

      img.onerror = (error) => {
        console.error('Error loading image:', error);
      };

      img.src = this.imageDataUrl;
    }
  }

  findById(id: number): Reservation | undefined {
    const foundReservation = this.reservedAppointments.data.find(
      (reservation: Reservation) => reservation.id === id
    );
    return foundReservation;
  }
}
