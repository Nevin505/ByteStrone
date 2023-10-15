import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../services/api.service';
import { Ticket } from '../model/ticket';
import { Chat } from '../model/chat';
import { HttpErrorResponse } from '@angular/common/http';
import { Common } from '../model/common';

@Component({
  selector: 'app-agent-assigination',
  templateUrl: './agent-assigination.component.html',
  styleUrls: ['./agent-assigination.component.css']
})
export class AgentAssiginationComponent {

  ticketId!: any;
  ticketData!: any;
  agentData!: any;
  ticketDa:Ticket=new Ticket();

  constructor(private api: ApiService, private route: ActivatedRoute) {

  }

  ticketCategory: String='';

  ngOnInit() {

    this.api.getSpecificTicket().subscribe({
      next: (res: Common) => {
        if (res.success) {
          this.ticketDa = res.data;
          console.log(this.ticketDa);   
          this.ticketCategory = res.data.categoryId.category_name;
          console.log(this.ticketCategory);
          
        }
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error.mssg)
      }
    })

    this.api.getAgentList().subscribe({
      next: (res: Common) => {
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
  }

  // agentValue!: any;
  dat!: any;
  ad!: any;

  apival!: any;
  Assgination(agentNames: any) {
    this.dat = this.agentData.filter((agedata: any) => {
      return agedata.userName
        === agentNames
    })   
   const agentIDs = this.dat.map((agedata: any) => agedata.id);  

    this.ad = agentIDs[0];
    console.log(this.ad);
  
    this.api.assignTickets(this.ad).subscribe({
      next: (res: Common) => {
        if (res.success) {
          alert(res.mssg);
        }
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error.mssg)
      }
    })

  }

  closedTicketDetails: boolean = false;
  ticketAllChat: any;
  chat: Chat[] = [];
  showMore() {
    this.closedTicketDetails = true;
    this.api.getAllTicketComments().subscribe({
      next: (res: Common) => {
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
    
    // this.api.getSpecificTicket().subscribe((res: any) => {
    //   if (res.success) {
    //     this.ticketDa = res.data;
    //     this.ticketCategory = res.data.categoryId.category_name
    //   }
    //   else {
    //     alert(res.mssg);
    //   }
    // })
  
  // ticketsCategory: TicketsCategory = new TicketsCategory();


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
