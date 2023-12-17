import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { Company } from 'src/app/model/company';
import { CompanyService } from 'src/app/services/company.service';
import { MapComponent } from 'src/app/shared/maps/map/map.component';

@Component({
  selector: 'app-company-map',
  templateUrl: './company-map.component.html',
  styleUrls: ['./company-map.component.css'],
})
export class CompanyMapComponent implements OnInit {
  @ViewChild(MapComponent) mapComponent: MapComponent;
  constructor(
    @Inject(MAT_DIALOG_DATA) public company: Company,
    public dialog: MatDialog,
    private companyService: CompanyService
  ) {}
  ngOnInit() {
    console.log(this.company.name);
  }
  ngAfterViewInit() {
    // this returns null
    this.mapComponent
      .reverseSearch(this.company.lat, this.company.lon)
      .subscribe({
        next: (location) => {
          this.mapComponent.drawMe(this.company.lat, this.company.lon);
        },
      });
  }

  updateCompany() {
    this.companyService.updateCompany(this.company).subscribe({
      next: (result: Company) => {
        this.dialog.closeAll();
      },
    });
  }

  onMapClick(event: { lat: number; lon: number }) {
    this.searchByCoord(event.lat, event.lon);
  }

  private searchByCoord(lat: number, lon: number) {
    this.mapComponent.reverseSearch(lat, lon).subscribe({
      next: (location) => {
        // Handle the location data here
        const foundLocation = location;
        this.company.lon = foundLocation.lon;
        this.company.lat = foundLocation.lat;
        this.company.street = foundLocation.address.road || '';
        this.company.country = foundLocation.address.country || '';
        this.company.houseNumber = parseInt(
          foundLocation.address.house_number || ''
        );
        if (foundLocation.address.town) {
          this.company.city = foundLocation.address.town;
        } else if (foundLocation.address.city) {
          this.company.city = foundLocation.address.city;
        } else {
          this.company.city = foundLocation.address.city_district || '';
        }
      },
      error: (error) => {
        console.error('Error:', error);
      },
    });
  }
}
