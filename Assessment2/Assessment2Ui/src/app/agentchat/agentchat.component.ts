import { Chat } from './../model/chat';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Ticket } from '../model/ticket';


@Component({
  selector: 'app-agentchat',
  templateUrl: './agentchat.component.html',
  styleUrls: ['./agentchat.component.css']
})
export class AgentchatComponent {
  chatContent!:string;
  constructor(private api:ApiService){

  }


  chat:Chat=new Chat();
  
  ticket:Ticket=new Ticket();
  ticketChatData!:any;
  ticketId!:number;
   
  chatContents!:any;
  

  ngOnInit(){
    this.ticketId=this.api.getagentChat();
    this.api.getTicketChat().subscribe((res)=>{
      this.ticketChatData=res;
      this.ticket.ticketId=this.ticketChatData.ticketId;
      this.ticket.subject=this.ticketChatData.subject;
      this.ticket.description=this.ticketChatData.description;
      this.ticket.creation_Date=this.ticketChatData.creation_Date;
      console.log(this.ticketChatData);
      
    })


    this.api.getagentChat()
      this.api.getAgentCustomerChat().subscribe(res=>{
        this.chatContents=res;
        
      })

  }

  sendChat(){
  this.chat.content=this.chatContent;
    this.chat.author='Agent';
   
   this.api.getChatMessage(this.chat).subscribe(RES=>{
    console.log(RES); 
  }) 
   
  }

  closeTickets(){
    this.api.closeTicketss().subscribe((res)=>{
      console.log(res);
      
    })
  }
}
