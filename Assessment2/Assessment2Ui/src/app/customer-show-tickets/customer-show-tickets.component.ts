import { Ticket } from './../model/ticket';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { Filter } from '../model/filter';
import { SearchCriteria } from '../model/search-criteria';

@Component({
  selector: 'app-customer-show-tickets',
  templateUrl: './customer-show-tickets.component.html',
  styleUrls: ['./customer-show-tickets.component.css']
})
export class CustomerShowTicketsComponent {
  constructor(private api: ApiService, private router: Router) {

  }
  data!: any;
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
      this.data = res.data;
      this.lengtharray = this.data.length;
      console.log(res);
      console.log(this.data);

    })
  }
 

  getMessageData(item: any) {
    this.router.navigate(['customerchat']);
    this.api.setCustomerTicket(item);
    localStorage.setItem("customerViewTicketId",item);
    console.log(this.api.getCustomerTicketInfo());

    console.log(this.api.getCustomerTicketInfo());

  }

  selectedStatus:String='';
  filter: Filter = new Filter();
   flag:boolean=false;
   Status:String='';
  getFilter(status: String) {
    if (status === 'Open') {
    //  this.page=1;
      this.selectedStatus=status
      this.filter.status = 'Open';
      // this.Status=status
      this.flag=true;
      this.api.getCustomerFilterTickets(this.filter).subscribe((res:any) => {
        if(res.success){
          this.data = res.data
          this.lengtharray = this.data.length;
          console.log(this.data);
        }else{
          alert(res.mssg)
        }
        
      })
    }
    else if (status === 'Assigined') {
      // this.page=1
      this.filter.status = 'Assigined'
      this.Status=status

      this.selectedStatus=status

      this.flag=true;
      this.api.getCustomerFilterTickets(this.filter).subscribe((res:any) => {
        if(res.success){
          this.data = res.data;
          this.lengtharray = this.data.length;
          console.log(this.data);
        }
        else{
          alert(res.mssg)
        }
        
      })
    }
    else if (status == 'Closed') {
      // this.page=1
      this.filter.status = 'Closed'
      this.Status=status
    
      this.selectedStatus=status
      
      this.flag=true;
      this.api.getCustomerFilterTickets(this.filter).subscribe((res:any) => {
        if(res.success){
          this.data = res.data;
          this.lengtharray = this.data.length;
          console.log(this.data);
        }else{
          alert(res.mssg)
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
    this.page = event;
    this.getFilter(this.selectedStatus);
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
  
  searchTicket(){

      console.log("Value of searchtickId:", this.searchtickId);
      console.log("Type of searchtickId:", typeof this.searchtickId);
      
      this.search.subject=this.searchtickId;
      this.search.status=this.Status
      console.log(this.search);
      
      this.api.getSearch(this.search).subscribe((res:any)=>{
        if(res.success){
          console.log(res);
          this.data=res.data;
          this.lengtharray=this.data.length; 
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
