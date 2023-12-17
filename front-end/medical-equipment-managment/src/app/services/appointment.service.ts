import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ExtraordinaryAppointment } from '../model/extraordinary-appointment.model';
import { environment } from '../env/environment';
import { Observable } from 'rxjs';
import { Appointment } from '../model/appointment.model';
import { ReservationCreate } from '../model/reservation-create';
import { Equipment } from '../model/equipment';
import { EquipmentCreate } from '../model/equipment-create.model';
import { AppointmentCreate } from '../model/appointment-create.model';
import { ResApp } from '../model/resApp.model';

@Injectable({
  providedIn: 'root',
})
export class AppointmentService {
  constructor(private http: HttpClient) {}

  GetAvailableTime(
    date: string,
    companyId: number
  ): Observable<ExtraordinaryAppointment[]> {
    return this.http.get<ExtraordinaryAppointment[]>(
      environment.apiHost +
        'appointments/extraordinaryAppointments?date=' +
        date +
        '&companyId=' +
        companyId
    );
  }

  CreateExtraordinaryAppointment(
    eoAppoitnment: ExtraordinaryAppointment,
    reservation: ReservationCreate,
    companyId: number
  ): Observable<Appointment> {
    let createDto = {
      extraordinaryAppointmentDTO: eoAppoitnment,
      reservationCreateDTO: reservation,
      companyId: companyId,
    };
    return this.http.post<Appointment>(
      environment.apiHost + 'appointments/selectExtraordinaryAppointment',
      createDto
    );
  }

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
  getAppointmentsForUser(userId: number): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(
      environment.apiHost + 'appointments/futureappointments/' + userId
    );
  }

  getAllAppointments(id: number): Observable<any[]> {
    return this.http.get<any>(
      environment.apiHost + 'appointments/company/full/' + id
    );
  }

  getReservationUserByAppointmentId(id: number): Observable<ResApp> {
    return this.http.get<ResApp>(
      environment.apiHost + 'appointments/reservationUser/' + id
    );
  }
}
