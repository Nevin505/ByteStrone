import { Category } from './../model/category';
import { Component } from '@angular/core';
import { Customerlogin } from '../model/customerlogin';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { Agentlogin } from '../model/agentlogin';
import { FormGroup ,FormControl, Validators} from '@angular/forms';
import { UserDetails } from '../model/user-details';
import * as CryptoJS from 'crypto-js';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  // loginform=new FormGroup({
  //   username:new FormControl('',Validators.required),
  //   Password:new FormControl('',Validators.required),
  //   Role:new FormControl('',Validators.required)


  // })

  UserName!:string;
  Password!:string;
  customerId:number=0;
  customerlogin:Customerlogin=new Customerlogin();
  Role!:string;

  agent:Agentlogin=new Agentlogin();
  agentName!:string;
  agentPassword!:string;
  loginForm:FormGroup;


  constructor(private api:ApiService,private router:Router){
   
    this.loginForm = new FormGroup({
      agentName: new FormControl('', [Validators.required]),
      agentPassword: new FormControl('', [Validators.required])
      // role: new FormControl('', [Validators.required])
    });
    
  }


  ngOnInit(){
    sessionStorage.clear();
  }
    userDetails:UserDetails=new UserDetails();
    
    loginAgent() {
      this.userDetails.userName = this.loginForm.value.agentName.trim();
      this.userDetails.userPassword = this.loginForm.value.agentPassword.trim();
    this.api.validatelogin(this.userDetails).subscribe({
      next: (res: any) => {
        if(res.success){
          sessionStorage.setItem('token','t');
            if(res.data.role=='Agent'){
              const secretKey = 'your-secret-key';
              const dataToEncrypt = res.data.id.toString();
              const wordArray = CryptoJS.enc.Utf8.parse(dataToEncrypt);
              const encryptedData = CryptoJS.AES.encrypt(wordArray, secretKey).toString();
              sessionStorage.setItem('AgentId', encryptedData);   
              sessionStorage.setItem("agentName",res.data.userName);
              sessionStorage.setItem("Categorys",JSON.stringify(res.data.category));
              this.router.navigate(['agentView']);
              alert("Agent")
            }
            else if(res.data.role==='Supervisor'){
              sessionStorage.setItem("superVisorId",res.data.id);
              this.router.navigate(['supervisor']);
            }      
          else if(res.data.role==='Customer'){
            const secretKey = 'your-secret-key';
            const dataToEncrypt = res.data.id.toString();
            const wordArray = CryptoJS.enc.Utf8.parse(dataToEncrypt);
            const encryptedData = CryptoJS.AES.encrypt(wordArray, secretKey).toString();
            sessionStorage.setItem('customerId', encryptedData);
            sessionStorage.setItem('customerName',res.data.userName);
            this.router.navigate(['customerview']);
            alert("Customer")
          }
        }
        else{
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

  


    // if(res.data.category.length!=0){
    //   sessionStorage.setItem("Categorys",JSON.stringify(res.data.category));
    // }
    // else{
    //   sessionStorage.setItem("Categorys",JSON.stringify(res.data.category));
    // }
 