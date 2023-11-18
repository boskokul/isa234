import { Component, OnDestroy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Equipment } from 'src/app/model/equipment';
import { EquipmentService } from 'src/app/services/equipment.service';
import { CompanyListPopupComponent } from '../company-list-popup/company-list-popup.component';
import { Company } from 'src/app/model/company';

@Component({
  selector: 'app-equipment',
  templateUrl: './equipment.component.html',
  styleUrls: ['./equipment.component.css']
})
export class EquipmentComponent implements OnDestroy{
  items: Equipment[] = [];
  backupItems: Equipment[] = [];
  nameFilter: string = '';
  typeFilter: string = 'All';
  private subscriptions: Subscription[] = [];


  constructor(private equipmentService: EquipmentService,  private router: Router, public dialog:MatDialog) {
    this.loadItems();
  }

  loadItems() {
    const subscription = this.equipmentService.getAllEquipment().subscribe(
      (data) => {
        this.items = data;
        this.backupItems = data;
      },
      (error) => {
        console.error('Error loading equipment:', error);
      }
    ); 

  }

  applyFilters() {
    this.items = this.backupItems;
    const filteredItems = this.items.filter(item => {
      const nameMatch = item.name.toLowerCase().includes(this.nameFilter.toLowerCase());
      const typeMatch = this.typeFilter === 'All' || item.type === this.typeFilter;
      return nameMatch && typeMatch;
    });
    // Update the items to display the filtered results
    this.items = filteredItems;
  }


  showCompanies(itemId: number){
    this.equipmentService.getEquipmentCompanies(itemId).subscribe({
      next: (result: Company[]) => {
        this.dialog.open(CompanyListPopupComponent, {
          data: result,
           width: '300px',
           height:'300px',
           panelClass: 'custom-dialog',
         });
         console.log(result);
      },
    });


  }



    

  ngOnDestroy() {
    // Unsubscribe from all subscriptions to avoid memory leaks when the component is destroyed
    this.subscriptions.forEach((subscription) => {
      subscription.unsubscribe();
    });
  }
}
