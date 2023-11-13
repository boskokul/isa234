import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../env/environment';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  constructor(private http: HttpClient) {}

  getCompanies(): Observable<any[]> {
    return this.http.get<any>(environment.apiHost + 'companies');
  }
  getCompany(id: number): Observable<any> {
    return this.http.get<any>(environment.apiHost + 'companies/' + id);
  }
}
