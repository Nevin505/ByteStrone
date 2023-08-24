import { Chat } from './../model/chat';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Ticket } from '../model/ticket';
import { HttpErrorResponse } from '@angular/common/http';


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
  ticketChatData!: any;
  ticketId!: number;
  status: String = '';
  chatContents!: any;


  ngOnInit() {
    // this.ticketId=this.api.getagentChat();
    this.api.getTicketChat().subscribe((res) => {
      if (res.success) {
        this.ticketChatData = res.data;
        this.ticket.ticketId = this.ticketChatData.ticketId;
        this.ticket.subject = this.ticketChatData.subject;
        this.ticket.description = this.ticketChatData.description;
        this.ticket.creation_Date = this.ticketChatData.creation_Date;
        this.status = this.ticketChatData.status;
        console.log(this.ticketChatData);
      }
      else {
        alert(res.mssg)
      }


    })
   
    this.api.getAgentCustomerChats().subscribe({
      next: (res: any) => {
        if (res.success) {
          this.chatContents = res.data;
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
    this.chat.content = this.chatContent;
    this.chat.author = 'Agent';
    
    if (this.status == 'Assigned') {
      if (this.chatContent.length == 0) {
        alert("Message Cannot Be Empty")
      }
      else {
        const newAgentMessage = {
          author: 'Agent',
          content: this.chatContent,
          timeStamp: new Date()
        };
        this.chatContents.push(newAgentMessage);
      }

    }
    else {
      alert("The Ticket is Being Closed")
      this.buttondisabled=true;
    }



    this.chatContent = '';
    this.api.addAgentChatMessage(this.chat).subscribe((res: any) => {
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

  closeTickets() {
    this.api.closeTicketss().subscribe((res: any) => {
      if (res.success) {
        console.log(res.mssg);
        alert(res.mssg)
        this.status = 'Closed';
      }
      else {
        console.log(res.mssg);
      }

      // alert("")
    })
  }
}
