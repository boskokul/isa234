import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { CompaniesComponent } from './components/companies/companies.component';
import { CompanyProfileComponent } from './components/company-profile/company-profile.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { RegisterCompanyComponent } from './components/register-company/register-company.component';
import { EquipmentComponent } from './components/equipment/equipment.component';
import { VerificationComponent } from './components/verification/verification.component';
import { CompanyAdminProfileComponent } from './components/company-admin-profile/company-admin-profile.component';
import { LoginComponent } from './components/login/login.component';
import { CompanyCalendarComponent } from './components/company-calendar/company-calendar.component';
import { AuthGuardService } from './ActivateGuard/AuthGuardService';
import { SysAdminFormComponent } from './components/sys-admin-form/sys-admin-form.component';
import { ReservedEquipmentComponent } from './components/reserved-equipment/reserved-equipment.component';
import { CompanyAdminHomePageComponent } from './components/company-admin-home-page/company-admin-home-page.component';
import { UsersWithReservationsComponent } from './components/users-with-reservations/users-with-reservations.component';
import { UserHomepageComponent } from './components/user-homepage/user-homepage.component';
import { UserAppointmentHistoryComponent } from './components/user-appointment-history/user-appointment-history.component';
import { PositionSimulatorComponent } from './components/position-simulator/position-simulator.component';

const routes: Routes = [
  { path: '', redirectTo: 'register', pathMatch: 'full' },
  {
    path: 'companies',
    component: CompaniesComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'companies/:id',
    component: CompanyProfileComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'company/create',
    component: RegisterCompanyComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'equipment/list',
    component: EquipmentComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'userProfile',
    component: UserProfileComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'userHomepage',
    component: UserHomepageComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'userAppointmentHistory',
    component: UserAppointmentHistoryComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'verification',
    component: VerificationComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'companyAdminProfile',
    component: CompanyAdminProfileComponent,
    canActivate: [AuthGuardService],
  },
  { path: 'login', component: LoginComponent, canActivate: [AuthGuardService] },
  {
    path: 'calendar/:id',
    component: CompanyCalendarComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'sys-register',
    component: SysAdminFormComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'reserved-equipment',
    component: ReservedEquipmentComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'company-admin-homePage',
    component: CompanyAdminHomePageComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'users-with-reservations',
    component: UsersWithReservationsComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'position-simulator',
    component: PositionSimulatorComponent,
    canActivate: [AuthGuardService],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
