import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Chat } from '../model/chat';
import { Ticket } from '../model/ticket';
import { Common } from '../model/common';
import { HttpErrorResponse } from '@angular/common/http';

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


  // this.api.addTicket(this.ticketForm.value).subscribe({
  //   next: (res: any) => {
  //     if (res.success) {
  //       alert(res.mssg)
  //       this.ticketForm.reset();
  //     }
  //   },
  //   error: (error: HttpErrorResponse) => {
  //     alert(error.error.mssg)
  //   }

  // });
  ngOnInit() {
    this.getTicketsDetails();

    this.api.getAgentCustomerChat().subscribe( {
      next: (res: any) => {
      if (res.success) {
        console.log(res);
        this.chatContents = res.data;
      }
      else {
        alert(res.mssg);
      }
    }, error: (error: HttpErrorResponse) => {
          alert(error.error.mssg)
        }

    })
  }

  getTicketsDetails() {
    // getCustomerSingleTicketsDetails()
    this.api.getCustomerSingleTicketsDetails().subscribe( {
      next:(res: Common)=>{
        if (res.success) {
          console.log(res);
          this.singleticket = res.data;
  
          this.singleTicketDetaisl = res.data;
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
      this.api.setCustomerChat(this.chat).subscribe({
        next:(res: any)=>{
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
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.mssg)
        }
      })
    }
    else {
      alert("Enter a Valid Message ")
    }

  }
  message: String = '';
  setRating(rating: number) {
    this.api.setTicketRating(rating).subscribe( {   
     next:(res: any)=>{
      if (res.success) {
        alert(res.mssg);
      }
      else {
        console.log(res.mssg);
        alert("Not here")

      }
     },
     error: (error: HttpErrorResponse) => {
      alert(error.error.mssg)
    }
    })
  }
}
