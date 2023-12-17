import { ExtraordinaryAppointment } from "./extraordinary-appointment.model";
import { ReservationCreate } from "./reservation-create";

export interface ExtraordinaryAppointmentCreate{
    eoAppointmet: ExtraordinaryAppointment;
    reservation: ReservationCreate;
    companyId: number;
}