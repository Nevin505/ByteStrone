import { Agentlogin } from "./agentlogin";
import { Ticket } from "./ticket";

export class Chat {
    content!:string;
    author!:string;
    ticket:Ticket=new Ticket();
    agentId:Agentlogin=new Agentlogin();

}
