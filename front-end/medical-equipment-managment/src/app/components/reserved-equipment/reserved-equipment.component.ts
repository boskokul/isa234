import { Component } from '@angular/core';
import jsQR from 'jsqr';

@Component({
  selector: 'app-reserved-equipment',
  templateUrl: './reserved-equipment.component.html',
  styleUrls: ['./reserved-equipment.component.css']
})
export class ReservedEquipmentComponent {
  public decodedData: string | null = null;
  public imageDataUrl: string | null = null; 

  onFileSelected(event: any): void {
    const input = event.target;

    if (input.files && input.files[0]) {
      const reader = new FileReader();

      reader.onload = (e) => {
        const dataUrl = e.target?.result as string;
        this.imageDataUrl = dataUrl; 
        console.log(this.imageDataUrl)
      };
      
      reader.readAsDataURL(input.files[0]);
    }
  }

  decodeImage(): void {
    if (this.imageDataUrl) {
      const img = new Image();
      img.onload = () => {
        const canvas = document.createElement('canvas');
        canvas.width = img.width;
        canvas.height = img.height;
        const context = canvas.getContext('2d');
        context!.drawImage(img, 0, 0);

        const imageData = context!.getImageData(0, 0, img.width, img.height);
        console.log(imageData)
        const code = jsQR(imageData.data, imageData.width, imageData.height);

        if (code) {
          this.decodedData = code.data;
          console.log('Found QR code', code);
        } else {
          console.log('No QR code found or couldn\'t decode.');
        }
      };

      img.onerror = (error) => {
        console.error('Error loading image:', error);
      };

      img.src = this.imageDataUrl;
    }
  }

}
