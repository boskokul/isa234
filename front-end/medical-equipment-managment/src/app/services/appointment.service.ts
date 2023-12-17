import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ExtraordinaryAppointment } from '../model/extraordinary-appointment.model';
import { environment } from '../env/environment';
import { Observable } from 'rxjs';
import { Appointment } from '../model/appointment.model';
import { ReservationCreate } from '../model/reservation-create';
import { ExtraordinaryAppointmentCreate } from '../model/extraordinary-appointment-create.model';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private http: HttpClient) { }

  GetAvailableTime(date: string, companyId: number): Observable<ExtraordinaryAppointment[]>{
    return this.http.get<ExtraordinaryAppointment[]>(environment.apiHost + 'appointments/extraordinaryAppointments?date=' + date + '&companyId=' + companyId);
  }

  CreateExtraordinaryAppointment(eoAppoitnment: ExtraordinaryAppointment, reservation: ReservationCreate, companyId: number): Observable<Appointment>{
    let createDto = {
      'extraordinaryAppointmentDTO': eoAppoitnment,
      'reservationCreateDTO': reservation,
      'companyId': companyId,
    }
    return this.http.post<Appointment>(environment.apiHost + 'appointments/selectExtraordinaryAppointment', createDto);
  }
}
