import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { CompanyAdmin } from 'src/app/model/company-admin.model';
import { AdminService } from 'src/app/services/admin.service';
export interface ModalData {
  adminId: number;
  companyId: number;
}
@Component({
  selector: 'app-other-company-admins',
  templateUrl: './other-company-admins.component.html',
  styleUrls: ['./other-company-admins.component.css'],
})
export class OtherCompanyAdminsComponent implements OnInit {
  admins: CompanyAdmin[] = [];
  adminId: number;
  companyId: number;
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: ModalData,
    public dialog: MatDialog,
    private adminService: AdminService
  ) {}
  ngOnInit(): void {
    this.adminId = this.data.adminId;
    this.companyId = this.data.companyId;
    this.adminService.getAdmins(this.companyId).subscribe({
      next: (result: CompanyAdmin[]) => {
        result.forEach((item, index) => {
          if (item.id != this.adminId) {
            this.admins.push(item);
            console.log(item.id, this.adminId, this.companyId);
          }
        });
      },
    });
  }
}
