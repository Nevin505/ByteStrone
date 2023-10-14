import { Ticket } from './../model/ticket';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { Filter } from '../model/filter';
import { SearchCriteria } from '../model/search-criteria';
// import { UserTicketDetails } from '../model/user-ticket-details';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-customer-show-tickets',
  templateUrl: './customer-show-tickets.component.html',
  styleUrls: ['./customer-show-tickets.component.css']
})
export class CustomerShowTicketsComponent {
  constructor(private api: ApiService, private router: Router) {

  }

  data:Ticket[]=[];
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
            this.lengtharray = this.data.length;
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


  onDatachange(event: any) {
     if (this.selectedStatus=='Open' || this.selectedStatus=='Assigned'||this.selectedStatus=='Closed') {
     this.getFilter(this.selectedStatus);
     this.page=event;
   }
  else if(this.searchPageChange){
    this.searchTicket();
    this.page=event;
  }
  else {
    this.page = event;
    this.getCustomerTickets();
  }
  }

  searchticketSubject:String='';
  
  filterOpen: boolean = false;
  setfilter() {
      this.filterOpen = !this.filterOpen;
  }

  searchtickId:any=null;
  search:SearchCriteria=new SearchCriteria();
  searchPageChange:boolean=false;
 
  searchBackButton:boolean=false;
  searchTicket(){
  this.searchticketSubject=this.searchticketSubject.trim();
  console.log(this.searchtickId);
  console.log(this.searchticketSubject);
  
  
    if((this.searchtickId!=null&&this.searchtickId>=0)||this.searchticketSubject.length!=0){
      this.page=1;
      this.searchPageChange=true;
      this.searchBackButton=true;
        this.search.ticketId=this.searchtickId;
        this.search.subject=this.searchticketSubject;
        this.search.status= this.selectedStatus;      
        this.api.getCustomerSearchTickets(this.search).subscribe((res:any)=>{
          if(res.success){
            console.log(res);
            this.data=res.data;
            this.lengtharray=this.data.length; 
            this.message= `<p><strong>No results found for ${this.searchtickId===null?this.searchticketSubject:this.searchtickId}</strong></p><p>Please make sure your words are spelled correctly, or use fewer or different keywords.</p>`;
          }
          else{
            alert(res.mssg)
          }       
        }) 
    }
    else{
      alert("Please Enter Keyword")
    }

    }

    reloadPage(){
      this.searchPageChange=false;
      this.getFilter(this.selectedStatus);
    }

    showFullDescription(item: any) {
      item.showFullDescription = true;
    }
    
    showLessDescription(item: any) {
      item.showFullDescription = false;
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