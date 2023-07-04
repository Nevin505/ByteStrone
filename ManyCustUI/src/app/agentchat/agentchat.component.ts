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

//   {mssg: 'The Details Of The Ticket With Oarticular Id is', data: {…}, success: true}
// data
// : 
// {ticketId: 252, subject: 'ety', description: 'et', priority: 'High', status: 'Assigined', …}
// mssg
// : 
// "The Details Of The Ticket With Oarticular Id is"
// success
// : 
// true

  chat:Chat=new Chat();
  
  ticket:Ticket=new Ticket();
  ticketChatData!:any;
  ticketId!:number;
   
  chatContents!:any;
  

  ngOnInit(){
    this.ticketId=this.api.getagentChat();
    this.api.getTicketChat().subscribe((res:any)=>{
      
     if(res.success){
      this.ticketChatData=res;
      this.ticket.ticketId=this.ticketChatData.data.ticketId;
      this.ticket.subject=this.ticketChatData.data.subject;
      this.ticket.description=this.ticketChatData.data.description;
     }
      console.log(this.ticketChatData);
      
    })


    this.api.getagentChat()
      this.api.getAgentCustomerChat().subscribe(res=>{
        this.chatContents=res;
        
      })

  }
  // getChatMessage(chat:Chat){
  //   return this.http.post(`http://localhost:8080/comment/addcomments/${this.agentId}/${this.agentcha}`,chat)
  // }
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
