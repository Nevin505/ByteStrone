import { Ticket } from './../model/ticket';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import {  Router } from '@angular/router';
// import { TicketsCategory } from '../model/tickets-category';
import { HttpErrorResponse } from '@angular/common/http';
import { Common } from '../model/common';

@Component({
  selector: 'app-agent-tickets',
  templateUrl: './agent-tickets.component.html',
  styleUrls: ['./agent-tickets.component.css']
})
export class AgentTicketsComponent {

  constructor(private api: ApiService, private route: Router) {

  }
  ngOnInit() {
    this.getTicket();
  }

  agentAssiginedTickets: Ticket[] = [];
  lengthTickets: number = 0
  // ticketCount:number=0;
  getTicket() {

    this.api.getAssiginedTickets().subscribe({
      next: (res: Common) => {
        if (res.success) {

          this.agentAssiginedTickets = res.data;
          // this.ticketCount=this.tickets.length;
          console.log(this.agentAssiginedTickets);
          
          this.lengthTickets = this.agentAssiginedTickets.length;
          this.api.setTicketCount(this.lengthTickets);
   
          console.log(this.lengthTickets);
          // console.log(this.ticketCount);
          
        }
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
        alert(error.error.mssg)
    
        
      }
    })
    
  }
 
  get(data: any) {
    sessionStorage.setItem("agentChatTicketId", data);
    this.getTicket();
    this.route.navigate(['agentchat'])
    sessionStorage.setItem("customerTicketId", data);
  }

  page: number = 1;
  count: number = 0;
  tablesize: number = 9;

  onData(event:any){
    this.getTicket();
    this.page=event;
  }
}
