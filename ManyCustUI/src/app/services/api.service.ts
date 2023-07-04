import { Chat } from './../model/chat';
import { Injectable } from '@angular/core';
import { Customerlogin } from '../model/customerlogin';
import { HttpClient } from '@angular/common/http';
import { Category } from '../model/category';
import { Ticket } from '../model/ticket';
import { Agentlogin } from '../model/agentlogin';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  
  customerId!:number;
  agentId!:number;
  agentCategory!:string;
  

  constructor(private http:HttpClient) { 

  }

  

  customerIdGetter(){
     return this.customerId;
  }
  customerIdSetter(Id:number){
    this.customerId=Id;
  }

  agentIdGetter(){
    return this.agentId;
  }
  agentIdSetter(Id:number){
     this.agentId=Id;
  }
  agentCategoryGetter(){
    return this.agentCategory
  }
  agentCategorySetter(category:string){
    this.agentCategory=category;
  }
  
  agentTicketId!:any;
  agentTicketIdGetter(){
    return this.agentTicketId;
  }
  agentTicketIdSetter(Id:any){
   this.agentTicketId=Id;
  }

  agentcha!:any;
  getagentChat(){
    return this.agentcha;
  }
  setagentChat(agechat:any){
    this.agentcha=agechat;
  }
  
  particularTicketId!:any;
  getparticularTicketId(){
   return this.particularTicketId
  }
  particularTicketSetter(Id:any){
    this.particularTicketId=Id;
  }
  customerTicket!:any;

  getCustomerTicketInfo(){
    return this.customerTicket;
  }
  setCustomerTicket(ticketCustomerId:any){
    this.customerTicket=ticketCustomerId;
  }

  validateCustomer(customerlogin:Customerlogin){
    return this.http.post<Customerlogin>("http://localhost:8080/customer/access",customerlogin)
  }

  getCategory() {
    return this.http.get<Category[]>('http://localhost:8080/ticket/values');
  }

  addTicket(ticket:any){
    console.log(this.customerId);
    console.log(ticket);   
    return this.http.post(`http://localhost:8080/ticket/addticket/${this.customerId}`,ticket)
  }

  getCustomerTicketDetails(){
    return this.http.get(`http://localhost:8080/customer/getCustomerTicketDetails/${this.customerId}`)
  }

  agentloginService(agent:Agentlogin){
    return this.http.post('http://localhost:8080/agents/Access',agent)
  }

  getTicket(){
    return this.http.get(`http://localhost:8080/agents/categorytickets/${this.agentId}`)
  }

  getAgentList(){
    return this.http.get(`http://localhost:8080/agents/agentsCategory/${this.agentId}`)
  }
  getSearchTicket(searchtickId:number){
    return this.http.get(`http://localhost:8080/agents/categorytickets/${this.agentId}/${searchtickId}`)
  }
  getSpecificTicket(){
    return this.http.get(`http://localhost:8080/agents/specificticket/${this.particularTicketId}`)
  }
  I:any;
  assignTickets(agentId:any){
   this.I=agentId;
    return this.http.get(`http://localhost:8080/agents/${this.particularTicketId}/assign/${this.I}`,{ responseType: 'text' })
  }
  getAssiginedTickets(){
    return this.http.get(`http://localhost:8080/agents/assignedTickets/${this.agentId}`)
  }
  getChatMessage(chat:Chat){
    return this.http.post(`http://localhost:8080/comment/addcomments/${this.agentId}/${this.agentcha}`,chat)
  }
  getTicketChat(){
    return this.http.get<Ticket>(`http://localhost:8080/agents/specificticket/${this.agentcha}`)
  }
  
  setCustomerChat(chats:Chat){
     return this.http.post(`http://localhost:8080/comment/addcustomercomments/${this.customerId}/${this.customerTicket}`,chats)
  }
  
  getAgentCustomerChat(){
    return this.http.get(`http://localhost:8080/comment/agentCustomerConversation/${ this.customerTicket}`)
  }

  getCustomerSingleTicketsDetails(){
    return this.http.get(`http://localhost:8080/ticket/getTicket/${this.customerTicket}`)
  }
  closeTicketss(){
    return this.http.put(`http://localhost:8080/agents/${this.customerTicket}/closedticket/${this.agentId}`,{ responseType:'text' })
  }
}
