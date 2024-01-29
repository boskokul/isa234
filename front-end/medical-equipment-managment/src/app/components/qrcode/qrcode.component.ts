import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-qrcode',
  templateUrl: './qrcode.component.html',
  styleUrls: ['./qrcode.component.css']
})
export class QRcodeComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public qrcontent: string,
    public dialog: MatDialog,
  ) {}
}
