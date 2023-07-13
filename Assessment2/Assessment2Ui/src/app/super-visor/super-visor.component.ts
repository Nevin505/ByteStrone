import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import Chart from 'chart.js/auto';
import { DateCount } from '../model/date-count';
import { PriorityCount } from '../model/priority-count';
import { Ticket } from '../model/ticket';
@Component({
  selector: 'app-super-visor',
  templateUrl: './super-visor.component.html',
  styleUrls: ['./super-visor.component.css']
})
export class SuperVisorComponent {

  values!: number;

 
   date!:number;
   index!:number;
   
   allTicketNumber:number=0;

  constructor(private api: ApiService) {
  }

  greetings:String[]=["Good Morning","Good AfterNoon", "Good Evening"];
 
  keys: string[] = [];
  valuess: string[] = [];
  dateCount: DateCount[] = [];
  greetingmessage:String='';
  superVisorName: String = '';

  priorityCount:PriorityCount=new PriorityCount();

  ngOnInit() {
    this.date=new Date().getHours(); 

    this.index= this.date<17&&this.date>=12?1:this.date>=17?2:0;
    this.greetingmessage=this.greetings[this.index];  

    //All The Tickets Open and Assigined Tickets
    this.api.getSupervisorOpenAssigined().subscribe((res: any) => {
      this.keys = Object.keys(res);
      this.valuess = Object.values(res);
      console.log(this.keys);
      console.log(this.valuess);
      this.createChart();
    })
    this.superVisorName = this.api.getAgentName();

    //To get All The Tickets Recived So Far
    this.api.getTicketVolumes().subscribe((res:any)=>{
      this.allTicketNumber=res;
    })

    this.showPriorityCount();

    this. getTicketStatusChart();
  }


 
// To Find Open And Tickets Based on Priority Only For Opened Tickets
  ticketCount!: any;
  showPriorityCount() {
    this.api.getProiorityCount().subscribe((res:PriorityCount) => {
      console.log(res);
      this.priorityCount=res;

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
          y: {
            beginAtZero: true,
            ticks: {
              stepSize: 1
            }
          }
        }
        ,plugins: {
          tooltip: {
            enabled: false
          }
        },
        interaction: {
          mode: 'nearest',
          intersect: false
        }
      }
    });
  }


  //Get Ticket Volumes Reports On a Respective Dates 
  keyss!: any;
  valuesss!: any; 
  dateValue!: Date;
  onSetdata() {
    this.api.getSupervisorTicketPerDate(this.dateValue).subscribe((res: any) => {
      this.dateCount = res;
      console.log(res);
      this.keyss = Object.keys(res);
      this.valuesss = Object.values(res);
      var canvasId = 'myDayTicketChart';
      var chart = Chart.getChart(canvasId);
      if (chart) {
        chart.destroy();
      }
      console.log(this.keyss);
      console.log(this.valuesss);
      this.onCreateaDayTicketChartss();
    })
    this.generateReport();
  }

  //Get chart On Dates
  onCreateaDayTicketChartss() {
    var chart = new Chart("myDayTicketChart", {
      type: 'bar',
      data: {
        labels: this.keyss,
        datasets: [{
          label: '# of Tickets',
          data: this.valuesss,
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
            ticks: {
              stepSize: 1
            }
          }
        },plugins: {
          tooltip: {
            enabled: false
          }
        },
        interaction: {
          mode: 'nearest',
          intersect: false
        }
      }
    });
  }


// To get ticke volumes report between two dates
   startdate!: Date;
   enddate!: Date;
   onChartDataBetweenDate(){
     this.api.getTicketBetweenDays(this.startdate,this.enddate).subscribe(res=>{
       this.keyss = Object.keys(res);
       this.valuesss= Object.values(res);
       var canvasId = 'myDayTicketChart';
       var chart = Chart.getChart(canvasId);
       if (chart) {
         chart.destroy();
       }
       this.onCreateaDayTicketChartss();
     })
   }


  //  viewChart(){
  //    this.onChartDataBetweenDate()
  //  }


  //  Ticket Status Chart
  ticketStausKeys: String[] = [];
  ticketCountValues: number[] = [];
  getTicketStatusChart() {
    this.api.getSolvedUnsolvedTickets().subscribe(res => {
      console.log(res);
      this.ticketStausKeys = Object.keys(res);
      this.ticketCountValues = Object.values(res);
      let ticketStatusChart = Chart.getChart("mySolvedUnsolvedTickets");
      this.createTicketStatusChart();
      if (ticketStatusChart) {
        ticketStatusChart.destroy();
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
          position: 'bottom' ,
          labels: {
            usePointStyle: true,
          
          },
          onClick: function() {}
          
        }
      }

    }
  });
  }

  ticketData!:any;
  ticketlength!:number;
  tableShow:boolean=false;
  ticket:Ticket[]=[];
  // ticket:any;
  generateReport(){
    this.api.generateHtmlReport(this.dateValue).subscribe((res:any)=>{
      console.log(res);
      this.ticket=res;
      console.log(this.ticket);
      
      this.tableShow=true;
      this.ticketlength=res.length;
    })
  }
  

  getResponseTime(item: any): string {
    let updatedDate = new Date(item.updated_Date);
    let closedDate = new Date(item.closedDate);
    let diffMilliseconds = closedDate.getTime() - updatedDate.getTime();
  
    let diffSeconds = Math.floor(diffMilliseconds / 1000);
    let diffMinutes = Math.floor(diffSeconds / 60);
    let diffHours = Math.floor(diffMinutes / 60);
  
    let hoursString = diffHours > 0 ? diffHours + 'h ' : '';
    let minutesString = diffMinutes > 0 ? (diffMinutes % 60) + 'm ' : '';
    let secondsString = diffSeconds % 60 + 's';
  
    return hoursString + minutesString + secondsString;
  }
}
