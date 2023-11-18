import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../env/environment';
import { Company } from '../model/company';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  constructor(private http: HttpClient) {}

  getCompanies(): Observable<any[]> {
    return this.http.get<any>(environment.apiHost + 'companies/all');
  }
  getCompany(id: number): Observable<any> {
    return this.http.get<any>(environment.apiHost + 'companies/' + id);
  }

  registerCompany(company: Company): Observable<any> {
    return this.http.post<any>(environment.apiHost + "companies", company);
  }

  getLastCompanyId(): Observable<any> {
    return this.http.get<any>(environment.apiHost + 'companies/last/created/id' );
  }


}
