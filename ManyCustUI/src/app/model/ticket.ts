import { Agentlogin } from "./agentlogin";
import { Category } from "./category";
import { Customerlogin } from "./customerlogin";

export class Ticket {
    ticketId!:number;
    subject:string='';
    status!:string;
    description:string='';
    priority:string='';
    customer!:Customerlogin
    categoryName!:string;
    category!:Category;
    mssg!:string;
    success!:boolean;
    agent:Agentlogin=new Agentlogin();
    data!:any;

}
