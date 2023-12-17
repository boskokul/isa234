import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { CompanyAdmin } from 'src/app/model/company-admin.model';
import { SystemAdmin } from 'src/app/model/sys-admin.model';
import { AdminService } from 'src/app/services/admin.service';
import { SysAdminService } from 'src/app/services/sys-admin.service';

@Component({
  selector: 'app-sys-pass-change',
  templateUrl: './sys-pass-change.component.html',
  styleUrls: ['./sys-pass-change.component.css'],
})
export class SysPassChangeComponent {
  passwordNew: string = '';
  passwordRepeat: string = '';
  constructor(
    @Inject(MAT_DIALOG_DATA) public admin: SystemAdmin,
    public dialog: MatDialog,
    private adminService: SysAdminService
  ) {}

  updateAdmin() {
    console.log(this.admin);
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
    let adminForUpdate: SystemAdmin = {
      id: this.admin.id,
      firstName: this.admin.firstName,
      lastName: this.admin.lastName,
      password: this.passwordNew,
      email: this.admin.email,
      city: this.admin.city,
      country: this.admin.country,
      phoneNumber: this.admin.phoneNumber,
    };
    console.log(adminForUpdate);
    this.adminService.updateAdminPassword(adminForUpdate).subscribe({
      next: (result: SystemAdmin) => {
        this.dialog.closeAll();
      },
    });
  }
}
