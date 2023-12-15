import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Company } from 'src/app/model/company';
import { CurrentUser } from 'src/app/model/current-user';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css'],
})
export class CompaniesComponent implements OnInit, OnDestroy {
  dataSource: any;
  nameSearch: string;
  countrySearch: string;
  citySearch: string;
  subscription: Subscription
  loggedInUserData: CurrentUser | undefined
  displayedColumns: string[] = ['name', 'country', 'city', 'button'];

  @ViewChild(MatSort) sort: MatSort;

  constructor(private companyService: CompanyService, private router: Router, private authService: AuthService) {
    this.dataSource = new MatTableDataSource<Company>();
    this.nameSearch = '';
    this.countrySearch = '';
    this.citySearch = '';
  }

  ngOnInit(): void {
    this.subscription = this.authService.currentUser.subscribe(user => {
      this.loggedInUserData = user;
    });
    this.LoadCompanies();
  }
  ngOnDestroy() {
    this.subscription.unsubscribe()
  }
  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  ViewProfile(id: number) {
    this.router.navigate(['/companies/' + id]);
    console.log(id);
  }

  LoadCompanies() {
    this.companyService.getCompanies().subscribe({
      next: (result: Company[]) => {
        this.dataSource.data = result;
      },
    });
  }

  SearchCompanies() {
    this.companyService.searchCompanies(this.nameSearch, this.countrySearch, this.citySearch).subscribe({
      next: (result: Company[]) => {
        this.dataSource.data = result;
      }
    })
  }

  ResetSearch() {
    this.LoadCompanies();
    this.nameSearch = '';
    this.countrySearch = '';
    this.citySearch = '';
  }

  FilterCompanies(key: string) {
  }
}
