package ftn.isa.dto;

import java.util.ArrayList;

public class ReservationCreateDTO {
    private int appointmentId;
    private int userId;
    private ArrayList<Integer> amounts = new ArrayList<>();
    private ArrayList<Integer> equipmentIds = new ArrayList<>();

    public ReservationCreateDTO(){

    }

    public ReservationCreateDTO(int appointmentId, int userId, ArrayList<Integer> amounts, ArrayList<Integer> equipmentIds) {
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.amounts = amounts;
        this.equipmentIds = equipmentIds;
    }

    public ArrayList<Integer> getAmounts() {
        return amounts;
    }

    public void setAmounts(ArrayList<Integer> amounts) {
        this.amounts = amounts;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Integer> getEquipmentIds() {
        return equipmentIds;
    }

    public void setEquipmentIds(ArrayList<Integer> equipmentIds) {
        this.equipmentIds = equipmentIds;
    }
}
