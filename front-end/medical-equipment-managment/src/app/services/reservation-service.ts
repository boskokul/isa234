import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../env/environment';
import { ReservationCreate } from '../model/reservation-create';

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  constructor(private http: HttpClient) {}

  MakeReservation(reservation: ReservationCreate): Observable<any[]> {
    return this.http.post<any>(environment.apiHost + 'reservation', reservation);
  }

}
