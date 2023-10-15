import { UserDetails } from './../model/user-details';
import { Chat } from './../model/chat';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from '../model/category';
import { Ticket } from '../model/ticket';
import { SearchCriteria } from '../model/search-criteria';
import { filter } from 'rxjs';
import { PriorityCount } from '../model/priority-count';
import * as CryptoJS from 'crypto-js';
// import { UserTicketDetails } from '../model/user-ticket-details';
import { Common } from '../model/common';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  customerId!: number;
  agentId!: number;
  agentCategory!: string;


  constructor(private http: HttpClient) {

  }
  ticketCount:number=0;
  setTicketCount(count:number){
    this.ticketCount=count;
  }
  getTicketCount(){
    return this.ticketCount;
  }
  customerName!: String;
  getCustomerName() {
    return this.customerName;
  }
  setCustomerName(name: String) {
    this.customerName = name;
  }
  selectedd:String='';
  saveFilterStatus(Status:String){
   this.selectedd=Status;
  }

  getFilterStatus(){
    return this.selectedd
  }

  // Login
  validatelogin(user:UserDetails) {
    return this.http.post<Common>("http://localhost:8080/userLogin/Access",user);
  }

  getCategory() {
    return this.http.get<Common>('http://localhost:8080/ticket/values');
  }

//Customer View
  addTicket(ticket: any) {
    const encryptedData1 = sessionStorage.getItem('customerId') || ' ';
    return this.http.post<Common>(`http://localhost:8080/ticket/addticket/${encryptedData1}`, ticket)
  }

  getCustomerTicketDetails() {
    const encryptedData1 = sessionStorage.getItem('customerId') || ' ';
    return this.http.get<Common>(`http://localhost:8080/customer/getCustomerTicketDetails/${encryptedData1}`)
  }

  // Agent Section 

  // To get the tickets Which belongs to Agents Category
  getTicket() {
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    return this.http.get<Common>(`http://localhost:8080/agents/categorytickets/${encryptedData1}`)
  }
  // Single Api to call Open and Closed Tickets
  getFilteredTickets(filter:any){
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    return this.http.post<Common>(`http://localhost:8080/agents/getfilteredtickets/${encryptedData1}`,filter)
  }

  getSearch(search: SearchCriteria) {
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    return this.http.post<Common>(`http://localhost:8080/agents/search/${encryptedData1}`, search)
  }

  getAgentList() {
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    return this.http.get<Common>(`http://localhost:8080/agents/agentsCategory/${encryptedData1}`)
  }

  I: any;
  assignTickets(agentId: number) {
    this.I = agentId;
    const storedValue = sessionStorage.getItem("particularTicketId");
    const partTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.put<Common>(`http://localhost:8080/agents/${partTicketId}/assign/${this.I}`, { responseType: 'text' })
  }

  getAssiginedTickets() {
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    return this.http.get<Common>(`http://localhost:8080/agents/assignedTickets/${encryptedData1}`)
  }

  closeTicketss() {
    const storedValue = sessionStorage.getItem("customerTicketId");
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    return this.http.put<Common>(`http://localhost:8080/agents/${storedValue}/closedticket/${encryptedData1}`, { responseType: 'text' })
  }


// Agent
 

  getSpecificTicket() {
    const storedValue = sessionStorage.getItem("particularTicketId");
    const partTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.get<Common>(`http://localhost:8080/agents/specificticket/${partTicketId}`)
  }



  getTicketChat() { 
    const storedValue = sessionStorage.getItem("agentChatTicketId");
    const agentId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.get<Common>(`http://localhost:8080/agents/specificticket/${agentId}`)
  }
  
 





// Customer View
  getCustomerSingleTicketsDetails() {
    const storedValue = sessionStorage.getItem("customerViewTicketId");
    const custTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    // this.customerTicket
    return this.http.get<Common>(`http://localhost:8080/ticket/getTicket/${custTicketId}`)
  }

  getCustomerSearchTickets(search: SearchCriteria){
    const encryptedData1 = sessionStorage.getItem('customerId') || ' ';
    return this.http.post<Common>(`http://localhost:8080/customer/search/${encryptedData1}`,search)
  
  }

  // Added New To get Open Tickets


 
  // Customer Setting Ticket Rating
  setTicketRating(rating: number) {

    const storedValue = sessionStorage.getItem("customerViewTicketId");
    const custTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    // this.customerTicket
    return this.http.put<Common>(`http://localhost:8080/customer/setSatisfactoryRating/${custTicketId}/${rating}`, {})
  }



  // Customer Functionalities Filter Open And Closed Tickets

  getCustomerFilterTickets(filter: any) {
    const encryptedData1 = sessionStorage.getItem('customerId') || ' ';
    return this.http.post<Common>(`http://localhost:8080/customer/filtercustomertickets/${encryptedData1}`, filter)
  }

  //SuperVisor Section

  getSolvedUnsolvedTickets() {
    return this.http.get<Common>("http://localhost:8080/supervisor/agents/alltickets")
  }

  getProiorityCount() {
    return this.http.get<Common>("http://localhost:8080/supervisor/priotityCounts")
  }

  getSupervisorOpenAssigined() {
    return this.http.get<Common>('http://localhost:8080/supervisor/report/percategoriestickets')
  }
  // /report/percategoriestickets


  getTicketVolumes() {
    return this.http.get<Common>("http://localhost:8080/supervisor/report/tickets")
  }



  generateHtmlReportBetweenDates(start: Date, end: Date) {
    return this.http.get<Common>(`http://localhost:8080/supervisor/date/${start}/${end}`)
  }

  // Comment Section

  getChatMessage(chat: Chat) {
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const storeValue = sessionStorage.getItem("agentChatTicketId");
    const agentTicket = storeValue !== null ? parseInt(storeValue, 10) : 0;
    
    return this.http.post<Common>(`http://localhost:8080/comment/addcomments/${encryptedData1}/${agentTicket}`, chat)
  }


  addAgentChatMessage(chat: Chat) {
    // const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    // const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    // const agentId = parseInt(decryptedData, 10);

    const storeValue = sessionStorage.getItem("agentChatTicketId");
    const agentTicket = storeValue !== null ? parseInt(storeValue, 10) : 0;
    
    return this.http.post<Common>(`http://localhost:8080/comment/addcomments/${encryptedData1}/${agentTicket}`,chat)
  }

  getAllTicketComments() {

    const storedValue = sessionStorage.getItem("particularTicketId");
    const partTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.get<Common>(`http://localhost:8080/comment/ticketcomments/${partTicketId}`)
  }
  // Customer Adding Comments
  setCustomerChat(chats: Chat) {
    // const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('customerId') || ' ';
    // const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    // const customerId = parseInt(decryptedData, 10);

    const storValue = sessionStorage.getItem("customerViewTicketId");
    const acustomerTicketId = storValue !== null ? parseInt(storValue, 10) : 0;
    return this.http.post<Common>(`http://localhost:8080/comment/addcustomercomments/${encryptedData1}/${acustomerTicketId}`, chats)
  }
  // Customer View
    getAgentCustomerChat() {
    const storedValue = sessionStorage.getItem("customerViewTicketId");
    const acustomerTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.get<Common>(`http://localhost:8080/comment/agentCustomerConversation/${acustomerTicketId}`)
  }
  

  getAgentCustomerChats() {
    const storedValue = sessionStorage.getItem("agentChatTicketId");
    const acustomerTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.get<Common>(`http://localhost:8080/comment/agentCustomerConversation/${acustomerTicketId}`)
  }

}

