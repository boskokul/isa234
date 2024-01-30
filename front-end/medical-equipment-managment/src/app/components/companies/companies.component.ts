import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Company } from 'src/app/model/company';
import { CompanyAdmin } from 'src/app/model/company-admin.model';
import { CurrentUser } from 'src/app/model/current-user';
import { User } from 'src/app/model/user.model';
import { AdminService } from 'src/app/services/admin.service';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';
import { CompanyAdminPasswordChangeComponent } from '../company-admin-password-change/company-admin-password-change.component';
import { MatDialog } from '@angular/material/dialog';
import { UserVerify } from 'src/app/model/user-verify.model';
import { faL } from '@fortawesome/free-solid-svg-icons';
import { SysAdminService } from 'src/app/services/sys-admin.service';
import { SystemAdmin } from 'src/app/model/sys-admin.model';
import { SysPassChangeComponent } from '../sys-pass-change/sys-pass-change.component';

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
  subscription: Subscription;
  loggedInUserData: CurrentUser | undefined;
  displayedColumns: string[] = ['name', 'country', 'city', 'button'];

  admin: CompanyAdmin = {
    id: 0,
    firstName: '',
    lastName: '',
    password: '',
    email: '',
    city: '',
    country: '',
    phoneNumber: '',
    companyId: 0,
  };

  sysAdmin: SystemAdmin = {
    id: 0,
    firstName: '',
    lastName: '',
    password: '',
    email: '',
    city: '',
    country: '',
    phoneNumber: '',
  };

  adminVerify: UserVerify;

  @ViewChild(MatSort) sort: MatSort = new MatSort();

  constructor(
    private companyService: CompanyService,
    private router: Router,
    private authService: AuthService,
    private adminService: AdminService,
    private sysAdminService: SysAdminService,
    public dialog: MatDialog
  ) {
    this.dataSource = new MatTableDataSource<Company>();
    this.nameSearch = '';
    this.countrySearch = '';
    this.citySearch = '';
  }

  ngOnInit(): void {
    this.subscription = this.authService.currentUser.subscribe((user) => {
      this.loggedInUserData = user;
    });
    this.LoadCompanies();
    if (this.loggedInUserData?.role == 'ROLE_COMPANY_ADMIN') {
      setTimeout(() => {
        this.verifieIfNot();
      }, 100);
    }

    if (this.loggedInUserData?.role == 'ROLE_SYSTEM_ADMIN') {
      setTimeout(() => {
        this.verifieIfNotSys();
      }, 100);
    }
  }

  verifieIfNot() {
    this.adminService.getIsVerified(this.loggedInUserData?.id || 0).subscribe({
      next: (result: UserVerify) => {
        console.log(result);
        if (result.verified == false) {
          this.loadAdmin();
          setTimeout(() => {
            this.editPassword();
          }, 100);
        }
      },
    });
  }

  verifieIfNotSys() {
    this.sysAdminService.getIsVerified(this.loggedInUserData?.id || 0).subscribe({
      next: (result: UserVerify) => {
        console.log(result);
        if (result.verified == false) {
          this.loadSysAdmin();
          setTimeout(() => {
            this.editPasswordSys();
          }, 100);
        }
      },
    });
  }

  loadAdmin() {
    this.adminService.getAdmin(this.loggedInUserData!.id || 0).subscribe({
      next: (result: CompanyAdmin) => {
        this.admin = result;
      },
    });
  }

  loadSysAdmin() {
    this.sysAdminService.getAdmin(this.loggedInUserData!.id || 0).subscribe({
      next: (result: SystemAdmin) => {
        this.sysAdmin = result;
        console.log(this.sysAdmin)
      },
    });
  }

  editPassword() {
    const dialogRef = this.dialog.open(CompanyAdminPasswordChangeComponent, {
      data: this.admin,
      width: '400px',
      height: '450px',
      panelClass: 'custom-dialog',
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((item) => {});
  }

  editPasswordSys() {
    const dialogRef = this.dialog.open(SysPassChangeComponent, {
      data: this.sysAdmin,
      width: '400px',
      height: '450px',
      panelClass: 'custom-dialog',
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((item) => {});
  }
  ngOnDestroy() {
    this.subscription.unsubscribe();
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
        console.log(this.dataSource.data)
      },
    });
  }

  SearchCompanies() {
    this.companyService
      .searchCompanies(this.nameSearch, this.countrySearch, this.citySearch)
      .subscribe({
        next: (result: Company[]) => {
          this.dataSource.data = result;
        },
      });
  }

  ResetSearch() {
    this.LoadCompanies();
    this.nameSearch = '';
    this.countrySearch = '';
    this.citySearch = '';
  }

  FilterCompanies(key: string) {}
}
