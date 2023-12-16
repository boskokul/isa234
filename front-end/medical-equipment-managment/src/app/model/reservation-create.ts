export interface ReservationCreate{
    userId: number,
    appointmentId: number,
    amounts:number[],
    equipmentIds:number[]
}