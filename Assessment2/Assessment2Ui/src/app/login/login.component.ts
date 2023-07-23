import { Category } from './../model/category';
import { Component } from '@angular/core';
import { Customerlogin } from '../model/customerlogin';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { Agentlogin } from '../model/agentlogin';
import { FormGroup ,FormControl, Validators} from '@angular/forms';

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
      agentPassword: new FormControl('', [Validators.required]),
      role: new FormControl('', [Validators.required])
    });
    
  }
  
  

  // loginAgent(){
  //   console.log(this.Role);
  //   if(this.Role==='Customer'){
  //     this.customerlogin.username=this.UserName
  //     this.customerlogin.userpassword=this.Password;     
  //     this.api.validateCustomer(this.customerlogin).subscribe(res=>{ 
  //       console.log(res)
  //         if(res.success){
  //           this.customerId=res.data.customerid;
  //           this.api.customerIdSetter(res.data.customerid);
  //           this.router.navigate(['customerview'])
  //         }
  //         else{
  //           alert(res.mssg);
  //         }

           
  //     })  
  //   }
  //   else if(this.Role==='Agent'){
   
  //       this.agent.agentName=this.UserName;
  //       this.agent.agentPassword=this.Password;
  //        this.api.agentloginService(this.agent).subscribe((res:any)=>{           
  //          if(res.data!=null){
  //            console.log(res.data.agentID);
  //            console.log(res.data);
             
  //            if(res.data.role=='Agent'){
  //            this.api.agentIdSetter(res.data.agentID);
  //            console.log(res.data);
            
  //            this.api.agentCategorySetter(res.data.category);
  //            this.router.navigate(['agentView']);
  //            }
  //            else{
  //             // this.api.agentCategorySetter(res.data.category);
  //             console.log(res.data);
              
  //             this.router.navigate(['supervisor']);
  //            }
             
             
  //          }
  //          else{
  //              alert(res.mssg);
  //          }
        
  //        })
     
  //      }

  //   }




    loginAgent() {
      console.log(this.loginForm.value.role);
      if (this.loginForm.value.role === 'Customer') {
        this.customerlogin.username = this.loginForm.value.agentName;
        this.customerlogin.userpassword = this.loginForm.value.agentPassword;
        this.api.setCustomerName( this.customerlogin.username);
        this.api.validateCustomer(this.customerlogin).subscribe(res => { 
          console.log(res);
          if (res.success) {
            this.customerId = res.data.customerid;
            localStorage.setItem("customerUserId",res.data.customerid);
            this.api.customerIdSetter(res.data.customerid);
            this.router.navigate(['customerview']);
          } else {
            alert(res.mssg);
          }
        });
      } else if (this.loginForm.value.role === 'Agent') {
        this.agent.agentName = this.loginForm.value.agentName;
        this.api.setAgentName(this.agent.agentName);
        this.agent.agentPassword = this.loginForm.value.agentPassword;
        this.api.agentloginService(this.agent).subscribe((res: any) => {           
          if (res.data != null) {
            console.log(res.data.agentID);
            console.log(res.data);
            if (res.data.role == 'Agent') {
              this.api.agentIdSetter(res.data.agentID);
              localStorage.setItem("agentUserId",res.data.agentID);
              localStorage.setItem("Categorys",JSON.stringify(res.data.category))
              localStorage.setItem("agentName",JSON.stringify(res.data.agentName))
              console.log(res.data);
              this.api.agentCategorySetter(res.data.category);
              this.router.navigate(['agentView']);
            } else {
              this.api.setAgentName(this.agent.agentName);
              console.log(res.data);
              this.router.navigate(['supervisor']);
            }
          } else {
            alert(res.mssg);
          }
        });
      }
    }
    


    
       }

  


  


