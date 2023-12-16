import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Company } from 'src/app/model/company';
import { Equipment } from 'src/app/model/equipment';
import { CompanyService } from 'src/app/services/company.service';
import { EquipmentService } from 'src/app/services/equipment.service';
import { EquipmentAppointmentComponent } from '../equipment-appointment/equipment-appointment.component';
import { AuthService } from 'src/app/services/auth.service';
import { CurrentUser } from 'src/app/model/current-user';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css'],
})
export class CompanyProfileComponent implements OnInit {
  companyId: number;
  name: string;
  description: string;
  country: string;
  city: string;
  averageGrade: number;
  items: Equipment[] = [];
  quantities: number[] = [];
  user: CurrentUser | undefined
  constructor(
    private companyService: CompanyService,
    private route: ActivatedRoute,
    private equipmentService: EquipmentService,
    private authService: AuthService,
    public dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.authService.currentUser.subscribe(user => {
      this.user = user;
    });
    this.companyService.getCompany(this.route.snapshot.params['id']).subscribe({
      next: (result: Company) => {
        this.name = result.name;
        this.description = result.description;
        this.country = result.country;
        this.city = result.city;
        this.averageGrade = result.averageGrade;
        this.companyId = result.id;
        this.loadItems();
      },
    });
  }

  loadItems() {
    this.equipmentService.getCompanyEquipment(this.companyId).subscribe({
      next: (result: Equipment[]) => {
        this.items = result;
        this.quantities = new Array<number>(this.items.length).fill(0);
      },
    });
  }

  buyEquipment() {
    if(Math.max(...this.quantities) == 0){
      alert('Please select equipment you want to buy');
    }
    else{
      let selectedEquipments: Equipment[] = [];
      let selectedQuantities: number[] = [];
      for(let i = 0; i < this.items.length; i++){
        if(this.quantities[i] > 0){
          selectedEquipments.push(this.items[i]);
          selectedQuantities.push(this.quantities[i]);
        }
      }
      const dialog = this.dialog.open(EquipmentAppointmentComponent, {
        data: {
          equipments: selectedEquipments,
          quantities: selectedQuantities,
          companyId: this.companyId,
          userId: this.user!.id
        },
        width: '800px',
        height: '600px'
      })
    }
  }
}
