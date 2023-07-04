import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../services/api.service';
import { Ticket } from '../model/ticket';

@Component({
  selector: 'app-agent-assigination',
  templateUrl: './agent-assigination.component.html',
  styleUrls: ['./agent-assigination.component.css']
})
export class AgentAssiginationComponent {
  ticketId!: any;

  ticketData!:any;

  agentData!:any;

  ticketDa!:any;

  constructor( private api:ApiService,private route: ActivatedRoute) {
    
   }
   
  ngOnInit() {

    console.log(this.api.getparticularTicketId());
    
     this.api.getSpecificTicket().subscribe((res)=>{
      this.ticketDa=res;

     
      if(this.ticketDa.success)
      this.ticketData=this.ticketDa.data;
      console.log(res);
      
     })
     this.api.getAgentList().subscribe(res=>{

      console.log(res);
      this.agentData=res;

     })
  }
  agentValue!:any;


  dat!:any;
  ad!:any;



  apival!:any;
  Assginatin(agentNames:any){
    console.log(agentNames);
    console.log(this.agentData);

    this.dat=this.agentData.filter((agedata:any)=>{
      return JSON.stringify(agedata.agentName)
      ===JSON.stringify(agentNames)
     })

   const agentIDs = this.dat.map((agedata: any) => agedata.agentID);
    console.log(agentIDs[0]);

    
   this.ad=agentIDs[0];
   console.log(this.ad);
    
   this.api.assignTickets(this.ad).subscribe((res)=>{
    this.apival=res;
    console.log(this.apival);
   if(this.apival){
    alert(this.apival)
   }
   else{
    alert(this.apival)
   }
 
   });
  
  }


  
 
}
