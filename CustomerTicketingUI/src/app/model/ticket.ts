import { Category } from "./category";
import { customer } from "./customer";

export class Ticket {
    ticketId!:number;
    customerid!:number
    subject!:String ;
    description!:String ;
    priority!:String;
    status!:String; 
    customer!:customer;
    category!: Category;   
}
