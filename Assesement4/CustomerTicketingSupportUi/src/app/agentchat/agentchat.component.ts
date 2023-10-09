import { Chat } from './../model/chat';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Ticket } from '../model/ticket';
import { HttpErrorResponse } from '@angular/common/http';
import { Common } from '../model/common';


@Component({
  selector: 'app-agentchat',
  templateUrl: './agentchat.component.html',
  styleUrls: ['./agentchat.component.css']
})
export class AgentchatComponent {
  chatContent: String = '';
  constructor(private api: ApiService) {

  }


  chat: Chat = new Chat();

  ticket: Ticket = new Ticket();
  ticketChatData!:Ticket;
  ticketId!: number;
  status: String = '';
  chatContents!: any;


  // 
  previouslength:number=0

  ngOnInit() {
    // this.ticketId=this.api.getagentChat();
    this.api.getTicketChat().subscribe((res) => {
      if (res.success) {
        this.ticket = res.data;
        this.status=this.ticket.status;
        console.log(this.ticket);
      }
      else {
        alert(res.mssg)
      }
    })
   
    this.api.getAgentCustomerChats().subscribe({
      next: (res: any) => {
        if (res.success) {
          this.chatContents = res.data;
          console.log(this.chatContents.length);        
        }
        else {
          alert(res.mssg)
        }
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error.mssg)
      }
    })

  }
  values: number = 0;
  buttondisabled:boolean=false;
  sendChat() { 
   
    this.chat.content = this.chatContent.trim();
    this.chat.author = 'Agent';
    if (this.status == 'Assigned') {
      if (this.chatContent.trim().length == 0) {
        alert("Message Cannot Be Empty")
      }
      else {
        const newAgentMessage = {
          author: 'Agent',
          content: this.chatContent,
          timeStamp: new Date()
        };
        this.chatContents.push(newAgentMessage);
        this.api.addAgentChatMessage(this.chat).subscribe((res: any) => {
           alert(res.mssg);
          this.chatContent='';
    
        })
      }
      // alert("hhh")
    }
    else {
      alert("The Ticket is Being Closed")
    
    }
  }

  closeTickets() {
    this.api.closeTicketss().subscribe( {
      next: (res: Common) => {
        if (res.success) {
            console.log(res.mssg);
            alert(res.mssg)
            this.status = 'Closed';
            // this.buttondisabled=true;
          }
          else {
            console.log(res.mssg);
          }
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error.mssg)
      }
    })
  }
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
