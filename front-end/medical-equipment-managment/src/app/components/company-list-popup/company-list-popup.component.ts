import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Company } from 'src/app/model/company';

@Component({
  selector: 'app-company-list-popup',
  templateUrl: './company-list-popup.component.html',
  styleUrls: ['./company-list-popup.component.css']
})
export class CompanyListPopupComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public itemCompanies: Company[]) {
    console.log(this.itemCompanies);
  }
}
