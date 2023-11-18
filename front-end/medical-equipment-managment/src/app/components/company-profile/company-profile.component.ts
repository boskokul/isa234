import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Company } from 'src/app/model/company';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css'],
})
export class CompanyProfileComponent implements OnInit {
  name: string;
  description: string;
  country: string;
  city: string;
  averageGrade: number;
  constructor(
    private companyService: CompanyService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.companyService.getCompany(this.route.snapshot.params['id']).subscribe({
      next: (result: Company) => {
        this.name = result.name;
        this.description = result.description;
        this.country = result.country;
        this.city = result.city;
        this.averageGrade = result.averageGrade;
      },
    });
  }
}
