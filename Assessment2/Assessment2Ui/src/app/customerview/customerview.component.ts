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

 constructor(private api:ApiService){

 }
 agentName:String='';
 ngOnInit(){
  this.api.getCategory().subscribe((response:Category[])=>{
    this.categoryList = response;   
  })
  this.agentName=this.api.getCustomerName();
}

  onRaiseTicket(){
     this.raiseValue=true;
    //  console.log(this.value1);
     
  }
  
  addedValue(){
    // this.ticket.subject=this.Subject;
    // this.ticket.description=this.Description;
    // this.ticket.priority=this.priority;
    // console.log(this.ticket.subject);
    
    // this.ticket.categoryName=this.categoryName;
    this.api.addTicket(this.ticketForm.value).subscribe((res)=>{
      this.details=res
      console.log(this.details);
      
    if(this.details.subject==null || this.details.description==null || this.details.priority==null){
      alert("Ticket is Not being raised")
    }
    else{
      alert("Ticket Is Raised")
    }
      
    });
    
  }
  
 


}
