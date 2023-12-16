import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../env/environment';
import { Equipment } from '../model/equipment';
import { EquipmentCreate } from '../model/equipment-create.model';
import { AppointmentCreate } from '../model/appointment-create.model';
import { Appointment } from '../model/appointment.model';

@Injectable({
  providedIn: 'root',
})
export class AppointmentService {
  constructor(private http: HttpClient) {}

  getEquipmentCompanies(id: number): Observable<any[]> {
    return this.http.get<any>(
      environment.apiHost + 'equipment/' + id + '/companies'
    );
  }

  addAppointment(appointment: AppointmentCreate): Observable<Appointment> {
    return this.http.post<Appointment>(
      environment.apiHost + 'appointments',
      appointment
    );
  }
}
