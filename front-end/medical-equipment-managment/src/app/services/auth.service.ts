import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserServiceService } from './user-service.service';
import { environment } from '../env/environment';
import { BehaviorSubject, map } from 'rxjs';
import { CurrentUser } from '../model/current-user';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  currentUser = new BehaviorSubject<CurrentUser>({
    email: '',
    role: '',
    id: 0,
  });
  constructor(
    private http: HttpClient,
    private router: Router,
    private userService: UserServiceService
  ) {
    this.setUser();
  }
  private access_token = null;
  login(user: any) {
    const loginHeaders = new HttpHeaders({
      Accept: 'application/json',
      'Content-Type': 'application/json',
    });
    const body = {
      email: user.email,
      password: user.password,
    };
    let options = { headers: loginHeaders };
    return this.http
      .post<any>(
        environment.apiHost + 'registration/login',
        JSON.stringify(body),
        options
      )
      .pipe(
        map((res) => {
          console.log('Login success');
          this.access_token = res.accessToken;
          localStorage.setItem('jwt', res.accessToken);
          this.setUser();
        })
      );
  }
  tokenIsPresent() {
    let token = localStorage.getItem('jwt');
    if (token == null) {
      return false;
    } else {
      return true;
    }
  }
  getToken() {
    return localStorage.getItem('jwt');
  }
  logout() {
    localStorage.removeItem('jwt');
    this.access_token = null;
    this.router.navigate(['/login']);
    this.currentUser.next({ email: '', role: '', id: 0 });
  }
  setUser() {
    let token = localStorage.getItem('jwt');
    if (token == null) {
      return;
    }
    const jwtHelperService = new JwtHelperService();
    const user: CurrentUser = {
      email: jwtHelperService.decodeToken(token!).email,
      role: jwtHelperService.decodeToken(token!).role,
      id: jwtHelperService.decodeToken(token!).id,
    };
    //console.log(user);
    this.currentUser.next(user);
    if (user.role == 'ROLE_COMPANY_ADMIN') {
      this.router.navigate(['/company-admin-homePage']);
    } else {
      this.router.navigate(['/companies']);
    }
    console.log(user);
  }
}
