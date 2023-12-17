import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserCreate } from '../model/user-create.model';
import { environment } from '../env/environment';
import { SystemAdmin } from '../model/sys-admin.model';
import { UserVerify } from '../model/user-verify.model';

@Injectable({
  providedIn: 'root',
})
export class SysAdminService {
  constructor(private http: HttpClient) {}

  registerUser(user: UserCreate): Observable<any> {
    return this.http.post<any>(environment.apiHost + 'sys/admins', user);
  }

  updateAdmin(admin: SystemAdmin): Observable<SystemAdmin> {
    return this.http.put<SystemAdmin>(environment.apiHost + 'sys/admins', admin);
  }

  updateAdminPassword(admin: SystemAdmin): Observable<SystemAdmin> {
    return this.http.put<SystemAdmin>(
      environment.apiHost + 'sys/admins/pass',
      admin
    );
  }

  getIsVerified(id: number): Observable<any> {
    return this.http.get<any>(environment.apiHost + 'sys/admins/isverified/' + id);
  }

  updateAdminVerified(admin: UserVerify): Observable<UserVerify> {
    return this.http.put<UserVerify>(
      environment.apiHost + 'sys/admins/verify',
      admin
    );
  }

  getAdmin(id: number): Observable<any> {
    return this.http.get<any>(environment.apiHost + 'sys/admins/' + id);
  }


}