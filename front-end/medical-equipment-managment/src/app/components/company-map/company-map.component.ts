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
}
