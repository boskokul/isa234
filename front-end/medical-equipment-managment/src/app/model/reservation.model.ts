import { DateTime } from 'luxon';

export interface Reservation {
  id: number;
  userId: number;
  appointmentId: number;
  status: string;
  dateTime: DateTime;
  usersName: string;
  usersEmail: string;
}
