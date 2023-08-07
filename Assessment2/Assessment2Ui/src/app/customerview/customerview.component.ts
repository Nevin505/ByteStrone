import { Route, Router } from '@angular/router';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Category } from '../model/category';
import { Ticket } from '../model/ticket';
import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-customerview',
  templateUrl: './customerview.component.html',
  styleUrls: ['./customerview.component.css']
})
export class CustomerviewComponent {
  value1:number=0;
 raiseValue:boolean=false;
 categoryList!:Category[];
 Subject!:string;
 Description!:string;
 priority!:string;

 categoryName!:string;
 
 response:any;
 
 cateName!:string;
 ticket:Ticket=new Ticket();
 category:Category=new Category();
 details:any;
  


 ticketForm = new FormGroup({
  subject: new FormControl('',Validators.required),
  description: new FormControl('',Validators.required),
  priority: new FormControl('',Validators.required),
  categoryName: new FormControl('',Validators.required),
})

 constructor(private api:ApiService, private route:Router){

 }
 agentName:String='';
 time!:Date;
 ngOnInit(){
  this.api.getCategory().subscribe((res:any)=>{
    if(res.success){
      this.categoryList = res.data;   
    }else{
      alert(res.mssg)
    }
    
  })
  this.agentName=this.api.getCustomerName();
  this.time=new Date();

  this.agentName=localStorage.getItem("customerName") as String;
}

  onRaiseTicket(){
     this.raiseValue=true;   
  }
  removeDate(){
    sessionStorage.clear();
    
    this.route.navigate(['/landingPage'])

    // localStorage.clear();
    // if (localStorage.getItem("customerUserId") !== null) {
    //   localStorage.removeItem("customerUserId");
    // }
    // // } 
    
    // if (localStorage.getItem("customerViewTicketId") !== null) {
    //   localStorage.removeItem("customerViewTicketId");
    // }
   
    // if(localStorage.getItem("customerName")!=null){
    //   localStorage.removeItem("customerName");
    // }
    
  }
  addedValue(){
    this.api.addTicket(this.ticketForm.value).subscribe((res:any)=>{
      this.details=res
      console.log(this.details);
      
      if(res.success){
       alert(res.mssg)
      }else{
        alert(res.mssg)
      }

      
    });
    }
    
    

   

  }
  
 


