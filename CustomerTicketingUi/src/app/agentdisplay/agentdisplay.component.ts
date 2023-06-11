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
  agentData!:any;
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

    // this.loginService.getParticularAgent().subscribe((res:any)=>{
    //   this.agentData=res;
    // })


    
  }

  getDetails(searchtickId:any) {
   this.all=true;
    this.loginService.getSearchTickets(searchtickId).subscribe(res => {
      this.response = res;
    })
  }

  onfilterHigh(): any[] {
    return this.data.sort((a: { priority: string; }, b: { priority: string; }) => {
      const priorityOrder = ['High', 'Medium', 'Low'];
      return priorityOrder.indexOf(a.priority) - priorityOrder.indexOf(b.priority);
    });
  }
  onfilterlow(): any[] {
    return this.data.sort((a: { priority: string; }, b: { priority: string; }) => {
      const priorityOrder = ['High', 'Medium', 'Low'];
      return priorityOrder.indexOf(b.priority) - priorityOrder.indexOf(a.priority);
    });
  }

  // getAgentList(){
  //   this.loginService. getParticularAgent().subscribe(res=>{
  //    this.agentData=res;
     
  //   })
  // }


}

