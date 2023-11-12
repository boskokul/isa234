import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserCreate } from '../model/user-create.model';
import { environment } from '../env/environment';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }

  registerUser(user: UserCreate): Observable<any> {
    return this.http.post<any>(environment.apiHost + "users", user);
  }
}
