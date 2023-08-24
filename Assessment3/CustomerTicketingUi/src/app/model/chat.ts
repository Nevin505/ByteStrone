import { Agentlogin } from "./agentlogin";
import { Ticket } from "./ticket";

export class Chat {
    content!:String;
    author!:string;
    timeStamp!:Date;
    ticket:Ticket=new Ticket();
    agentId:Agentlogin=new Agentlogin();

}
