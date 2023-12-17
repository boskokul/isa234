package ftn.isa.dto;

public class ExtraordinaryAppointmentCreateDTO {
    ExtraordinaryAppointmentDTO extraordinaryAppointmentDTO;
    ReservationCreateDTO reservationCreateDTO;
    int companyId;

    public ExtraordinaryAppointmentCreateDTO(ExtraordinaryAppointmentDTO extraordinaryAppointmentDTO, ReservationCreateDTO reservationCreateDTO, int companyId) {
        this.extraordinaryAppointmentDTO = extraordinaryAppointmentDTO;
        this.reservationCreateDTO = reservationCreateDTO;
        this.companyId = companyId;
    }

    public ExtraordinaryAppointmentDTO getExtraordinaryAppointmentDTO() {
        return extraordinaryAppointmentDTO;
    }

    public void setExtraordinaryAppointmentDTO(ExtraordinaryAppointmentDTO extraordinaryAppointmentDTO) {
        this.extraordinaryAppointmentDTO = extraordinaryAppointmentDTO;
    }

    public ReservationCreateDTO getReservationCreateDTO() {
        return reservationCreateDTO;
    }

    public void setReservationCreateDTO(ReservationCreateDTO reservationCreateDTO) {
        this.reservationCreateDTO = reservationCreateDTO;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
