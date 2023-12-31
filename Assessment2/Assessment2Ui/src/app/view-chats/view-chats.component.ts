import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Chat } from '../model/chat';
import { SingleTicket } from '../model/single-ticket';

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


  sticket: SingleTicket = new SingleTicket();
  getTicketsDetails() {
    // getCustomerSingleTicketsDetails()
    this.api.getCustomerSingleTicketsDetails().subscribe((res: any) => {
      if (res.success) {
        console.log(res);
        this.sticket.ticketId = res.data.ticketId;
        this.sticket.subject = res.data.subject;
        this.sticket.description = res.data.description;
        this.sticket.priority = res.data.priority;
        this.sticket.status = res.data.status
        this.sticket.creation_Date = res.data.creation_Date;
        this.sticket.status = res.data.status;
        this.singleTicketDetaisl = res.data;
      }
      else {
        alert(res.mssg)
      }


    })
  }

  AddComments() {
    if (this.isCommentEmpty()) {
      this.chat.author = 'Customer';
      this.chat.content = this.customerMessage;
      this.api.setCustomerChat(this.chat).subscribe((res: any) => {
        console.log(res);
        if (res.success) {
          if (res.data == null) {
            alert(res.mssg)
          }
          else {
            alert(res.mssg)
          }
        }
        else {
          alert(res.mssg)
        }

      })
      window.location.reload();
    }
    else {
      alert("Enter Something")
    }

  }
  isCommentEmpty() {
    return this.customerMessage.trim().length > 1;
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
