import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../env/environment';
import { Equipment } from '../model/equipment';
import { EquipmentCreate } from '../model/equipment-create.model';

@Injectable({
  providedIn: 'root',
})
export class EquipmentService {
  constructor(private http: HttpClient) {}

  getAllEquipment(): Observable<any[]> {
    return this.http.get<any>(environment.apiHost + 'equipment');
  }

  getCompanyEquipment(id: number): Observable<any[]> {
    return this.http.get<any>(
      environment.apiHost + 'companies/' + id + '/equipment'
    );
  }

  getEquipmentCompanies(id: number): Observable<any[]> {
    return this.http.get<any>(
      environment.apiHost + 'equipment/' + id + '/companies'
    );
  }

  deleteEquipment(id: number): Observable<Equipment> {
    return this.http.delete<Equipment>(environment.apiHost + 'equipment/' + id);
  }

  updateEquipment(equipment: Equipment): Observable<Equipment> {
    return this.http.put<Equipment>(
      environment.apiHost + 'equipment',
      equipment
    );
  }

  addEquipment(equipment: EquipmentCreate): Observable<Equipment> {
    return this.http.post<Equipment>(
      environment.apiHost + 'equipment',
      equipment
    );
  }
}
