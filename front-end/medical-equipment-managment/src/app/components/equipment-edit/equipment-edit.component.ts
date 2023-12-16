import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { Company } from 'src/app/model/company';
import { Equipment } from 'src/app/model/equipment';
import { EquipmentService } from 'src/app/services/equipment.service';

@Component({
  selector: 'app-equipment-edit',
  templateUrl: './equipment-edit.component.html',
  styleUrls: ['./equipment-edit.component.css'],
})
export class EquipmentEditComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public equipment: Equipment,
    public dialog: MatDialog,
    private equipmentService: EquipmentService
  ) {
    console.log(this.equipment.name);
  }
  updateEquipment() {
    this.equipmentService.updateEquipment(this.equipment).subscribe({
      next: (result: Equipment) => {
        this.dialog.closeAll();
      },
    });
  }
}
