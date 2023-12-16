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

const routes: Routes = [
  { path: '', redirectTo: 'register', pathMatch: 'full' },
  { path: 'companies', component: CompaniesComponent, canActivate: [AuthGuardService] },
  { path: 'companies/:id', component: CompanyProfileComponent, canActivate: [AuthGuardService] },
  { path: 'register', component: RegisterComponent, canActivate: [AuthGuardService] },
  { path: 'company/create', component: RegisterCompanyComponent, canActivate: [AuthGuardService] },
  { path: 'equipment/list', component: EquipmentComponent, canActivate: [AuthGuardService] },
  { path: 'userProfile', component: UserProfileComponent, canActivate: [AuthGuardService] },
  { path: 'verification', component: VerificationComponent, canActivate: [AuthGuardService] },
  { path: 'companyAdminProfile', component: CompanyAdminProfileComponent, canActivate: [AuthGuardService] },
  { path: 'login', component: LoginComponent, canActivate: [AuthGuardService] },
  { path: 'calendar', component: CompanyCalendarComponent, canActivate: [AuthGuardService] },
  { path: 'sys-register', component: SysAdminFormComponent, canActivate: [AuthGuardService]  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
