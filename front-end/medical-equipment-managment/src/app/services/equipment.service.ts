import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../env/environment';


@Injectable({
  providedIn: 'root',
})
export class EquipmentService {
  constructor(private http: HttpClient) {}

  getAllEquipment(): Observable<any[]> {
    return this.http.get<any>(environment.apiHost + 'equipment');
  }

  getEquipmentCompanies(id: number): Observable<any[]> {
    return this.http.get<any>(environment.apiHost + 'equipment/' + id + "/companies");
  }

}