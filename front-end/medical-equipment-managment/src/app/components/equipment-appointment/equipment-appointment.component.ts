import { DatePipe } from '@angular/common';
import { Component, Inject, ViewChild, OnInit, SimpleChanges, Input } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Appointment } from 'src/app/model/appointment.model';
import { Company } from 'src/app/model/company';
import { Equipment } from 'src/app/model/equipment';
import { CompanyService } from 'src/app/services/company.service';


export interface ModalData {
  equipment: Equipment,
  company: Company
}
@Component({
  selector: 'app-equipment-appointment',
  templateUrl: './equipment-appointment.component.html',
  styleUrls: ['./equipment-appointment.component.css'],
  providers: [DatePipe]
})
export class EquipmentAppointmentComponent implements OnInit {
  equipment: Equipment;
  availableAppointments: any;
  quantity: number = 0;
  showForm: boolean = false;

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
    @Inject(MAT_DIALOG_DATA) public data: ModalData,
    public datePipe: DatePipe,
  ) {
    this.equipment = data.equipment;
    this.availableAppointments = new MatTableDataSource<Appointment>([]);
    this.availableTimes = new MatTableDataSource([]);
    let temp: Appointment = {
      id: 0,
      adminName: 'Pera Peric',
      dateTime: new Date(),
      duration: 30,
    }
    this.availableAppointments.data.push(temp);
  }
  
  ngOnInit(): void {
    this.LoadAvailableAppointments();
  }

  ngAfterViewInit() {
    this.availableAppointments.sort = this.sort;
  }
  
  LoadAvailableAppointments(): void {
    //TODO
  }

  ToggleExtraordinaryAppointmentForm(){
    this.showForm = !this.showForm;
  }

  ReserveAppointment(appointment: Appointment){
    //TODO
    //Check quantity
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
