import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { UserCreate } from '../model/user-create.model';
import { environment } from '../env/environment';
import { User } from '../model/user.model';
import { UserUpdate } from '../model/user-update.model';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  
  constructor(private http: HttpClient) { }

  registerUser(user: UserCreate): Observable<any> {
    return this.http.post<any>(environment.apiHost + "registration", user);
  }

  getLoggedUser(id: number): Observable<User> {
    return this.http.get<User>(environment.apiHost + "users/" + id);
  }

  updateUser(userUpdate: UserUpdate): Observable<User> {
    return this.http.put<User>(environment.apiHost + "users", userUpdate)
  }
  
}
