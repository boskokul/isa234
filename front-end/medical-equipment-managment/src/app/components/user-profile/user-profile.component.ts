import { Component, OnInit } from '@angular/core';
import { UserUpdate } from 'src/app/model/user-update.model';
import { User } from 'src/app/model/user.model';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {
  user: User;
  userBackup: User;
  editable: boolean;

  constructor(private service: UserServiceService){ }

  ngOnInit(): void{
    this.loadUser();
  }

  loadUser(){
    //TODO change id
    this.service.getLoggedUser(1).subscribe({
      next: (result: User) => {
        this.userBackup = result;
        this.user = Object.assign({}, this.userBackup);
      },
      error: (err: any) => {
        console.log(err);
      }
    });
  }

  updateUser(){
    let userUpdate : UserUpdate = {
      id: this.user.id,
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      city: this.user.city,
      country: this.user.country,
      phoneNumber: this.user.phoneNumber,
      profession: this.user.profession,
      companyInformation: this.user.companyInformation,
    }
    this.service.updateUser(userUpdate).subscribe({
      next: (result: User) => {
        this.userBackup = result;
        this.user = Object.assign({}, this.userBackup);
      },
      error: (err: any) => {
        console.log(err);
      }
    });
  }

  toggleEdit(): void{
    if(this.editable){
      this.user = Object.assign({}, this.userBackup);
    }
    this.editable = !this.editable;
  }
}
