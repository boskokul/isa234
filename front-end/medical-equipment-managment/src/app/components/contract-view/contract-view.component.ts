import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Contract } from 'src/app/model/contract';
import { ContractService } from 'src/app/services/contract.service';

@Component({
  selector: 'app-contract-view',
  templateUrl: './contract-view.component.html',
  styleUrls: ['./contract-view.component.css']
})
export class ContractViewComponent implements OnInit {
  contracts: any;
  displayedColumns: string[] = [
    'hospitalName',
    'date',
    'time',
    'duration',
    'status',
    'equipment',
    'cancel',
  ];
  currentMonth: number;
  nextMonth: number;
  currentDay: number;
  constructor(
    private contractService: ContractService,
  ) {
    this.contracts = new MatTableDataSource<Contract>([]);
  }


  ngOnInit(): void {
    this.LoadContracts();
    this.currentMonth = new Date().getMonth() + 1;
    this.nextMonth = this.currentMonth == 12? 1 : this.currentMonth+1;
    this.currentDay = new Date().getDate();
  }

  LoadContracts(): void {
    this.contractService.getAllContracts().subscribe({
      next:(result) => {
        this.contracts.data = result;
      },
    });
  }

  CancelContract(c: Contract): void {
    c.status = "Canceled";
    this.contractService.cancelContract(c).subscribe({
      next:(result) => {
        console.log("Uspesno otkazan ugovor");
      },
    });
  }

  daysInMonth(nextMonths: number): number {
    let now = new Date();
    return new Date(now.getFullYear(), now.getMonth()+1+nextMonths, 0).getDate();
  }

  getMonth(dayInDB: number): number{
    if(dayInDB >= this.currentDay) return this.currentMonth;
    let next = 1;
    while(this.daysInMonth(next) < dayInDB){
      next += 1;
    }
    return this.currentMonth+next;
  }
}
