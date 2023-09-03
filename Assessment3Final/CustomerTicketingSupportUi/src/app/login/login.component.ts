import { Category } from './../model/category';
import { Component } from '@angular/core';
// import { Customerlogin } from '../model/customerlogin';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
// import { Agentlogin } from '../model/agentlogin';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserDetails } from '../model/user-details';
import * as CryptoJS from 'crypto-js';
import { HttpErrorResponse } from '@angular/common/http';
import { CustomerDetails } from '../model/customer-details';
import { Agent } from '../model/agent';
import { Common } from '../model/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  UserName!: string;
  Password!: string;
  customerId: number = 0;
  // customerlogin!: CustomerDetails;
  // Role!:string;

  agent!: Agent;
  agentName!: string;
  agentPassword!: string;
  loginForm: FormGroup;


  constructor(private api: ApiService, private router: Router) {
    this.loginForm = new FormGroup({
      agentName: new FormControl('', [Validators.required]),
      agentPassword: new FormControl('', [Validators.required])
    });
  }


  ngOnInit() {
    sessionStorage.clear();
    
  }
  userDetails:UserDetails=new UserDetails();

  loginAgent() {
    this.userDetails.userName = this.loginForm.value.agentName.trim();
    this.userDetails.userPassword = this.loginForm.value.agentPassword.trim();
    this.api.validatelogin(this.userDetails).subscribe({
      next: (res: Common) => {
        if (res.success) {
          sessionStorage.setItem('token', 't');
          if (res.data.role == 'Agent') {
            const secretKey = 'your-secret-key';
            const dataToEncrypt = res.data.id.toString();
            const wordArray = CryptoJS.enc.Utf8.parse(dataToEncrypt);
            const encryptedData = CryptoJS.AES.encrypt(wordArray, secretKey).toString();
            sessionStorage.setItem('AgentId', encryptedData);
            sessionStorage.setItem("agentName", res.data.userName);
            sessionStorage.setItem("Categorys", JSON.stringify(res.data.category));
            sessionStorage.setItem("agentRoutes", "agentRoutes");
            this.router.navigate(['agentView']);
            alert("Agent")
          }
          else if (res.data.role === 'Supervisor') {
            sessionStorage.setItem("superVisorId", res.data.id);
            sessionStorage.setItem("superVisorName", res.data.userName);
            this.router.navigate(['supervisor']);
          }
          else if (res.data.role === 'Customer') {
            const secretKey = 'your-secret-key';
            const dataToEncrypt = res.data.id.toString();
            const wordArray = CryptoJS.enc.Utf8.parse(dataToEncrypt);
            const encryptedData = CryptoJS.AES.encrypt(wordArray, secretKey).toString();
            sessionStorage.setItem('customerId', encryptedData);
            sessionStorage.setItem('customerName', res.data.userName);
            sessionStorage.setItem('customerRoutes',"customerRoutes")
            this.router.navigate(['customerview']);
            alert("Customer")
          }
        }
        else {
          alert(res.mssg)
        }
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
        alert(error.error.mssg)
      }
    })
  }
}
