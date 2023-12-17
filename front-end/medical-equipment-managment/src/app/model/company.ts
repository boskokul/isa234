import { Time } from '@angular/common';

export interface Company {
  id: number;
  name: string;
  description: string;
  averageGrade: number;
  country: string;
  city: string;
  startTime: Time;
  endTime: Time;
  lat: number;
  lon: number;
  street: string;
  houseNumber: number;
}
