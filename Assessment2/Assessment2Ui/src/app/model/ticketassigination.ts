import { Agentlogin } from "./agentlogin";
import { Customerlogin } from "./customerlogin";

export class Ticketassigination {

    ticketId!:number;
    subject:string='';
    status!:string;
    description:string='';
    priority:string='';
    creation_Date!:Date;
    closedDate!:Date;
    satisfactionRating!:number;
    customer:Customerlogin=new Customerlogin();
    agentId:Agentlogin=new Agentlogin();

    

}
