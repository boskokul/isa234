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

const routes: Routes = [
  { path: '', redirectTo: 'register', pathMatch: 'full' },
  { path: 'companies', component: CompaniesComponent },
  { path: 'companies/:id', component: CompanyProfileComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'company/create', component: RegisterCompanyComponent },
  { path: 'equipment/list', component: EquipmentComponent },
  { path: 'userProfile', component: UserProfileComponent },
  { path: 'verification', component: VerificationComponent },
  { path: 'companyAdminProfile', component: CompanyAdminProfileComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
