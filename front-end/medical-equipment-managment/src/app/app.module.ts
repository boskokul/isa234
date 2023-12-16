import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
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
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
