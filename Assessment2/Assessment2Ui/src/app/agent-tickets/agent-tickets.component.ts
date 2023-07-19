import { Ticket } from './../model/ticket';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Route, Router } from '@angular/router';
import { TicketsCategory } from '../model/tickets-category';

@Component({
  selector: 'app-agent-tickets',
  templateUrl: './agent-tickets.component.html',
  styleUrls: ['./agent-tickets.component.css']
})
export class AgentTicketsComponent {
  
  agentAssiginedTickets!:any;

   constructor(private api:ApiService,private route:Router){

   }
   ngOnInit(){
    this.getTicket();
   }
   tickets:TicketsCategory[]=[];
   lengthTickets:number=0
getTicket(){
  this.api.getAssiginedTickets().subscribe((res:any)=>{
    console.log(res);
    if(res.success){
      this.agentAssiginedTickets=res.data;
      this.tickets=res.data;
      this.lengthTickets=this.tickets.length;
    }
    else{
      alert("res.mssg")
    }
    
  })
}

   get(data:any){
    this.api.setagentChat(data);
    localStorage.setItem("agentChatTicketId",data);
    console.log(this.api.getagentChat());
    
    this.getTicket()
   console.log("Hai");
   
    this.route.navigate(['agentchat'])

    this.api.setCustomerTicket(data)
    console.log("From Customer");
    console.log(this.api.getCustomerTicketInfo());
    
    
    localStorage.setItem("customerTicketId",data);
   }
}
