import { Component, LOCALE_ID } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { SearchCriteria } from '../model/search-criteria';
import { HttpErrorResponse } from '@angular/common/http';
import { Filter } from '../model/filter';

@Component({
  selector: 'app-agent-view',
  templateUrl: './agent-view.component.html',
  styleUrls: ['./agent-view.component.css']
})

export class AgentViewComponent {

  constructor(private api: ApiService, private router: Router) {

  }

  page: number = 1;
  count: number = 0;
  tablesize: number = 9;
  datas!: any;
  response!: any;
  cate!: any;
  ResetButton: boolean = false;
  agentList!: any;
  AgentName!: string;
  selected!: boolean;
  searchresult!: any;
  ticketAssiginedCount: number = 0;
  ngOnInit() {
    this.getTicketDetails()

    this.api.getAgentList().subscribe({
      next: (res: any) => {
        if (res.success) {
          this.agentList = res;
          console.log(this.agentList);
        }
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error.mssg)
      }
    })

    this.selected = true;
    this.agentName = sessionStorage.getItem('agentName') || "";
    this.cates = JSON.parse(sessionStorage.getItem("Categorys") || "");

    this.api.getAssiginedTickets().subscribe({
      next: (res: any) => {
        if (res.success) {
          this.ticketAssiginedCount = res.data.length;
        }
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
        alert(error.error.mssg)
      }
    })
  }

  lengtharray!: number;
  agentName: String = '';
  cates!: any;
  getTicketDetails() {
    this.api.getTicket().subscribe({
      next: (res: any) => {
        if (res.success) {
          this.datas = res.data;
          this.lengtharray = this.datas.length;
          this.lengthsearch = this.datas.length;
        }
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error.mssg)
      }
    })
  }

  Flag: boolean = false;
  getFilter() {
    this.Flag = !this.Flag;
  }

  currentStaus: String = '';
  filter: Filter = new Filter();
  openTicketData!: any;
  messageFilter: String = '';
  filterByStatus(Status: String) {
    this.currentStaus = Status;
    this.searchButton = false;
    this.page = 1;
    if (this.currentStaus === 'Open' || this.currentStaus == 'Closed') {
      this.ResetButton = true;
      this.filter.status = this.currentStaus
      this.api.getFilteredTickets(this.filter).subscribe({
        next: (res: any) => {
          if (res.success) {
            this.datas = res.data;
            this.lengthsearch = this.datas.length;
            this.lengtharray = this.datas.length;
            if (this.lengtharray < this.tablesize) {
              this.page = 1;
            }
            this.messageFilter = "No" + this.currentStaus + "Tickets Are There";
          }
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.mssg)
        }
      })
    }
    else {
      this.ResetButton = false;
      this.getTicketDetails();
    }

  }


  onData(event: any) {
   if (this.currentStaus === 'Open' || this.currentStaus === 'Closed') {
      if (this.sortHigh) {
        this.onsortHigh();
      } else if (this.sortLow) {
        this.onsortlow();
      } else {
        this.getTicketDetails();
      }
      this.filterByStatus(this.currentStaus);
      this.page = event;
    }
   else if (this.isSearching) {
      this.searchTicket();
      this.page = event;
    } 
    else {
      if (this.sortHigh) {
        this.onsortHigh();
      } else if (this.sortLow) {
        this.onsortlow();
      } else {
        this.getTicketDetails();
      }
      this.page = event;
    }
  }

  searchtickId!: any;
  search: SearchCriteria = new SearchCriteria();
  lengthsearch: number = 0;
  searchButton: boolean = false;
  na!: any;
  isSearching: boolean = false;

  searchTicket() {
    this.searchtickId = this.searchtickId.trim();
    if (this.searchtickId.length != 0) {
      this.page = 1;
      this.na = true;
      this.search.subject = this.searchtickId;
      this.search.status = this.currentStaus
      this.api.getSearch(this.search).subscribe({
        next: (res: any) => {
          if (res.success) {
            console.log(res);
            this.datas = res.data;
            this.lengthsearch = this.datas.length;
            this.lengtharray = this.lengthsearch

            this.messageFilter = `<p><strong>No results found for ${this.searchtickId}</strong></p><p>Please make sure your words are spelled correctly, or use fewer or different keywords.</p>`;
          }
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error.mssg)
        }
      })
      this.searchButton = true;
      this.isSearching = true;
    }
    else {
      alert("Please Enter Something")
    }
  }

  Set(val: any) {
    sessionStorage.setItem("particularTicketId", val);
  }

  sortHigh: boolean = false;
  sortLow: boolean = false;

  onsortHigh(): any[] {
    this.sortHigh = true;
    this.sortLow = false;
    return this.datas.sort((a: { priority: string; }, b: { priority: string; }) => {
      const priorityOrder = ['High', 'Medium', 'Low'];
      this.selected = false;
      return priorityOrder.indexOf(a.priority) - priorityOrder.indexOf(b.priority);
    });
  }
  onsortlow(): any[] {
    this.sortLow = true;
    this.sortHigh = false;
    return this.datas.sort((a: { priority: string; }, b: { priority: string; }) => {
      const priorityOrder = ['High', 'Medium', 'Low'];
      this.selected = true;
      return priorityOrder.indexOf(b.priority) - priorityOrder.indexOf(a.priority);
    });
  }


  resetData(Status: String) {
    this.isSearching=false;
    console.log(this.currentStaus);
    
    this.filterByStatus(this.currentStaus);
  }
  removeValues() {
    this.router.navigate(['landingPage'])
    sessionStorage.clear();
  }
}
