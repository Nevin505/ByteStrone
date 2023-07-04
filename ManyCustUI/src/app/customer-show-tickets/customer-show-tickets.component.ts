import { Ticket } from './../model/ticket';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-show-tickets',
  templateUrl: './customer-show-tickets.component.html',
  styleUrls: ['./customer-show-tickets.component.css']
})
export class CustomerShowTicketsComponent {
  constructor(private api:ApiService,private router:Router){

  }
 data!:any;
 page:number=1;
 count:number=0;
 tablesize:number=10;
 agentName!:string;

 lengtharray!:number;
 ticket:Ticket[]=[];

  ngOnInit(){
    this.getCustomerTickets();
  }
  getCustomerTickets(){
  this.api.getCustomerTicketDetails().subscribe(res=>{
    this.data=res;
     this.lengtharray=this.data.length;
    console.log(res); 
    console.log(this.data);
        
  })
}
  onDatachange(event:any){
      this.page=event;
      this.getCustomerTickets()
    }
    getMessageData(item:any){
      this.router.navigate(['customerchat']);
      this.api.setCustomerTicket(item);
      console.log(this.api. getCustomerTicketInfo());
      
    }
    
  }
