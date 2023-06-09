import { Category } from './../model/category';
import { Component } from '@angular/core';
import { Ticket } from '../model/ticket';
import { LoginService } from '../services/login.service';
import { CustomerLoginComponent } from '../customer-login/customer-login.component';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-agentdisplay',
  templateUrl: './agentdisplay.component.html',
  styleUrls: ['./agentdisplay.component.css']
})
export class AgentdisplayComponent {

  res: any;
  agentid: any;
  searchtickId!: any;
  all: boolean=false;
  constructor(private loginService: LoginService, private route: ActivatedRoute) {

  }
  data!: any;
  // tickets:Ticket[]=[]
  response!: any;
  ngOnInit() {

    this.agentid = this.route.snapshot.paramMap.get('queryParams');
    console.log(this.agentid);

    this.loginService.getTicket(this.agentid).subscribe(res => {
      this.data = res;
      console.log(this.data);

    })
  }
  //       for(let i=0;i<this.data.length;i++){
  //         let ticket=new Ticket();
  //         ticket.customerid=this.data.customerid;
  //         ticket.description=this.data.description;
  //         ticket.status=this.data.status;
  //         // ticket.category.categoryId=this.data.category.categoryId;
  //         ticket.priority=this.data.priority;
  //         // ticket.customer.customerId=this.data.customer.customerId;
  //         console.log(ticket);
  //         // this.tickets.push(ticket);
  //        }
  //  })


  onfilterHigh() {
    this.data.sort((a: { priority: string; }, b: { priority: any; }) => a.priority.localeCompare(b.priority));

  }
  onfilterlow() {
    this.data.sort((a: { priority: string; }, b: { priority: any; }) => b.priority.localeCompare(a.priority));

  }
  getDetails(searchtickId:any) {
   this.all=true;
    this.loginService.getSearchTickets(searchtickId).subscribe(res => {
      this.response = res;
    })
  }
}
