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
    email: "",
    role: ""
  })
  constructor(
    private http: HttpClient,
    private router: Router,
    private userService: UserServiceService
    ) {}
    private access_token = null;
    login(user:any) {
      const loginHeaders = new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      });
      const body = {
        'email': user.email,
        'password': user.password
      };
      let options = { headers: loginHeaders };
      return this.http.post<any>(environment.apiHost + "registration/login", JSON.stringify(body),
        options
      )
      .pipe(map((res) => {
        console.log('Login success');
        this.access_token = res.accessToken;
        localStorage.setItem("jwt", res.accessToken)
        this.setUser()
        this.router.navigate(['/companies'])
      }));
    }
    tokenIsPresent() {
      return this.access_token != undefined && this.access_token != null;
    }
    getToken() {
      return this.access_token;
    }
    logout() {
      localStorage.removeItem("jwt");
      this.access_token = null;
      this.router.navigate(['/login']);
      this.currentUser.next({ email: "", role: "" });
    }
    setUser(){
      let token = localStorage.getItem("jwt")
      const jwtHelperService = new JwtHelperService();
      const user: CurrentUser = {
        email: jwtHelperService.decodeToken(token!).email,
        role: jwtHelperService.decodeToken(token!).role
      }
      console.log(user)
      this.currentUser.next(user)
    }
}
