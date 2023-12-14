package ftn.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.isa.domain.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {


}
