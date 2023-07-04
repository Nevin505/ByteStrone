import { Component } from '@angular/core';
import { Customerlogin } from '../model/customerlogin';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { Agentlogin } from '../model/agentlogin';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  UserName!:string;
  Password!:string;
  customerId:number=0;
  customerlogin:Customerlogin=new Customerlogin();
  Role!:string;

  agent:Agentlogin=new Agentlogin();
  agentName!:string;
  agentPassword!:string;
  
  constructor(private api:ApiService,private router:Router){
  }
  
  

  loginAgent(){
    console.log(this.Role);
    if(this.Role==='Customer'){
      this.customerlogin.username=this.UserName
      this.customerlogin.userpassword=this.Password;     
      this.api.validateCustomer(this.customerlogin).subscribe(res=>{ 
        console.log(res)
          if(res.success){
            this.customerId=res.data.customerid;
            this.api.customerIdSetter(res.data.customerid);
            this.router.navigate(['customerview'])
          }
          else{
            alert(res.mssg);
          }

           
      })  
    }
    else if(this.Role==='Agent'){
   
        this.agent.agentName=this.UserName;
        this.agent.agentPassword=this.Password;
         this.api.agentloginService(this.agent).subscribe((res:any)=>{           
           if(res.data!=null){
             console.log(res.data.agentID);
             console.log(res.data);
             
             if(res.data.role=='Agent'){
             this.api.agentIdSetter(res.data.agentID);
             console.log(res.data);
            
             this.api.agentCategorySetter(res.data.category);
             this.router.navigate(['agentView']);
             }
             else{
              // this.api.agentCategorySetter(res.data.category);
              console.log(res.data);
              
              this.router.navigate(['supervisor']);
             }
             
             
           }
           else{
               alert(res.mssg);
           }
        
         })
     
       }

    }
    }
  


