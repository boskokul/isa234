import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { Equipment } from 'src/app/model/equipment';
import { EquipmentCreate } from 'src/app/model/equipment-create.model';
import { EquipmentService } from 'src/app/services/equipment.service';

@Component({
  selector: 'app-equipment-create',
  templateUrl: './equipment-create.component.html',
  styleUrls: ['./equipment-create.component.css'],
})
export class EquipmentCreateComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public equipment: EquipmentCreate,
    public dialog: MatDialog,
    private equipmentService: EquipmentService
  ) {
    console.log(this.equipment.name);
  }
  addEquipment() {
    this.equipmentService.addEquipment(this.equipment).subscribe({
      next: (result: Equipment) => {
        this.dialog.closeAll();
      },
    });
  }
}
