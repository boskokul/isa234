package ftn.isa.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contracts", schema = "isa")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hospital_name", nullable = false, unique = true)
    private String hospitalName;

    @Column(name = "duration", nullable = true)
    private Integer duration;

    @Column(name = "day_of_month", nullable = false)
    private Integer dayOfMonth;

    @Column(name = "hours", nullable = false)
    private Integer hours;

    @Column(name = "minutes", nullable = false)
    private Integer minutes;

    @Column(name = "status", nullable = false)
    private ContractStatus status;

    @OneToMany(mappedBy = "contract", fetch = FetchType.LAZY)
    @JsonProperty("contractEquipment")
    private Set<ContractEquipment> contractEquipment = new HashSet<ContractEquipment>();

    public Contract(){}

    public Contract(String hospitalName, Integer duration, Integer dayOfMonth, Integer hours, Integer minutes, ContractStatus status) {
        this.setHospitalName(hospitalName);
        this.setDuration(duration);
        this.setDayOfMonth(dayOfMonth);
        this.setHours(hours);
        this.setMinutes(minutes);
        this.setStatus(status);
    }

    @Override
    public String toString() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Contract(String contract){
        ObjectMapper om = new ObjectMapper();
        try {
            Contract contract1 = om.readValue(contract, Contract.class);
            this.setId(contract1.id);
            this.setHospitalName(contract1.hospitalName);
            this.setDuration(contract1.duration);
            this.setDayOfMonth(contract1.dayOfMonth);
            this.setHours(contract1.hours);
            this.setMinutes(contract1.minutes);
            this.setStatus(contract1.status);
            this.setContractEquipment(contract1.contractEquipment);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMount) {
        if(dayOfMount > 31) dayOfMount = 31;
        if(dayOfMount < 0) dayOfMount = 0;
        this.dayOfMonth = dayOfMount;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        if(hours < 0) hours = 0;
        if(hours > 24) hours = 24;
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        if(minutes > 59) minutes = 59;
        if(minutes < 0) minutes = 0;
        this.minutes = minutes;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public Set<ContractEquipment> getContractEquipment() {
        return contractEquipment;
    }

    public void setContractEquipment(Set<ContractEquipment> contractEquipment) {
        this.contractEquipment = contractEquipment;
    }
}
