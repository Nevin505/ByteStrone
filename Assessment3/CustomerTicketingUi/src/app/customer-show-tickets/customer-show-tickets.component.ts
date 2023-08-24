import { Ticket } from './../model/ticket';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { Filter } from '../model/filter';
import { SearchCriteria } from '../model/search-criteria';
import { UserTicketDetails } from '../model/user-ticket-details';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-customer-show-tickets',
  templateUrl: './customer-show-tickets.component.html',
  styleUrls: ['./customer-show-tickets.component.css']
})
export class CustomerShowTicketsComponent {
  constructor(private api: ApiService, private router: Router) {

  }

  data:UserTicketDetails[]=[];
  // data!: any;
  page: number = 1;
  count: number = 0;
  tablesize: number = 10;
  agentName!: string;

  lengtharray!: number;
  ticket: Ticket[] = [];

  ngOnInit() {
    this.getCustomerTickets();


    
  }
  getCustomerTickets() {
    this.api.getCustomerTicketDetails().subscribe((res: any) => {
      this.data=res.data;
      console.log( this.data[0].ticketId);  
      this.lengtharray = this.data.length;
      console.log(res);
      console.log(this.data);
        
    })
  }
 chatContentLength:any=0;


  getMessageData(item: any) {
    this.router.navigate(['customerchat']);
    sessionStorage.setItem("customerViewTicketId",item);
  }
  message:String='';
  selectedStatus:String='';
  filter: Filter = new Filter();
   flag:boolean=false;
   Status:String='';
  //
  assignedTicketId:any;  
  // 
  getFilter(status: String) {
    this.page=1;
    this.selectedStatus=status
    this.filter.status =this.selectedStatus;
    if (this.selectedStatus === 'Open'|| this.selectedStatus === 'Assigned' || this.selectedStatus === 'Closed') {   
      this.flag=true;
      console.log("Here");
      
      this.message="There Exist No"+this.selectedStatus+" ";
      this.api.getCustomerFilterTickets(this.filter).subscribe( {
        next:(res:any)=>{
          if(res.success){
            this.data = res.data;
            // if (this.selectedStatus === 'Assigned') {
            //   this.assignedTicketId = this.data.map(item => item.ticketId);
            //   console.log(this.assignedTicketId);
            // }
            // console.log(this.assignedTicketId);
            
            this.lengtharray = this.data.length;
            console.log(this.data);
          }
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.mssg)
        }
      })
    }
    else { 
      this.flag=false
      this.selectedStatus=''
      this.getCustomerTickets();
    }
  }


  // getNotifcations(){
  //   for (let index = 0; index < this.assignedTicketId.length; index++) {
  //     const element = this.assignedTicketId[index];
  //     this.api.getAgentCustomerChat().subscribe((res:any)=>{
  //       this.chatContentLength=res.data;
  //     })
  //   }
  // //  this.api.getAgentCustomerChat().subscribe((res:any)=>{
  // //   this.chatContentLength=res.data;

  
  // }

  onDatachange(event: any) {
    
     if (this.selectedStatus=='Open') {
     this.getFilter(this.selectedStatus);
     this.page=event;
  }
  else if(this.selectedStatus=='Assigned'){
  this.getFilter(this.selectedStatus);
  this.page=event;
  } 
  else if(this.searchPageChange){
    this.searchTicket();
    this.page=event;
  }
  else if(this.selectedStatus=='Closed'){
 
  this.getFilter(this.selectedStatus);
  this.page = event;
  }
  else {
    this.page = event;
    this.getCustomerTickets();
  }
  }


  filterOpen: boolean = false;
  setfilter() {
    if (this.filterOpen === true) {
      this.filterOpen = false
    }
    else {
      this.filterOpen = true;
    }

  }
  searchtickId:any;

  search:SearchCriteria=new SearchCriteria();
  
  searchPageChange:boolean=false;

  searchTicket(){
    this.page=1;
    this.searchPageChange=true;
      console.log("Value of searchtickId:", this.searchtickId);
      console.log("Type of searchtickId:", typeof this.searchtickId);
      
      this.search.subject=this.searchtickId;
      this.search.status= this.selectedStatus;
      console.log(this.search);
      
      this.api.getCustomerSearchTickets(this.search).subscribe((res:any)=>{
        if(res.success){
          console.log(res);
          this.data=res.data;
          this.lengtharray=this.data.length; 
          this.message="There Exist No Tickets"
          // this.messageFilter="No Tickets Are There"
        }
        else{
          alert(res.mssg)
        }
        
      })
  
      // this.searchButton=true;
  
    }


   
    

  }

// }


    // else if (status === 'Assigned') {
    //   // this.page=1
    //   this.filter.status = 'Assigned'
    //   this.Status=status

    //   this.selectedStatus=status

    //   this.flag=true;
      
    //   this.api.getCustomerFilterTickets(this.filter).subscribe( {
    //     next:(res:any)=>{
    //       if(res.success){
    //         this.data = res.data
    //         this.lengtharray = this.data.length;
    //         console.log(this.data);
    //       }
    //     },
    //     error: (error: HttpErrorResponse) => {
    //       alert(error.error.mssg)
    //     }
    //   })
    // }
    // else if (status == 'Closed') {
    //   // this.page=1
    //   this.filter.status = 'Closed'
    //   this.Status=status
    
    //   this.selectedStatus=status
      
    //   this.flag=true;
    //   this.api.getCustomerFilterTickets(this.filter).subscribe( {
    //     next:(res:any)=>{
    //       if(res.success){
    //         this.data = res.data
    //         this.lengtharray = this.data.length;
    //         console.log(this.data);
    //       }
    //     },
    //     error: (error: HttpErrorResponse) => {
    //       alert(error.error.mssg)
    //     }
    //   })

    // }
