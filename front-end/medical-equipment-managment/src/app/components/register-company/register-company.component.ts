import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { concatMap, forkJoin, of, switchMap, tap } from 'rxjs';
import { AdminCreate } from 'src/app/model/admin-create';
import { Company } from 'src/app/model/company';
import { UserCreate } from 'src/app/model/user-create.model';
import { AdminService } from 'src/app/services/admin.service';
import { CompanyService } from 'src/app/services/company.service';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-register-company',
  templateUrl: './register-company.component.html',
  styleUrls: ['./register-company.component.css']
})
  export class RegisterCompanyComponent implements OnInit{
    hide = true
    company: Company = {id: 0, name: '', description: '', averageGrade: 0, adress: ''}
    adminNames: string[] = [];
    admins: AdminCreate[] = [];
    companyId = 0;
    emptyFlag = false;
    user: AdminCreate = {firstName: '', lastName: '', email: '', city: '', country: '', phoneNumber: '', companyInformation: '', profession: '', password: '', companyId: 0}
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



    constructor(private userService: UserServiceService, private adminService: AdminService, private companyService: CompanyService, private router: Router){}
    ngOnInit(): void {
    }
    
    RegisterUser(){
      this.fillUser()
      if(this.emptyFlag==true){
        return;
      }
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

    RegisterCompany() {
      this.fillGiga();
      if (this.emptyFlag) {
        return;
      }
      if (this.admins.length === 0) {
        return;
      }
    
this.companyService.registerCompany(this.company).pipe(
  concatMap(() => this.companyService.getLastCompanyId())
).subscribe({
  next: (companyId) => { 
    this.companyId = companyId; // Assign the received companyId
    console.log("Both events completed");
    console.log(this.companyId); // Log the companyId after it's retrieved

    // Execute the code after companyId is received
    const updatedAdmins = this.admins.map(admin => {
      return { ...admin, companyId: this.companyId };
    });
    console.log(updatedAdmins);

    const registerObservables = updatedAdmins.map(admin => {
      return this.adminService.registerUser(admin);
    });

    forkJoin(registerObservables).subscribe(
      () => {
        console.log('All admins registered');
        this.router.navigate(['/companies']);
      },
      (err) => {
        console.error(err); // Handle errors if any
      }
    );
  },
  error: (err) => {
    // Handle errors if any
    console.error(err);
  }
});
    }
    
  
    fillUser(){
      this.emptyFlag=false
      this.user.firstName = this.registerForm.value.firstName || ""
      console.log(this.user.firstName);
      console.log(this.emptyFlag)
      if(this.user.firstName === ""){this.emptyFlag=true}
      console.log(this.emptyFlag)
      this.user.lastName = this.registerForm.value.lastName || ""
      if(this.user.lastName === ""){this.emptyFlag=true}
      this.user.email = this.registerForm.value.email || ""
      if(this.user.email === ""){this.emptyFlag=true}
      this.user.phoneNumber = this.registerForm.value.phoneNumber || ""
      console.log(this.user.phoneNumber);
      if(this.user.phoneNumber === ""){this.emptyFlag=true}
      this.user.city = this.registerForm.value.city || ""
      if(this.user.city === ""){this.emptyFlag=true}
      this.user.country = this.registerForm.value.country || ""
      if(this.user.country === ""){this.emptyFlag=true}
      this.user.profession = this.registerForm.value.profession || ""
      if(this.user.profession === ""){this.emptyFlag=true}
      this.user.password = this.registerForm.value.password || ""
      if(this.user.password === ""){this.emptyFlag=true}
      this.repeatPassword = this.registerForm.value.repeatPassword || ""
      if(this.repeatPassword === ""){this.emptyFlag=true}
      this.user.companyInformation = "."
      console.log(this.emptyFlag)
    }

    fillGiga() {
        this.emptyFlag=false
        this.company.name= this.gigaChadForm.value.name || ''
        if(this.company.name === ""){this.emptyFlag=true}
        this.company.description= this.gigaChadForm.value.description || ''
        if(this.company.description === ""){this.emptyFlag=true}
        this.company.adress= this.gigaChadForm.value.address || ''
        if(this.company.adress === ""){this.emptyFlag=true}
    }


}
