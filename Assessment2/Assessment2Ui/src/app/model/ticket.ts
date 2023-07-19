import { Agentlogin } from "./agentlogin";
import { Category } from "./category";
import { Customerlogin } from "./customerlogin";

export class Ticket {
    ticketId!:number;
    subject:string='';
    status!:string;
    description:string='';
    priority:string='';
    creation_Date!:Date;
    customer!:Customerlogin
    categoryName!:string;
    category!:Category;
    categoryId:Category=new Category();
    mssg!:string;
    success!:boolean;
    agent:Agentlogin=new Agentlogin();
    agentId:Agentlogin=new Agentlogin();
    data!:any;
    updated_Date!:Date;
    closedDate!:Date

}
