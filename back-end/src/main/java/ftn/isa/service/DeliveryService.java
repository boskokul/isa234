package ftn.isa.service;

import ftn.isa.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.table.TableStringConverter;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.lang.Math;

import static java.lang.Math.abs;

@Component
public class DeliveryService {
    @Autowired
    Producer producer;

    @Autowired
    ContractService contractService;


    @Scheduled( cron = "0 */5 * ? * *") // Run every 3 minutes
    public void checkDelivery() {
        Integer todaysDayOfMonth = LocalDateTime.now().getDayOfMonth();
        Integer hours = LocalDateTime.now().getHour();
        Integer minutes = LocalDateTime.now().getMinute();
        Integer totalMins =  hours*60 + minutes;
        List<Contract> contracts = contractService.getByDayOfMonth(todaysDayOfMonth);
        for(Contract c : contracts){
            if(abs(c.getHours()*60 + c.getMinutes() - totalMins) < 3){
                if(c.getStatus() == ContractStatus.NotDelivered){
                    c.setStatus(ContractStatus.Delivered);
                    contractService.update(c);
                    producer.sendTo("eksterna", "Slanje bolnici " + c.getHospitalName());
                    continue;
                }
            }
            if(abs(c.getHours()*60 + c.getMinutes() + c.getDuration() - totalMins) < 3){
                if(c.getStatus() == ContractStatus.Canceled){
                    c.setStatus(ContractStatus.NotDelivered);
                    contractService.update(c);
                    continue;
                }
                if(c.getStatus() == ContractStatus.Delivered){
                    c.setStatus(ContractStatus.NotDelivered);
                    contractService.update(c);
                    producer.sendTo("eksterna", "Zavrsava se isporuka bolnici " + c.getHospitalName());
                    continue;
                }
            }
        }
    }
}
