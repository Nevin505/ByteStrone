import { AgentDetails } from "./agent-details";
import { CategoryDetails } from "./category-details";
import { CustomerDetails } from "./customer-details";

export class UserTicketDetails {

    ticketId:number=0;
    subject:String='';
    description:String='';
    priority:String='';
    status:String='';
    satisfactionRating:number=0;
    creation_Date:any;
    closedDate: any;
    updated_Date:any;
    customer:CustomerDetails=new CustomerDetails();
    agentId:AgentDetails=new AgentDetails();
    categoryId:CategoryDetails=new CategoryDetails();
}
