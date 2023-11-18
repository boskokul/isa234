import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { Company } from 'src/app/model/company';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-company-edit',
  templateUrl: './company-edit.component.html',
  styleUrls: ['./company-edit.component.css'],
})
export class CompanyEditComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public company: Company,
    public dialog: MatDialog,
    private companyService: CompanyService
  ) {
    console.log(this.company.name);
  }

  updateCompany() {
    this.companyService.updateCompany(this.company).subscribe({
      next: (result: Company) => {
        this.dialog.closeAll();
      },
    });
  }
}
