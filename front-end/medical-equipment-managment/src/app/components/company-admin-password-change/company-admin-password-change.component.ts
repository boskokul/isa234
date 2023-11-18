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
  password: string;
  passwordNew: string;
  passwordRepeat: string;
  constructor(
    @Inject(MAT_DIALOG_DATA) public admin: CompanyAdmin,
    public dialog: MatDialog,
    private adminService: AdminService
  ) {
    console.log(this.admin.firstName);
  }

  updateAdmin() {
    if (this.password != this.admin.password) return;
    if (this.passwordNew != this.passwordRepeat) return;
  }
}
