import { Ticket } from './../model/ticket';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { Filter } from '../model/filter';

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
  onDatachange(event: any) {
    this.page = event;
    this.getCustomerTickets()
  }
  getMessageData(item: any) {
    this.router.navigate(['customerchat']);
    this.api.setCustomerTicket(item);
    localStorage.setItem("customerViewTicketId",item);
    console.log(this.api.getCustomerTicketInfo());

    console.log(this.api.getCustomerTicketInfo());

  }

  filter: Filter = new Filter();
   flag:boolean=false;
  getFilter(status: string) {
    if (status === 'Open') {
      this.filter.status = 'Open';
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
      this.filter.status = 'Assigined'
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
      this.filter.status = 'Closed'
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


}
