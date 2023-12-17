import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { CompanyAdmin } from 'src/app/model/company-admin.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-company-admin-password-change',
  templateUrl: './company-admin-password-change.component.html',
  styleUrls: ['./company-admin-password-change.component.css'],
})
export class CompanyAdminPasswordChangeComponent {
  passwordNew: string = '';
  passwordRepeat: string = '';
  constructor(
    @Inject(MAT_DIALOG_DATA) public admin: CompanyAdmin,
    public dialog: MatDialog,
    private adminService: AdminService
  ) {}

  updateAdmin() {
    /*if (this.password != this.admin.password) {
      alert('Provide your current password!');
      return;
    }*/
    if (
      this.passwordNew != this.passwordRepeat ||
      this.passwordNew.length == 0
    ) {
      alert('Passwords have to match and can not be empty!');
      return;
    }
    let adminForUpdate: CompanyAdmin = {
      id: this.admin.id,
      firstName: this.admin.firstName,
      lastName: this.admin.lastName,
      password: this.passwordNew,
      email: this.admin.email,
      city: this.admin.city,
      country: this.admin.country,
      phoneNumber: this.admin.phoneNumber,
      companyId: this.admin.companyId,
    };
    this.adminService.updateAdminPassword(adminForUpdate).subscribe({
      next: (result: CompanyAdmin) => {
        this.dialog.closeAll();
      },
    });
  }
}
