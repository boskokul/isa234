import { Time } from '@angular/common';

export interface CompanyCreate {
  id: number;
  name: string;
  description: string;
  averageGrade: number;
  country: string;
  city: string;
  startHour?: number;
  startMinute?: number;
  endHour?: number;
  endMinute?: number;
  lat: number;
  lon: number;
  street: string;
  houseNumber: number;
}
