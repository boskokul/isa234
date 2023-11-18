import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserCreate } from '../model/user-create.model';
import { environment } from '../env/environment';
import { CompanyAdmin } from '../model/company-admin.model';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private http: HttpClient) {}

  registerUser(user: UserCreate): Observable<any> {
    return this.http.post<any>(environment.apiHost + 'admins', user);
  }
  getAdmin(id: number): Observable<any> {
    return this.http.get<any>(environment.apiHost + 'admins/' + id);
  }
  getAdmins(id: number): Observable<any> {
    return this.http.get<any>(
      environment.apiHost + 'companies/all/admins/' + id
    );
  }
  getCompanyForAdmin(id: number): Observable<any> {
    return this.http.get<any>(
      environment.apiHost + 'admins/admin/company/' + id
    );
  }
  updateAdmin(admin: CompanyAdmin): Observable<CompanyAdmin> {
    return this.http.put<CompanyAdmin>(environment.apiHost + 'admins', admin);
  }
}
