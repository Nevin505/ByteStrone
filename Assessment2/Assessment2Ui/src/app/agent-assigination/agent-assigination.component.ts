import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../services/api.service';
import { Ticket } from '../model/ticket';
import { TicketsCategory } from '../model/tickets-category';
import { Ticketassigination } from '../model/ticketassigination';
import { flatMap } from 'rxjs';
import { Chat } from '../model/chat';

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

  ngOnInit() {


    this.api.getSpecificTicket().subscribe((res: any) => {
      this.ticketDa = res;

      console.log(this.ticketDa);
    })
    console.log(this.ticketDa);

    this.api.getAgentList().subscribe(res => {

      console.log(res);
      this.agentData = res;

    })


  }
  agentValue!: any;
  dat!: any;
  ad!: any;

  apival!: any;
  Assgination(agentNames: any) {
    console.log(agentNames);
    console.log(this.agentData);

    this.dat = this.agentData.filter((agedata: any) => {
      return JSON.stringify(agedata.agentName)
        === JSON.stringify(agentNames)
    })

    const agentIDs = this.dat.map((agedata: any) => agedata.agentID);
    console.log(agentIDs[0]);


    this.ad = agentIDs[0];
    console.log(this.ad);

    this.api.assignTickets(this.ad).subscribe((res) => {
      this.apival = res;
      console.log(this.apival);
      if (this.apival) {
        alert(this.apival)
      }
      else {
        alert(this.apival)
      }

    });

  }

  closedTicketDetails: boolean = false;
  ticketAllChat: any;
  chat: Chat[] =[];
  showMore() {
    this.closedTicketDetails = true;
    this.api.getAllTicketComments().subscribe((res: any) => {
      this.chat = res;
      console.log(this.chat);

    });


  }




}
