import { Chat } from './../model/chat';
import { Injectable } from '@angular/core';
import { Customerlogin } from '../model/customerlogin';
import { HttpClient } from '@angular/common/http';
import { Category } from '../model/category';
import { Ticket } from '../model/ticket';
import { Agentlogin } from '../model/agentlogin';
import { SearchCriteria } from '../model/search-criteria';
import { filter } from 'rxjs';
import { PriorityCount } from '../model/priority-count';


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
  AgentName:String='';

  setAgentName(agentName:String){
    this.AgentName=agentName;
  }
  getAgentName(){
    return this.AgentName;
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


  setCustomerTicket(ticketCustomerId:any){
    this.customerTicket=ticketCustomerId;
  }
  getCustomerTicketInfo(){
    return this.customerTicket;
  }
 
  

  validateCustomer(customerlogin:Customerlogin){
    return this.http.post<Customerlogin>("http://localhost:8080/customer/access",customerlogin)
  }
  customerName!:String;
  getCustomerName(){
   return this.customerName;
  }
  setCustomerName(name:String){
    this.customerName=name;
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
  // getSearchTicket(searchtickId:number){
  //   return this.http.get(`http://localhost:8080/agents/categorytickets/${this.agentId}/${searchtickId}`)
  // }
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

  getOpenTickets(){
    return this.http.get(`http://localhost:8080/agents/getOpenTickets/${this.agentId}`)
  }
  getClosedTickets(){
    return this.http.get(`http://localhost:8080/agents/getCloseTickets/${this.agentId}`)
  }

  

  // Added New To get Open Tickets
  getOpenTicke(){
    return this.http.get(`http://localhost:8080/agents/getOpentickets/${this.agentId}`)
  }
  getCloseFullTickets(){
    return this.http.get(`http://localhost:8080/agents/getCloseTickets/${this.agentId}`)
  }
  getSearch(search:SearchCriteria){
    return this.http.post(`http://localhost:8080/agents/search/${this.agentId}`,search)
  }

  
 
  getTicketBetweenDays(start:Date,end:Date){
    return this.http.get(`http://localhost:8080/supervisor/date/${start}/${end}`)
  }

  setTicketRating(rating:number){
    return this.http.put(`http://localhost:8080/customer/setSatisfactoryRating/${this.customerTicket}/${rating}`,{},{ responseType:'text' })
  }
  
  getAllTicketComments(){
    return this.http.get(`http://localhost:8080/comment/allticketcomments/${this.particularTicketId}`)
  }

  // Customer Functionalities Filter Open And Closed Tickets

  getCustomerFilterTickets(filter:any){
    return this.http.post(`http://localhost:8080/customer/filtercustomertickets/${this.customerId}`,filter)
  }

  //SuperVisor Section
  
  getSolvedUnsolvedTickets(){
    return this.http.get("http://localhost:8080/supervisor/agents/alltickets")
  }

  getProiorityCount(){
    return this.http.get<PriorityCount>("http://localhost:8080/supervisor/priotityCounts")
  }
  
  getSupervisorOpenAssigined(){
    return this.http.get('http://localhost:8080/supervisor/report/percategoriestickets')
  }

  getSupervisorTicketPerDate(date:Date){
    return this.http.get(`http://localhost:8080/supervisor/date/${date}`)
  }

  getTicketVolumes(){
    return this.http.get("http://localhost:8080/supervisor/report/tickets")
  }

  generateHtmlReport(date:Date){
    return this.http.get(`http://localhost:8080/supervisor/htmlreportdate/${date}`)
  }

}
