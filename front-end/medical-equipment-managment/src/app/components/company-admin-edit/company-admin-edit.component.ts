import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { CompanyAdmin } from 'src/app/model/company-admin.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-company-admin-edit',
  templateUrl: './company-admin-edit.component.html',
  styleUrls: ['./company-admin-edit.component.css'],
})
export class CompanyAdminEditComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public admin: CompanyAdmin,
    public dialog: MatDialog,
    private adminService: AdminService
  ) {
    console.log(this.admin.firstName);
  }

  updateAdmin() {
    this.adminService.updateAdmin(this.admin).subscribe({
      next: (result: CompanyAdmin) => {
        this.dialog.closeAll();
      },
    });
  }
}
