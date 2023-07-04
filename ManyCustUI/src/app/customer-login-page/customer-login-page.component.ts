import { Component } from '@angular/core';

import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../services/api.service';
import { Customerlogin } from '../model/customerlogin';

@Component({
  selector: 'app-customer-login-page',
  templateUrl: './customer-login-page.component.html',
  styleUrls: ['./customer-login-page.component.css']
})
export class CustomerLoginPageComponent {


  
  userName:string='';
  userpassword:string='';  
  customerId:number=0;

  customerlogin:Customerlogin=new Customerlogin();

  constructor(private api:ApiService,private router:Router){
  }

  customerLogin(){
    this.customerlogin.username=this.userName
    this.customerlogin.userpassword=this.userpassword;     
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

        //  if(res!=null){
        //   this.customerId=res.customerid;
        //   this.api.customerIdSetter(res.customerid);
        //   this.router.navigate(['customerview'])
        //    console.log(this.customerId+"From here");  
        //  }
        //  else{
        //   alert("Invalid Crenditials")
        //  }
         
    })  
  }
 }



