import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import Chart from 'chart.js/auto';
import { DateCount } from '../model/date-count';
import { PriorityCount } from '../model/priority-count';
import { Ticket } from '../model/ticket';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-super-visor',
  templateUrl: './super-visor.component.html',
  styleUrls: ['./super-visor.component.css']
})
export class SuperVisorComponent {

  values!: number;

  date!: Date;
  index!: number;

  allTicketNumber: number = 0;

  constructor(private api: ApiService) {
  }

  greetings: String[] = ["Good Morning", "Good AfterNoon", "Good Evening"];

  keys: String[] = [];
  valuess: string[] = [];
  dateCount: DateCount[] = [];
  greetingmessage: String = '';
  superVisorName: String = '';

  priorityCount: PriorityCount = new PriorityCount();

  ngOnInit() {

    this.date = new Date();
    this.index = this.date.getHours() < 17 && this.date.getHours() >= 12 ? 1 : this.date.getHours() >= 17 ? 2 : 0;
    this.greetingmessage = this.greetings[this.index];

    this.superVisorName = sessionStorage.getItem("superVisorName") || ' '
    //All The Tickets Open and Assigined Tickets
    this.api.getSupervisorOpenAssigined().subscribe( {
      next:(res: any)=>{
        if (res.success) {
          this.keys = Object.keys(res.data);
          this.valuess = Object.values(res.data);
          console.log(this.keys);
          console.log(this.valuess);
          this.createChart();
        }
        else{
          alert(res.mssg);
        }
      },
      error:(error:HttpErrorResponse)=>{      
          alert(error.error.mssg)     
      }
      
    })
    //To get All The Tickets Recived So Far
    this.api.getTicketVolumes().subscribe((res: any) => {
      if (res.success) {
        this.allTicketNumber = res.data;
      }


    })

    this.showPriorityCount();
    this.getTicketStatusChart();
    this.sortClosedDate();
  }

  // To Find Open And Tickets Based on Priority Only For Opened Tickets
  ticketCount!: any;
  showPriorityCount() {
    this.api.getProiorityCount().subscribe((res: any) => {
      if (res.success) {
        console.log(res);
        this.priorityCount = res.data;
      }
    })
  }
  //Opening Chart 
  // Total Tickets 
  
  createChart() {
    var chart = new Chart("myChart", {
      type: 'bar',
      data: {
        labels: this.keys,
        datasets: [{
          label: 'Tickets',
          data: this.valuess,
          borderWidth: 1
        }]
      },
      
      options: {
        scales: {
          x: {
            ticks: {
              stepSize: 1,
              maxRotation: 50,
              minRotation: 30,
              padding: 10,
              autoSkip: false
            }
          },
          y: {
            beginAtZero: true,
            ticks: {
              stepSize: 1
            },

            title: {
              display: true,
              text: 'Number Of Tickets '
            }
          }
        }
        , plugins: {
          tooltip: {
            enabled: false,
            
          }, legend: {
            position: 'bottom',
            onClick: function () { 

            }
          }
        },
        interaction: {
          mode: 'nearest',
          intersect: false
        }
      }
    });
  }
  //  Ticket Status Chart
  ticketStausKeys: String[] = [];
  ticketCountValues: number[] = [];
  getTicketStatusChart() {
    this.api.getSolvedUnsolvedTickets().subscribe( {
      next:(res:any)=>{
        console.log(res);
        if (res.success) {
          console.log("Here",res);     
          this.ticketStausKeys = Object.keys(res.data);
          this.ticketCountValues = Object.values(res.data);
          let ticketStatusChart = Chart.getChart("mySolvedUnsolvedTickets");
          this.createTicketStatusChart();
          if (ticketStatusChart) {
            ticketStatusChart.destroy();
          }
        }
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
        alert(error.error.mssg)
      }
    })
  }

  createTicketStatusChart() {
    var chart = new Chart("mySolvedUnsolvedTickets", {
      type: 'doughnut',
      data: {
        labels: this.ticketStausKeys,
        datasets: [{
          data: this.ticketCountValues,

          borderWidth: 1
        }]
      },
      options: {
        plugins: {
          legend: {
            position: 'bottom',
            labels: {
              usePointStyle: true,
            },
            onClick: function () { }
          },

        },


      }
    });
  }

  getResponseTime(item: any): string { 
    let updatedDate = new Date(item.updated_Date);
    let closedDate = new Date(item.closedDate);
    let diffMilliseconds = closedDate.getTime() - updatedDate.getTime();

    // Calculate the difference and format the response time
    let diffSeconds = Math.floor(diffMilliseconds / 1000);
    let diffMinutes = Math.floor(diffSeconds / 60);
    let diffHours = Math.floor(diffMinutes / 60);

    let hoursString = diffHours > 0 ? diffHours + 'h ' : '';
    let minutesString = diffMinutes > 0 ? (diffMinutes % 60) + 'm ' : '';
    let secondsString = diffSeconds % 60 + 's';

    return hoursString + minutesString + secondsString;
  }

  // To get ticke volumes report between two dates
  startdate!: Date;
  enddate!: Date;

  ticketFullData: Ticket[] = [];
  categories: string[] = [];
  openCounts: number[] = [];
  assignedCounts: number[] = [];
  closedCounts: number[] = [];
  graphDisplay: boolean = false;
  agentss: any = [];
  agentCounts: any = [];
  agentNames: String = '';
  agentIndex: number = 0;
  closedTicketSort: boolean = false;

  page: number = 1;
  count: number = 0;
  tablesize: number = 9;
  lengtharray: number = 0;
 data:any;
  viewVolumes() {

    this.graphDisplay = true;

    if (this.startdate <= this.enddate) {
      this.api.generateHtmlReportBetweenDates(this.startdate, this.enddate).subscribe( {
        next:(res: any)=>{
          if (res.success) {
            this.ticketFullData = res.data;
            this.ticketFullData.sort((a, b) => {
              if (a.status === "Closed" && b.status !== "Closed") {
                return -1; 
              } else if (a.status !== "Closed" && b.status === "Closed") {
                return 1;
              } else {
                return 0; 
              }
            });
            
            console.log("The Closed Tickets",this.ticketFullData);
            console.log(this.sortClosedDate());
            console.log("The Sorted data");
            // console.log(this.data);
            this.lengtharray = this.ticketFullData.length;
            this.data=this.sortClosedDate();
            console.log(this.data);
            if (this.ticketFullData.length != 0) {
        
              this.categories = [];
              this.openCounts = [];
              this.assignedCounts = [];
              this.closedCounts = [];
              this.agentss = [];
              this.agentCounts = [];
  
  
              console.log(this.ticketFullData);
              console.log(res);
              console.log("Here");
  
              console.log(this.sortClosedDate());
              console.log("there");
  
              for (let ticket of this.ticketFullData) {
                let category = ticket.categoryId.category_name;
                let status = ticket.status;
                if (ticket.agentId !== null) {
                  console.log("Here");
                  console.log(ticket.agentId.userName);
  
                  this.agentNames = ticket.agentId.userName;
                  this.agentIndex = this.agentss.indexOf(this.agentNames);
                  this.agentIndex = this.agentss.findIndex((agent: any) => agent.name === this.agentNames);
                  if (this.agentIndex === -1) {
                    this.agentss.push({ name: this.agentNames, count: 0 });
                    this.agentIndex = this.agentss.length - 1;
                  }
                  this.agentss[this.agentIndex].count++;
                }
                let index = this.categories.indexOf(category);
  
                if (index === -1) {
                  this.categories.push(category);
                  this.openCounts.push(0);
                  this.assignedCounts.push(0);
                  this.closedCounts.push(0);
                  index = this.categories.length - 1;
                }
                if (status === 'Open') {
                  this.openCounts[index]++;
                } else if (status === 'Assigned') {
                  this.assignedCounts[index]++;
                } else if (status === 'Closed') {
                  this.closedCounts[index]++;
                }
              }
              console.log(this.categories);
              console.log(this.openCounts);
              console.log(this.assignedCounts);
              console.log(this.closedCounts);
              console.log(this.agentss);
  
              var chartId = "myBetweenDayTicketCharts";
              var chart = Chart.getChart(chartId);
              if (chart) {
                chart.destroy();
              }
              // this.chart;
              this.getChart();
            }
          }
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);
          alert(error.error.mssg)
        }
      })
    }
    else {
      alert("Enter a Valid Date");
      this.graphDisplay = false;
      this.ticketFullData = [];
      var chart = Chart.getChart("myBetweenDayTicketCharts");
      if (chart) {
        chart.destroy();
      }
    }

  }
  onData(event: any) {
    if (this.closedTicketSort) {
      this.page = event;
    }
    else {
      this.page = event;
      this.viewVolumes();
    }
  }

  getChart() {
    var chart = new Chart("myBetweenDayTicketCharts", {
      type: 'bar',
      data: {
        labels: this.categories,
        datasets: [{
          label: 'Open Tickets',
          data: this.openCounts,
          borderWidth: 1,
          backgroundColor: "#00BFFF"
        }, {
          label: 'Assigined Tickets',
          data: this.assignedCounts,
          borderWidth: 1,
          backgroundColor: "#0000CD"
        }, {
          label: 'Closed Tickets',
          data: this.closedCounts,
          borderWidth: 1,
          backgroundColor: "#000080"
        }]
      },
      options: {
        scales: {
          x: {
            title: {
              display: true,
            },
            ticks: {
              stepSize: 1,
              maxRotation: 50,
              minRotation: 30,
              padding: 10,
              autoSkip: false
            }
          },
          y: {
            beginAtZero: true,
            ticks: {
              stepSize: 1
            }
          },

        }, plugins: {
          tooltip: {
            enabled: false
          },
          legend: {
            position: 'bottom',
            labels: {
              usePointStyle: true
            },
            onClick: function () { }
          }
        },
        interaction: {
          mode: 'nearest',
          intersect: false
        }

      }
    });
  }



  flag: Boolean = false;
  sortCreationDate() {
    this.ticketFullData;
    if (this.flag == true) {
      this.ticketFullData.sort((a, b) => {
        const dateA = new Date(a.creation_Date);
        const dateB = new Date(b.creation_Date)
        return dateA.getTime() - dateB.getTime();
      })
      this.flag = false;
    }
    else {
      this.ticketFullData.sort((a, b) => {
        const dateA = new Date(a.creation_Date);
        const dateB = new Date(b.creation_Date)
        return dateB.getTime() - dateA.getTime();
      })
      this.flag = true;
    }
  }
  flagsortAssigined: boolean = false;
  sortAssiginedDate() {
    if (this.flagsortAssigined == false) {
      this.ticketFullData.sort((a, b) => {
        const dateA = a.updated_Date !== null ? new Date(a.updated_Date) : null;
        const dateB = b.updated_Date !== null ? new Date(b.updated_Date) : null;
        if (dateA && dateB) {
          return dateA.getTime() - dateB.getTime();
        } else if (dateA && !dateB) {
          return -1;
        } else if (!dateA && dateB) {
          return 1;
        }
        else {
          return 0;
        }
      })
      this.flagsortAssigined = true;
    }
    else {
      this.ticketFullData.sort((a, b) => {
        const dateA = a.updated_Date !== null ? new Date(a.updated_Date) : null;
        const dateB = b.updated_Date !== null ? new Date(b.updated_Date) : null;
        if (dateA && dateB) {
          return dateB.getTime() - dateA.getTime();
        } else if (dateA && !dateB) {
          return -1;
        } else if (!dateA && dateB) {
          return 1;
        }
        else {
          return 0;
        }
      })
      this.flagsortAssigined = false;
    }
  }

  sortClosedDate() {
    this.closedTicketSort = true;
    return this.ticketFullData.sort((a, b) => {
      const dateA = a.closedDate !== null ? new Date(a.closedDate).getTime() : null;
      const dateB = b.closedDate !== null ? new Date(b.closedDate).getTime() : null;
      if (dateA && dateB) {
        return dateA - dateB;
      }
      else if (dateA && !dateB) {
        return -1;
      } else if (!dateA && dateB) {
        return 1;
      }
      else {
        return 0;
      }
    }) 
  }
}
