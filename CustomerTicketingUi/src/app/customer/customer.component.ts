import { Component } from '@angular/core';
import { LoginService } from './../services/login.service';
import { Ticket } from './../model/ticket';
import { Category } from '../model/category';
import { FormControl, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent {
  ticketForm!: FormGroup;
  value!:boolean;
  categoryList!:Category[];
  constructor(private loginService: LoginService) {
    
    this.ticketForm = new FormGroup({
      subject: new FormControl(''),
      description: new FormControl(''),
      priority: new FormControl(''),
      categoryName: new FormControl(''),
      customerid: new FormControl(''),
  })
}
  ngOnInit(){
    this.loginService.getCategory().subscribe((response:Category[])=>{
      this.categoryList = response
            // console.log("yfjfjhfhjf",typeof response);
      
    })
  }

  onRaiseTicket(){
    this.value=true;
  }
  addedValue(){
    alert("Value Is Being Added");
  }
    saveTicketData(){
      console.log(this.ticketForm.value);   //here
       console.log(this.ticketForm.get('categoryName')?.value+"from here");     
      this.loginService.createTicket(this.ticketForm.value).subscribe((res)=>{
        let result=res      
        console.log(res); 
         
      },(error)=>{
        console.log("Error");
        
      })
     
      
    }

    
  }


