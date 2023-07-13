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
    
    this.agentAssiginedTickets=res;

    this.tickets=res;
    this.lengthTickets=this.tickets.length;
  })
}

   get(data:any){
    this.api.setagentChat(data);
    
    
    this.getTicket()
   console.log("Hai");
   
    this.route.navigate(['agentchat'])




    this.api.setCustomerTicket(data)
   }
}
