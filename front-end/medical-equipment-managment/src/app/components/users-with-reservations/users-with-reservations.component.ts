import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Company } from 'src/app/model/company';
import { CompanyAdmin } from 'src/app/model/company-admin.model';
import { CurrentUser } from 'src/app/model/current-user';
import { ReservationCreate } from 'src/app/model/reservation-create';
import { User } from 'src/app/model/user.model';
import { AdminService } from 'src/app/services/admin.service';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';
import { ReservationService } from 'src/app/services/reservation-service';

@Component({
  selector: 'app-users-with-reservations',
  templateUrl: './users-with-reservations.component.html',
  styleUrls: ['./users-with-reservations.component.css'],
})
export class UsersWithReservationsComponent implements OnInit {
  user: CurrentUser | undefined;
  company: Company = {
    id: 0,
    averageGrade: 0,
    city: '',
    country: '',
    description: '',
    name: '',
    startTime: { hours: 10, minutes: 10 },
    endTime: { hours: 10, minutes: 10 },
    lat: 0,
    lon: 0,
    street: '',
    houseNumber: 0,
  };
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
  users: User[] = [];
  constructor(
    private companyService: CompanyService,
    private adminService: AdminService,
    private authService: AuthService,
    private reservationService: ReservationService
  ) {}

  ngOnInit(): void {
    this.subscription = this.authService.currentUser.subscribe((user) => {
      this.user = user;
    });
    setTimeout(() => {
      this.loadAdmin();
    }, 100);
  }

  loadAdmin() {
    this.adminService.getAdmin(this.user!.id).subscribe({
      next: (result: CompanyAdmin) => {
        this.admin = result;
        this.loadCompany();
      },
    });
  }

  loadCompany() {
    this.adminService.getCompanyForAdmin(this.admin.id).subscribe({
      next: (result: Company) => {
        this.company = result;
        this.loadReservedUsers();
      },
    });
  }

  loadReservedUsers() {
    this.reservationService
      .getAllUsersWithResInCompany(this.company.id)
      .subscribe({
        next: (result: User[]) => {
          this.users = result;
        },
      });
  }
}
