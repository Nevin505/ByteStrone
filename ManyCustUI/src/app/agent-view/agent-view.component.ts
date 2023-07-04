import { Component, LOCALE_ID } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { Ticket } from '../model/ticket';

@Component({
  selector: 'app-agent-view',
  templateUrl: './agent-view.component.html',
  styleUrls: ['./agent-view.component.css']
})
export class AgentViewComponent {


all: boolean=false;
searchtickId!:any;


page:number=1;
count:number=0;
tablesize:number=10;
tablesizes:any=[5,10,15,20];

 ticket:Ticket=new Ticket();

constructor(private api: ApiService, private router:Router) {

}
data!: any;
response!: any;
cate!:any;

agentList!:any;
AgentName!:string;
selected!: boolean;
searchresult!:any;
ngOnInit() {
  this.getTicketDetails()

  this.api.getAgentList().subscribe(res=>{
    this.agentList=res;
  })
  this.selected = true;

}
lengtharray!:number;
getTicketDetails(){
this.api.getTicket().subscribe(res => {  
  this.data = res;
   this.lengtharray=this.data.length;
  this.cate=this.api.agentCategoryGetter();
  console.log(this.data);
})
}

onDataChange(event:any){
  this.page=event;
  this.getTicketDetails();
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
  return this.data.sort((a: { priority: string; }, b: { priority: string; }) => {
    const priorityOrder = ['High','Medium','Low'];
    this.selected = false;
    return priorityOrder.indexOf(a.priority) - priorityOrder.indexOf(b.priority);
  });
}
onsortlow(): any[] {
  return this.data.sort((a: { priority: string; }, b: { priority: string; }) => {
    const priorityOrder = ['High', 'Medium', 'Low'];
    this.selected = true;
    return priorityOrder.indexOf(b.priority) - priorityOrder.indexOf(a.priority);
  });
}

val!:boolean;
val2!:boolean;
searchTicket(searchtickId:number){
  this.all=true;
  this.api.getSearchTicket(searchtickId).subscribe(res=>{
    this.searchresult=res;
    // if(searchresult.)
    console.log(this.searchresult);  
    if(this.searchresult.success){
       this.val=this.searchresult.success  
    }
   else{
    this.val=(this.searchresult.success);
    console.log(this.val);
    
    alert("No tickets is There")
   }
    
    
  })
}
opentickets!:any;

openFilter:boolean=true;

filterOpen(){
  this.openFilter=true;
  this.api.getTicket().subscribe((res)=>{
    this.searchresult=res;
    this.opentickets=this.searchresult.filter((tickets:any)=>
      tickets.status==='Handled'
    );
    console.log(this.opentickets);
    
    
  })

}
filterClosed(){


}
//  Added Values
selectedStatus!: string;
filterByStatus(status:string) {
  this.selectedStatus = status;
}

resetFilter() {
  this.selectedStatus = '';
}
  


Set(val:any){
  this.api.particularTicketSetter(val);
}
flag!:boolean;
getFilter(){
  if(this.flag===false){
    this.flag=true;
  }
  else{
    this.flag=false;
  }

}

}

