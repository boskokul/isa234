package ftn.isa.dto;

public class ResAppDTO {
    String fullName;

    public ResAppDTO(){}

    public  ResAppDTO(String fullName){
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
