import { Agentlogin } from "./agentlogin";
import { Category } from "./category";
import { Customerlogin } from "./customerlogin";

export class TicketsCategory {


    ticketId!:number;
    subject:string='';
    status!:string;
    description:string='';
    priority:string='';
    customer!:Customerlogin
    categoryName!:string;
    categoryId!:Category;
    mssg!:string;
    success!:boolean;
    agent:Agentlogin=new Agentlogin();
    data!:any;
    creation_Date!:Date;

}
