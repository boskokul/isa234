import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserCreate } from 'src/app/model/user-create.model';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  hide = true
  user: UserCreate = {firstName: '', lastName: '', email: '', city: '', country: '', phoneNumber: 0, companyInformation: '', profession: '', password: ''}
  repeatPassword: string
  registerForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    companyInformation: new FormControl('', [Validators.required]),
    profession: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    repeatPassword: new FormControl('', [Validators.required]),
  });
  constructor(private userService: UserServiceService, private router: Router){}
  ngOnInit(): void {
  }
  
  RegisterUser(){
    this.fillUser()
    if(this.user.password != this.repeatPassword){
      alert('passwords must match!')
      return;
    }
    console.log(this.user)
    this.userService.registerUser(this.user).subscribe({
      next: () => { 
        alert('user registered')
        this.router.navigate(['/companies'])
       }
    });
  }

  fillUser(){
    this.user.firstName = this.registerForm.value.firstName || ""
    this.user.lastName = this.registerForm.value.lastName || ""
    this.user.email = this.registerForm.value.email || ""
    this.user.phoneNumber = Number(this.registerForm.value.phoneNumber || "")
    this.user.city = this.registerForm.value.city || ""
    this.user.country = this.registerForm.value.country || ""
    this.user.companyInformation = this.registerForm.value.companyInformation || ""
    this.user.profession = this.registerForm.value.profession || ""
    this.user.password = this.registerForm.value.password || ""
    this.repeatPassword = this.registerForm.value.repeatPassword || ""
    this.user.companyInformation = "."
  }
}
