import { DateTime } from 'luxon';

export interface AppointmentCreate {
  adminsId: number;
  dateTime: DateTime;
  duration: number;
}
