import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { ResEquipment } from 'src/app/model/resEquipment.model';
export interface ModalData {
  equipment: ResEquipment[];
}
@Component({
  selector: 'app-res-equipment-details',
  templateUrl: './res-equipment-details.component.html',
  styleUrls: ['./res-equipment-details.component.css'],
})
export class ResEquipmentDetailsComponent implements OnInit {
  equipment: ResEquipment[] = [];
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: ModalData,
    public dialog: MatDialog
  ) {}
  ngOnInit(): void {
    this.equipment = this.data.equipment;
    console.log(this.equipment);
  }
}
