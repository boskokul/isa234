import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminCreate } from 'src/app/model/admin-create';
import { Company } from 'src/app/model/company';
import { UserCreate } from 'src/app/model/user-create.model';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-register-company',
  templateUrl: './register-company.component.html',
  styleUrls: ['./register-company.component.css']
})
  export class RegisterCompanyComponent implements OnInit{
    hide = true
    company: Company = {id: 0, name: '', description: '', averageGrade: 0, address: ''}
    adminNames: string[] = [];
    admins: AdminCreate[] = [];
    user: AdminCreate = {firstName: '', lastName: '', email: '', city: '', country: '', phoneNumber: 0, companyInformation: '', profession: '', password: '', companyId: 0}
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

    gigaChadForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
    })



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
      for (const user of this.admins){
        if(this.user.email == user.email){
          console.log(this.user.email);
          console.log(user.email);
          return;
        }
        if(this.user.password == user.password){
          console.log(this.user.password);
          console.log(user.password);
          return;
        }
        if(this.user.phoneNumber == user.phoneNumber){
          console.log(this.user.phoneNumber);
          console.log(user.phoneNumber);
          return;
        }
      }
      const newUser: AdminCreate = {
        firstName: this.user.firstName,
        lastName: this.user.lastName,
        password: this.user.password,
        email: this.user.email,
        city: this.user.city,
        country: this.user.country,
        phoneNumber: this.user.phoneNumber,
        profession: this.user.profession,
        companyInformation: this.user.companyInformation,
        companyId: 0
    };
      this.admins.push(newUser)
      this.adminNames.push(this.user.firstName+" "+ this.user.lastName);

      /*this.userService.registerUser(this.user).subscribe({
        next: () => { 
          alert('user registered')
          this.router.navigate(['/companies'])
         }
      });*/
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

    fillGiga() {
        this.company.name= this.gigaChadForm.value.name || '',
        this.company.description= this.gigaChadForm.value.description || ''
        this.company.address= this.gigaChadForm.value.address || ''
    }
}
