import { DatePipe } from '@angular/common';
import { Component, Inject, ViewChild, OnInit, SimpleChanges, Input, ChangeDetectorRef } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Appointment } from 'src/app/model/appointment.model';
import { Company } from 'src/app/model/company';
import { Equipment } from 'src/app/model/equipment';
import { ReservationCreate } from 'src/app/model/reservation-create';
import { CompanyService } from 'src/app/services/company.service';
import { ReservationService } from 'src/app/services/reservation-service';


export interface ModalData {
  equipments: Equipment[],
  quantities: number[],
  company: Company,
  companyId: number,
  userId: number
}
@Component({
  selector: 'app-equipment-appointment',
  templateUrl: './equipment-appointment.component.html',
  styleUrls: ['./equipment-appointment.component.css'],
  providers: [DatePipe]
})
export class EquipmentAppointmentComponent implements OnInit {
  equipments: Equipment[];
  quantities: number[];
  companyId: number
  availableAppointments: any;
  showForm: boolean = false;
  reservation: ReservationCreate = {amounts:[], equipmentIds:[], userId:0, appointmentId:0};
  //Extraordinary dates
  private _selectedDate: Date;
  get selectedDate(): Date {
    return this._selectedDate;
  }
  set selectedDate(value: Date) {
    if(new Date(value) <= new Date()){
      alert('Select valid date!');
      this._selectedDate = this._selectedDate;
    }
    else{
      this._selectedDate = value;
    }
    this.LoadAvailableAppointments();
  }
  
  dateSelected: boolean = false;
  validDate: boolean = false;
  availableTimes: any;



  displayedColumns: string[] = ['date', 'duration', 'adminName', 'button'];
  displayedColumnsAvailableAppointments: string[] = [];

  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private companyService: CompanyService,
    private reservationService: ReservationService,
    @Inject(MAT_DIALOG_DATA) public data: ModalData,
    public datePipe: DatePipe,
    public dialog: MatDialogRef<EquipmentAppointmentComponent>
  ) {
    this.equipments = data.equipments;
    this.quantities = data.quantities;
    this.companyId = data.companyId;
    this.availableAppointments = new MatTableDataSource<Appointment>([]);
    this.availableTimes = new MatTableDataSource([]);
  }
  
  ngOnInit(): void {
    this.LoadAvailableAppointments();
  }

  ngAfterViewInit() {
    this.availableAppointments.sort = this.sort;
  }
  
  LoadAvailableAppointments(): void {
    this.companyService.getAppointmentsForCompany(this.companyId).subscribe({
      next: (result: Appointment[]) => {
        this.availableAppointments.data = result;
      },
    });
  }

  ToggleExtraordinaryAppointmentForm(){
    this.showForm = !this.showForm;
  }

  ReserveAppointment(appointment: Appointment){
    this.reservation.amounts = []
    this.reservation.equipmentIds = []
    for(let i = 0; i < this.quantities.length; i++){
      if(this.quantities[i] > this.equipments[i].freeAmount){
        alert('There is no enough equipment')
        return;
      }
    }
    this.reservation.amounts = this.quantities
    for(let i = 0; i < this.equipments.length; i++){
      this.reservation.equipmentIds.push(this.equipments[i].id)
    }
    this.reservation.appointmentId = appointment.id
    this.reservation.userId = this.data.userId
    console.log(this.reservation) 
    this.reservationService.MakeReservation(this.reservation).subscribe({
      next: (result: any) => {
        alert('Reservation made')
        this.dialog.close()
      },
      error:(data)=>{
        console.log(data)
        alert('Error while making reservation')
      }
    });
  }

  CheckIfValidDate() {
    let tommorow = new Date();
    tommorow.setDate(tommorow.getDate() + 1);
    if(this.selectedDate < tommorow){
      alert('Select valid date!');
    }
  }

  SelectExtraordinaryAppointment(): void{
    //TODO
  }

}
