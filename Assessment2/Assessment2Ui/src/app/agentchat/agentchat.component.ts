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
    // this.ticketId=this.api.getagentChat();
    this.api.getTicketChat().subscribe((res)=>{
      if(res.success){
        this.ticketChatData=res.data;
        this.ticket.ticketId=this.ticketChatData.ticketId;
        this.ticket.subject=this.ticketChatData.subject;
        this.ticket.description=this.ticketChatData.description;
        this.ticket.creation_Date=this.ticketChatData.creation_Date;
        console.log(this.ticketChatData);
      }
      else{
        alert(res.mssg)
      }
      
      
    })
// getAgentCustomerChats

    // this.api.getagentChat()
      this.api.getAgentCustomerChats().subscribe((res:any)=>{
        if(res.success){
          this.chatContents=res.data;
        }
        else{
          alert(res.mssg)
        }
       
        
      })

  }
  values:number=0;
sendChat(){
  this.chat.content=this.chatContent;
  this.chat.author='Agent';

   this.values=this.chatContent.length;
  const newAgentMessage = {
    author: 'Agent',
    content: this.chatContent,
    timeStamp: new Date()
  };

  this.chatContents.push(newAgentMessage); 


  this.chatContent = '';
  this.api.addAgentChatMessage(this.chat).subscribe((res:any)=>{
    console.log(res);

  })
  
}

// window.location.reload()
  // sendChat(){
  // this.chat.content=this.chatContent;
  //   this.chat.author='Agent';
   
  //  this.api.getChatMessage(this.chat).subscribe((res:any)=>{
  //       if(res.success){
  //         if(res.data==null){
  //           alert(res.mssg);
  //         }
  //         else{
  //           alert(res.mssg)
  //         }
  //       }
  //       else{
  //         alert(res.mssg)
  //       }
  // }) 
  // //  window.location.reload();
   
  // }

  closeTickets(){
    this.api.closeTicketss().subscribe((res:any)=>{
      if(res.success){
          console.log(res.mssg);
          alert(res.mssg)
      }
      else{
        console.log(res.mssg);
      }
      
      // alert("")
    })
  }
}
