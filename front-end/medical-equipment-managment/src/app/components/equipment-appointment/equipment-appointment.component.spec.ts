import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipmentAppointmentComponent } from './equipment-appointment.component';

describe('EquipmentAppointmentComponent', () => {
  let component: EquipmentAppointmentComponent;
  let fixture: ComponentFixture<EquipmentAppointmentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EquipmentAppointmentComponent]
    });
    fixture = TestBed.createComponent(EquipmentAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
