import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../env/environment';
import { ReservationCreate } from '../model/reservation-create';
import { ReservationCancel } from '../model/reservation-cancel';
import { Reservation } from '../model/reservation.model';

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  constructor(private http: HttpClient) {}

  MakeReservation(reservation: ReservationCreate): Observable<any[]> {
    return this.http.post<any>(
      environment.apiHost + 'reservation',
      reservation
    );
  }
  CancelReservation(reservation: ReservationCancel): Observable<any[]> {
    return this.http.put<any>(
      environment.apiHost + 'reservation/cancelReservation',
      reservation
    );
  }
  getAllUsersWithResInCompany(companyId: number): Observable<any[]> {
    return this.http.get<any>(
      environment.apiHost + 'reservation/users/' + companyId
    );
  }
  getFutureReservations(adminsId: number): Observable<any[]> {
    return this.http.get<any>(
      environment.apiHost + 'reservation/futureReservations/' + adminsId
    );
  }
  SetResDone(reservation: Reservation): Observable<any[]> {
    return this.http.put<any>(
      environment.apiHost + 'reservation/setReservationDone',
      reservation
    );
  }
}
