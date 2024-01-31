package ftn.isa.service;

import ftn.isa.domain.*;
import ftn.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ReservationChecker {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RegisteredUserRepository registeredUserRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    ReservationItemRepository reservationItemRepository;
    /*
*/
    @Transactional
    @Scheduled( cron = "0 */30 * ? * *") // Run every 30 minutes
    public void checkDeliveryTime() {
        LocalDateTime now = LocalDateTime.now();
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation r : reservations) {
            if(r.getStatus() == ReservationStatus.NotFinalized){
                Reservation reservation = reservationRepository.getReferenceById(r.getId());
                if(reservation.getAppointment().getDateTime().isBefore(now)) {
                    reservation.setStatus(ReservationStatus.Rejected);
                    Integer usersId = reservation.getRegisteredUser().getId();
                    reservationRepository.save(reservation);
                    RegisteredUser user = registeredUserRepository.getReferenceById(usersId);
                    user.setPenalPoints(user.getPenalPoints()+2);
                    registeredUserRepository.save(user);
                    updateEquipmentAfterRejected(reservation.getId());
                }
            }
            if (r.getAppointment().getDateTime().isBefore(now)) {
            }

        }
        /*
         */
    }
    private void updateEquipmentAfterRejected(Integer reservationId){
        List<ReservationItem> reservationItems =  reservationItemRepository.findByReservationId(reservationId);
        for(ReservationItem item: reservationItems){
            Equipment equipment = item.getEquipment();
            equipment.setReservedAmount(equipment.getReservedAmount() - item.getAmount());
            equipment.setFreeAmount(equipment.getFreeAmount() + item.getAmount());
            equipmentRepository.save(equipment);
        }
    }
/*
    */
}

