import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CurrentUser } from 'src/app/model/current-user';
import { UserUpdate } from 'src/app/model/user-update.model';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit, OnDestroy {
  user: User;
  userBackup: User;
  editable: boolean;
  loggedInUserData: CurrentUser | undefined
  subscription: Subscription
  constructor(private service: UserServiceService, private authService: AuthService){ }

  ngOnInit(): void{
    this.loadUser();
  }

  loadUser(){
    this.subscription = this.authService.currentUser.subscribe(user => {
      this.loggedInUserData = user;
    });
    setTimeout(() => {
      this.service.getLoggedUser(this.loggedInUserData!.id).subscribe({
        next: (result: User) => {
          this.userBackup = result;
          this.user = Object.assign({}, this.userBackup);
        },
        error: (err: any) => {
          console.log(err);
        }
      });
    }, 100);
  }
  ngOnDestroy() {
    this.subscription.unsubscribe()
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
