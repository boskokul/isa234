import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserCreate } from '../model/user-create.model';
import { environment } from '../env/environment';
import { CompanyAdmin } from '../model/company-admin.model';
import { SystemAdmin } from '../model/sys-admin.model';

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
}