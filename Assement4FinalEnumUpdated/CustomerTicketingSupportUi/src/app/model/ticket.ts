import { Agent } from "./agent";

import { Category } from "./category";
import { CustomerDetails } from "./customer-details";

export class Ticket {
    mssg!:string;
    data!:any;
    success!:boolean;

    ticketId:number=0;
    subject:String='';
    status!:String;
    description:string='';
    priority:string='';
    satisfactionRating:number=0;
    creation_Date!:Date;
    updated_Date!:Date;
    closedDate!:Date

    categoryName!:string;
    category!:Category;
   
    categoryId:Category=new Category();

    agentId:Agent=new Agent();
  
    customer:CustomerDetails=new CustomerDetails();
  
}
// Modified on 28-08-2023
