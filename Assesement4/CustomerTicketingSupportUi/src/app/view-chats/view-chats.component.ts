import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Chat } from '../model/chat';
import { Ticket } from '../model/ticket';
import { Common } from '../model/common';

@Component({
  selector: 'app-view-chats',
  templateUrl: './view-chats.component.html',
  styleUrls: ['./view-chats.component.css']
})
export class ViewChatsComponent {

  customerMessage!: string;

  constructor(private api: ApiService) {

  }

  chatContents!: any;
  rating: number = 0;

  chat: Chat = new Chat();
  singleTicketDetaisl!: any;
  singleticket: Ticket = new Ticket();
  ngOnInit() {
    this.getTicketsDetails();

    this.api.getAgentCustomerChat().subscribe((res: any) => {
      if (res.success) {
        console.log(res);
        this.chatContents = res.data;

      }
      else {
        alert(res.mssg);
      }


    })
  }

  getTicketsDetails() {
    // getCustomerSingleTicketsDetails()
    this.api.getCustomerSingleTicketsDetails().subscribe((res: Common) => {
      if (res.success) {
        console.log(res);
        this.singleticket = res.data;

        this.singleTicketDetaisl = res.data;
      }
      else {
        alert(res.mssg)
      }


    })
  }

  AddComments() {
    this.customerMessage = this.customerMessage.trim();
    if (this.customerMessage.length != 0) {
      this.chat.author = 'Customer';
      this.chat.content = this.customerMessage;
      const newAgentMessage = {
        author: 'Customer',
        content: this.customerMessage,
        timeStamp: new Date()
      };
      this.chatContents.push(newAgentMessage);
      this.api.setCustomerChat(this.chat).subscribe((res: any) => {
        if (res.success) {
          if (res.data == null) {
            alert(res.mssg)
          }
          else {
            alert(res.mssg);
            this.customerMessage = '';
          }
        }
        else {
          alert(res.mssg)
        }

      })
    }
    else {
      alert("Enter a Valid Message ")
    }

  }
  message: String = '';
  setRating(rating: number) {
    this.api.setTicketRating(rating).subscribe((res: any) => {
      console.log(res);

      if (res.success) {
        alert(res.mssg);
      }
      else {
        console.log(res.mssg);
        alert("Not here")

      }
      // console.log(res);
      // this.message=res

    })

  }
}


        // this.singleticket.ticketId = res.data.ticketId;
        // this.singleticket.subject = res.data.subject;
        // this.singleticket.description = res.data.description;
        // this.singleticket.priority = res.data.priority;
        // this.singleticket.status = res.data.status
        // this.singleticket.creation_Date = res.data.creation_Date;
        // this.singleticket.status = res.data.status;
