import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../services/api.service';
import { Ticket } from '../model/ticket';
import { TicketsCategory } from '../model/tickets-category';
import { Ticketassigination } from '../model/ticketassigination';
import {  filter } from 'rxjs';
import { Chat } from '../model/chat';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-agent-assigination',
  templateUrl: './agent-assigination.component.html',
  styleUrls: ['./agent-assigination.component.css']
})
export class AgentAssiginationComponent {
  ticketId!: any;
  ticketData!: any;
  agentData!: any;
  ticketDa: Ticketassigination = new Ticketassigination();

  constructor(private api: ApiService, private route: ActivatedRoute) {

  }

  ticketsCategory: TicketsCategory = new TicketsCategory();


  ticketCategory!: any;
  ngOnInit() {

    this.api.getSpecificTicket().subscribe({
      next: (res: any) => {
        if (res.success) {
          this.ticketDa = res.data;
          this.ticketCategory = res.data.categoryId.category_name
        }
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error.mssg)
      }
    })
    
    // this.api.getSpecificTicket().subscribe((res: any) => {
    //   if (res.success) {
    //     this.ticketDa = res.data;
    //     this.ticketCategory = res.data.categoryId.category_name
    //   }
    //   else {
    //     alert(res.mssg);
    //   }
    // })

    this.api.getAgentList().subscribe({
      next: (res: any) => {
        if (res.success) {
          this.agentData = res.data.filter((value: any) => {
            return value.category.some((name: any) => name.category_name == this.ticketCategory)
          });
        }
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error.mssg)
      }
    })
    

    // this.api.getAgentList().subscribe((res: any) => {
    //   console.log(res);
    //   if (res.success) {
    //     this.agentData = res.data.filter((value: any) => {
    //       return value.category.some((name: any) => name.category_name == this.ticketCategory)
    //     });
    //     console.log(this.agentData);
    //   }
    //   else {
    //     alert(res.mssg);
    //   }
    // })


  }
  agentValue!: any;
  dat!: any;
  ad!: any;

  apival!: any;
  Assgination(agentNames: any) {
    this.dat = this.agentData.filter((agedata: any) => {
      return JSON.stringify(agedata.userName)
        === JSON.stringify(agentNames)
    })
    const agentIDs = this.dat.map((agedata: any) => agedata.id);
    console.log(agentIDs[0]);


    this.ad = agentIDs[0];
    console.log(this.ad);
  
    this.api.assignTickets(this.ad).subscribe({
      next: (res: any) => {
        if (res.success) {
          alert(res.mssg);
        }
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error.mssg)
      }
    })


    // this.api.assignTickets(this.ad).subscribe((res: any) => {
    //   if (res.success) {
    //     alert(res.mssg);
    //   }
    //   else {
    //     alert(res.mssg);
    //   }
      // //
      // this.apival = res;
      // console.log(this.apival);
      // if (this.apival) {
      //   alert(this.apival)
      // }
      // else {
      //   alert(this.apival)
      // }
      // //
    // });

  }

  closedTicketDetails: boolean = false;
  ticketAllChat: any;
  chat: Chat[] = [];
  showMore() {
    this.closedTicketDetails = true;
    this.api.getAllTicketComments().subscribe({
      next: (res: any) => {
        if (res.success) {
          this.chat = res.data;
          console.log(this.chat);
        }
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error.mssg)
      }
    })

    // this.api.getAllTicketComments().subscribe((res: any) => {
    //   this.chat = res.data;
    //   console.log(this.chat);

    // });
  }
}
