import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { CompaniesComponent } from './components/companies/companies.component';
import { CompanyProfileComponent } from './components/company-profile/company-profile.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';

const routes: Routes = [
  { path: '', redirectTo: 'register', pathMatch: 'full' },
  { path: 'companies', component: CompaniesComponent },
  { path: 'companies/:id', component: CompanyProfileComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'userProfile', component: UserProfileComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
