import { Category } from './../model/category';
import { Component } from '@angular/core';
import { Customerlogin } from '../model/customerlogin';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { Agentlogin } from '../model/agentlogin';
import { FormGroup ,FormControl, Validators} from '@angular/forms';
import { UserDetails } from '../model/user-details';
import * as CryptoJS from 'crypto-js';

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
  

    // loginAgent() {
    //   console.log(this.loginForm.value.role);
    //   if (this.loginForm.value.role === 'Customer') {
    //     this.customerlogin.username = this.loginForm.value.agentName;
    //     this.customerlogin.userpassword = this.loginForm.value.agentPassword;
    //     this.api.setCustomerName( this.customerlogin.username);
    //     localStorage.setItem("customerName",this.loginForm.value.agentName)
    //     this.api.validateCustomer(this.customerlogin).subscribe(res => { 
    //       console.log(res);
    //       if (res.success) {
    //         this.customerId = res.data.customerid;
    //         localStorage.setItem("customerUserId",res.data.customerid);
    //         this.api.customerIdSetter(res.data.customerid);
    //         this.router.navigate(['customerview']);
    //       } else {
    //         alert(res.mssg);
    //       }
    //     });
    //   } else if (this.loginForm.value.role === 'Agent') {
    //     this.agent.agentName = this.loginForm.value.agentName;
    //     this.api.setAgentName(this.agent.agentName);
    //     this.agent.agentPassword = this.loginForm.value.agentPassword;
    //     this.api.agentloginService(this.agent).subscribe((res: any) => {           
    //       if (res.data != null) {
    //         console.log(res.data.agentID);
    //         console.log(res.data);
    //         if (res.data.role == 'Agent') {
    //           this.api.agentIdSetter(res.data.agentID);
    //           localStorage.setItem("agentUserId",res.data.agentID);
    //           localStorage.setItem("Categorys",JSON.stringify(res.data.category))
    //           localStorage.setItem("agentName",JSON.stringify(res.data.agentName))
    //           console.log(res.data);
    //           this.api.agentCategorySetter(res.data.category);
    //           this.router.navigate(['agentView']);
    //         } else {
    //           this.api.setAgentName(this.agent.agentName);
    //           console.log(res.data);
    //           this.router.navigate(['supervisor']);
    //         }
    //       } else {
    //         alert(res.mssg);
    //       }
    //     });
    //   }
    // }

  

    userDetails:UserDetails=new UserDetails();
    
    loginAgent() {
      // console.log(this.loginForm.value.role);
      this.userDetails.userName = this.loginForm.value.agentName;
      this.userDetails.userPassword = this.loginForm.value.agentPassword;
     
    this.api.validatelogin(this.userDetails).subscribe((res:any)=>{
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
      
    })
  }


      // if (this.loginForm.value.role === 'Customer') {
      //   this.customerlogin.username = this.loginForm.value.agentName;
      //   this.customerlogin.userpassword = this.loginForm.value.agentPassword;
      //   this.api.setCustomerName( this.customerlogin.username);
      //   localStorage.setItem("customerName",this.loginForm.value.agentName)
      //   this.api.validateCustomer(this.customerlogin).subscribe(res => { 
      //     console.log(res);
      //     if (res.success) {
      //       this.customerId = res.data.customerid;
      //       localStorage.setItem("customerUserId",res.data.customerid);
      //       this.api.customerIdSetter(res.data.customerid);
      //       this.router.navigate(['customerview']);
      //     } else {
      //       alert(res.mssg);
      //     }
      //   });
      // } else if (this.loginForm.value.role === 'Agent') {
      //   this.agent.agentName = this.loginForm.value.agentName;
      //   this.api.setAgentName(this.agent.agentName);
      //   this.agent.agentPassword = this.loginForm.value.agentPassword;
      //   this.api.agentloginService(this.agent).subscribe((res: any) => {           
      //     if (res.data != null) {
      //       console.log(res.data.agentID);
      //       console.log(res.data);
      //       if (res.data.role == 'Agent') {
      //         this.api.agentIdSetter(res.data.agentID);
      //         localStorage.setItem("agentUserId",res.data.agentID);
      //         localStorage.setItem("Categorys",JSON.stringify(res.data.category))
      //         localStorage.setItem("agentName",JSON.stringify(res.data.agentName))
      //         console.log(res.data);
      //         this.api.agentCategorySetter(res.data.category);
      //         this.router.navigate(['agentView']);
      //       } else {
      //         this.api.setAgentName(this.agent.agentName);
      //         console.log(res.data);
      //         this.router.navigate(['supervisor']);
      //       }
      //     } else {
      //       alert(res.mssg);
      //     }
      //   });
      // }
    // }
    


    
       }

  


  


