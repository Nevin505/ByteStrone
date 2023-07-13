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
  
  customerMessage!:string;

  constructor(private api:ApiService){

  }
  
  chatContents!:any;
  rating:number=0; 
 
  chat:Chat=new Chat();
  singleTicketDetaisl!:any;
  ngOnInit(){
    this. getTicketsDetails();

    this.api.getAgentCustomerChat().subscribe((res)=>{
      console.log(res);
      this.chatContents=res;
      
    })
  }

 
  sticket:SingleTicket=new SingleTicket();
  getTicketsDetails(){
    // getCustomerSingleTicketsDetails()
    this.api.getCustomerSingleTicketsDetails().subscribe((res:any)=>{
      console.log(res);
      this.sticket.ticketId=res.ticketId;
      this.sticket.description=res.description;
      this.sticket.priority=res.priority;
      this.sticket.status=res.status
      this.sticket.creation_Date=res.creation_Date;
      this.sticket.status=res.status;
      this.singleTicketDetaisl=res;
      
    })
  }

  AddComments(){
    this.chat.author='Customer';
    this.chat.content=this.customerMessage;
    this.api.setCustomerChat(this.chat).subscribe((res)=>{
      console.log(res);
      if(res==null){
        alert("Ticket Is Not Assigined To a Agent")
      }
      else{
        alert("Comment Is Being Added To The Particular Ticket")
      }
    })
  }
 
  message:String='';
  setRating(rating:number){
    this.api.setTicketRating(rating).subscribe((res)=>{
      console.log(res);
      this.message=res
      
    })

  }

}
