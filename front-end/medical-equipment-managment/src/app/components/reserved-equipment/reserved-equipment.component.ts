import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import jsQR from 'jsqr';
import { Subscription } from 'rxjs';
import { Appointment } from 'src/app/model/appointment.model';
import { CompanyAdmin } from 'src/app/model/company-admin.model';
import { CurrentUser } from 'src/app/model/current-user';
import { Reservation } from 'src/app/model/reservation.model';
import { AdminService } from 'src/app/services/admin.service';
import { AppointmentService } from 'src/app/services/appointment.service';
import { AuthService } from 'src/app/services/auth.service';
import { ReservationService } from 'src/app/services/reservation-service';

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
  displayedColumns: string[] = [
    'date and time',
    'Users name',
    'Users email',
    'Status',
    'cancelReservation',
  ];
  theStatus: string = 'NotFinalized';
  constructor(
    private authService: AuthService,
    private adminService: AdminService,
    private appointmentService: AppointmentService,
    private reservationService: ReservationService,
    public datePipe: DatePipe
  ) {
    this.reservedAppointments = new MatTableDataSource<Reservation>([]);
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
      },
    });
  }

  setReservationDone(reservation: Reservation) {
    this.reservationService.SetResDone(reservation).subscribe({
      next: (result: Reservation[]) => {
        this.loadReservations();
      },
    });
  }

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
          console.log('Found QR code', code);
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
}
