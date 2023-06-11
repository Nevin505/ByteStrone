import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service'
@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrls: ['./customer-login.component.css']
})
export class CustomerLoginComponent {

  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    userpassword: new FormControl('', Validators.required),
  })
  constructor(private loginService:LoginService, private route: Router) {
  }
  loginData(){
    // this.route.navigate(['customer'])
    console.log(this.loginForm.value);
    this.loginService.userLoginAccess(this.loginForm.value);
  }

}
