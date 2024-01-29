import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RegisterComponent } from './components/register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CompaniesComponent } from './components/companies/companies.component';
import { CompanyProfileComponent } from './components/company-profile/company-profile.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { RegisterCompanyComponent } from './components/register-company/register-company.component';
import { EquipmentComponent } from './components/equipment/equipment.component';
import { MatCardModule } from '@angular/material/card';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { CompanyListPopupComponent } from './components/company-list-popup/company-list-popup.component';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { CompanyAdminProfileComponent } from './components/company-admin-profile/company-admin-profile.component';
import { CompanyAdminEditComponent } from './components/company-admin-edit/company-admin-edit.component';
import { CompanyEditComponent } from './components/company-edit/company-edit.component';
import { OtherCompanyAdminsComponent } from './components/other-company-admins/other-company-admins.component';
import { CompanyAdminPasswordChangeComponent } from './components/company-admin-password-change/company-admin-password-change.component';
import { MatTableModule } from '@angular/material/table'; 
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MatSortModule } from '@angular/material/sort';
import { VerificationComponent } from './components/verification/verification.component';
import { EquipmentEditComponent } from './components/equipment-edit/equipment-edit.component';
import { EquipmentCreateComponent } from './components/equipment-create/equipment-create.component';
import { LoginComponent } from './components/login/login.component';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { AdminService } from './services/admin.service';
import { CompanyService } from './services/company.service';
import { EquipmentService } from './services/equipment.service';
import { UserServiceService } from './services/user-service.service';
import { EquipmentAppointmentComponent } from './components/equipment-appointment/equipment-appointment.component';
import { CompanyCalendarComponent } from './components/company-calendar/company-calendar.component';
import { FullCalendarModule } from '@fullcalendar/angular';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { AuthGuardService } from './ActivateGuard/AuthGuardService';
import { ReservationService } from './services/reservation-service';
import { SysAdminFormComponent } from './components/sys-admin-form/sys-admin-form.component';
import { SharedModule } from "./shared/maps/shared.module";
import { AppointmentService } from './services/appointment.service';
import { CompanyMapComponent } from './components/company-map/company-map.component';
import { SysPassChangeComponent } from './components/sys-pass-change/sys-pass-change.component';
import { ReservedEquipmentComponent } from './components/reserved-equipment/reserved-equipment.component';
import { CompanyAdminHomePageComponent } from './components/company-admin-home-page/company-admin-home-page.component';
import { UsersWithReservationsComponent } from './components/users-with-reservations/users-with-reservations.component';
import { UserHomepageComponent } from './components/user-homepage/user-homepage.component';
import { UserAppointmentHistoryComponent } from './components/user-appointment-history/user-appointment-history.component';
import { QRcodeComponent } from './components/qrcode/qrcode.component';
import { QRCodeModule } from 'angularx-qrcode';
import { DatePipe } from '@angular/common';
import { PositionSimulatorComponent } from './components/position-simulator/position-simulator.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RegisterComponent,
    CompaniesComponent,
    CompanyProfileComponent,
    UserProfileComponent,
    RegisterCompanyComponent,
    EquipmentComponent,
    CompanyListPopupComponent,
    VerificationComponent,
    CompanyAdminProfileComponent,
    CompanyAdminEditComponent,
    CompanyEditComponent,
    OtherCompanyAdminsComponent,
    CompanyAdminPasswordChangeComponent,
    EquipmentEditComponent,
    EquipmentCreateComponent,
    LoginComponent,
    EquipmentAppointmentComponent,
    CompanyCalendarComponent,
    SysAdminFormComponent,
    CompanyMapComponent,
    SysPassChangeComponent,
    ReservedEquipmentComponent,
    CompanyAdminHomePageComponent,
    UsersWithReservationsComponent,
    UserHomepageComponent,
    UserAppointmentHistoryComponent,
    QRcodeComponent,
    PositionSimulatorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    MatOptionModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDialogModule,
    MatTableModule,
    FontAwesomeModule,
    MatSortModule,
    FullCalendarModule,
    NgxMaterialTimepickerModule,
    SharedModule,
    QRCodeModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    AdminService,
    CompanyService,
    EquipmentService,
    UserServiceService,
    ReservationService,
    AuthGuardService,
    AppointmentService,
    DatePipe,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
