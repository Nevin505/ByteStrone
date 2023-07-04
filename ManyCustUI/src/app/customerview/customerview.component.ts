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
  


 ticketForm = new FormGroup({
  subject: new FormControl('',Validators.required),
  description: new FormControl('',Validators.required),
  priority: new FormControl('',Validators.required),
  categoryName: new FormControl('',Validators.required),
})

 constructor(private api:ApiService){

 }

 ngOnInit(){
  this.api.getCategory().subscribe((response:Category[])=>{
    this.categoryList = response;   
  })
}

  onRaiseTicket(){
     this.raiseValue=true;
    //  console.log(this.value1);
     
  }
  
  addedValue(){
    this.ticket.subject=this.Subject;
    this.ticket.description=this.Description;
    this.ticket.priority=this.priority;
    this.ticket.categoryName=this.categoryName;
    this.api.addTicket(this.ticketForm.value).subscribe((res)=>{
    if(this.ticket.subject==null || this.ticket.description==null || this.ticket.priority==null){
      alert("Ticket is Not being raised")
    }
      
    });
    
  }
 


}
