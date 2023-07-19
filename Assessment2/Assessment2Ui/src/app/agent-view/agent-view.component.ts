import { Component, LOCALE_ID } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { Ticket } from '../model/ticket';
import { SearchCriteria } from '../model/search-criteria';

@Component({
  selector: 'app-agent-view',
  templateUrl: './agent-view.component.html',
  styleUrls: ['./agent-view.component.css']
})
export class AgentViewComponent {




  constructor(private api: ApiService, private router: Router) {

  }
  datas!: any;
  response!: any;
  cate!: any;

  agentList!: any;
  AgentName!: string;
  selected!: boolean;
  searchresult!: any;
  ngOnInit() {
    this.getTicketDetails()
   
    this.api.getAgentList().subscribe(res => {
      this.agentList = res;
    })
    this.selected = true;


  }
  lengtharray!: number;
  agentName:String='';
  cates!:any;
  getTicketDetails() {
     this.agentName= this.api.getAgentName();
    this.api.getTicket().subscribe((res:any) => {
      if(res.success){
        this.datas = res.data;
        this.lengthsearch=this.datas.length;
        this.cate = this.api.agentCategoryGetter();
        console.log(this.datas);
        this.cates=JSON.parse( localStorage.getItem("Categorys") as string)
      }
      else{
        alert(res.mssg)
      }
     
    })
  }

  



  Flag: boolean = false;
  getFilter() {
    if (this.Flag === false) {
      this.Flag = true;
    }
    else {
      this.Flag = false;
    }

  }
  currentStaus: String = '';

  openTicketData!: any;
  messageFilter:String='';
  filterByStatus(Status: String) {
    this.currentStaus = Status;
    if (this.currentStaus === 'Open') {
      this.api.getOpenTicke().subscribe((res:any) => {
        if(res.success){
          this.datas = res.data;
          this.lengthsearch=this.datas.length;
          this.messageFilter="No Open Tickets Are There";
          
        }
        else{
          alert(res.mssg)
        }
       
      })
    }
    else if (this.currentStaus == 'Closed') {
      this.api.getCloseFullTickets().subscribe((res:any) => {
        if(res.success){
          this.datas = res.data;
          console.log(this.datas);
          this.lengthsearch=this.datas.length; 
          this.messageFilter="No  Close Tickets Are There"
        }
       else{
          alert(res.mssg)
       }
        
       
        // ser
      })
      
    }
    else {
       this.getTicketDetails();
    }

  }

  searchtickId!:any;

  search:SearchCriteria=new SearchCriteria();
  
  lengthsearch:number=0;

   searchButton:boolean=false;
  searchTicket(){

    console.log("Value of searchtickId:", this.searchtickId);
    console.log("Type of searchtickId:", typeof this.searchtickId);
    
    this.search.subject=this.searchtickId;
    this.search.status=this.currentStaus
    console.log(this.search);
    
    this.api.getSearch(this.search).subscribe((res:any)=>{
      if(res.success){
        console.log(res);
        this.datas=res.data;
        this.lengthsearch=this.datas.length; 
        this.messageFilter="No Tickets Are There"
      }
      else{
        alert(res.mssg)
      }
      
    })

    this.searchButton=true;

  }
    Set(val:any){
      console.log(val);
      
      localStorage.setItem("particularTicketId",val);
    this.api.particularTicketSetter(val);
  }
 
  sortColumn(): void{
      if(this.selected == true){
        this.onsortHigh();
      }
      else{
        this.onsortlow();
      }
    }
  
    onsortHigh(): any[] {
      return this.datas.sort((a: { priority: string; }, b: { priority: string; }) => {
        const priorityOrder = ['High','Medium','Low'];
        this.selected = false;
        return priorityOrder.indexOf(a.priority) - priorityOrder.indexOf(b.priority);
      });
    }
    onsortlow(): any[] {
      return this.datas.sort((a: { priority: string; }, b: { priority: string; }) => {
        const priorityOrder = ['High', 'Medium', 'Low'];
        this.selected = true;
        return priorityOrder.indexOf(b.priority) - priorityOrder.indexOf(a.priority);
      });
    }
  

    resetData(){
      this.getTicketDetails();
      this.searchButton=false;
    }
    removeValues(){
      // [routerLink]="['/LoginComponent']"
      this.router.navigate(['landingPage'])
      // localStorage.removeItem('agentUserId');
      // localStorage.removeItem('particularTicketId')
      // localStorage.removeItem('agentChatTicketId')
      // localStorage.removeItem('customerTicketId')
      // localStorage.removeItem('Categorys')
      localStorage.clear();
      

    }









}


  // all: boolean=false;
  // searchtickId!:any;


  // page:number=1;
  // count:number=0;
  // tablesize:number=10;
  // tablesizes:any=[5,10,15,20];

  //  ticket:Ticket=new Ticket();

  // constructor(private api: ApiService, private router:Router) {

  // }
  // data!: any;
  // response!: any;
  // cate!:any;

  // agentList!:any;
  // AgentName!:string;
  // selected!: boolean;
  // searchresult!:any;
  // ngOnInit() {
  //   this.getTicketDetails()

  //   this.api.getAgentList().subscribe(res=>{
  //     this.agentList=res;
  //   })
  //   this.selected = true;

  // }
  // lengtharray!:number;
  // getTicketDetails(){
  // this.api.getTicket().subscribe(res => {  
  //   this.data = res;
  //    this.lengtharray=this.data.length;
  //   this.cate=this.api.agentCategoryGetter();
  //   console.log(this.data);
  // })
  // }

  // onDataChange(event:any){
  //   this.page=event;
  //   this.getTicketDetails();
  // }


  // sortColumn(): void{
  //   if(this.selected == true){
  //     this.onsortHigh();
  //   }
  //   else{
  //     this.onsortlow();
  //   }
  // }

  // onsortHigh(): any[] {
  //   return this.data.sort((a: { priority: string; }, b: { priority: string; }) => {
  //     const priorityOrder = ['High','Medium','Low'];
  //     this.selected = false;
  //     return priorityOrder.indexOf(a.priority) - priorityOrder.indexOf(b.priority);
  //   });
  // }
  // onsortlow(): any[] {
  //   return this.data.sort((a: { priority: string; }, b: { priority: string; }) => {
  //     const priorityOrder = ['High', 'Medium', 'Low'];
  //     this.selected = true;
  //     return priorityOrder.indexOf(b.priority) - priorityOrder.indexOf(a.priority);
  //   });
  // }

  // val!:boolean;
  // val2!:boolean;
  // searchTicket(searchtickId:number){
  //   this.all=true;
  //   this.api.getSearchTicket(searchtickId).subscribe(res=>{
  //     this.searchresult=res;
  //     // if(searchresult.)
  //     console.log(this.searchresult);  
  //     if(this.searchresult.success){
  //        this.val=this.searchresult.success  
  //     }
  //    else{
  //     this.val=(this.searchresult.success);
  //     console.log(this.val);

  //     alert("No tickets is There")
  //    }


  //   })
  // }
  // opentickets!:any;

  // openFilter:boolean=true;

  // filterOpen(){
  //   this.openFilter=true;
  //   this.api.getTicket().subscribe((res)=>{
  //     this.searchresult=res;
  //     this.opentickets=this.searchresult.filter((tickets:any)=>
  //       tickets.status==='Handled'
  //     );
  //     console.log(this.opentickets);


  //   })

  // }
  // filterClosed(){


  // }
  // //  Added Values
  // selectedStatus!: string;
  // filterByStatus(status:string) {
  //   this.selectedStatus = status;
  // }

  // resetFilter() {
  //   this.selectedStatus = '';
  // }



  // Set(val:any){
  //   this.api.particularTicketSetter(val);
  // }
  // flag!:boolean;
  // getFilter(){
  //   if(this.flag===false){
  //     this.flag=true;
  //   }
  //   else{
  //     this.flag=false;
  //   }

  // }