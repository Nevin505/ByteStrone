import { UserDetails } from './../model/user-details';
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
import * as CryptoJS from 'crypto-js';
import { UserTicketDetails } from '../model/user-ticket-details';
import { Commcon } from '../model/commcon';

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

  // Login
  validatelogin(user:UserDetails) {
    return this.http.post("http://localhost:8080/userLogin/Access",user);
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

  getCategory() {
    return this.http.get<Category[]>('http://localhost:8080/ticket/values');
  }

//Customer View
  addTicket(ticket: any) {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('customerId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const userId = parseInt(decryptedData, 10);
    return this.http.post(`http://localhost:8080/ticket/addticket/${userId}`, ticket)
  }

  getCustomerTicketDetails() {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('customerId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const userId = parseInt(decryptedData, 10);
    return this.http.get<Commcon>(`http://localhost:8080/customer/getCustomerTicketDetails/${userId}`)
  }

  // Agent Section 
  getTicket() {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const agentId = parseInt(decryptedData, 10);
    return this.http.get(`http://localhost:8080/agents/categorytickets/${agentId}`)
  }
  // Single Api to call Open and Closed Tickets
  getFilteredTickets(filter:any){
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const agentId = parseInt(decryptedData, 10);
    return this.http.post(`http://localhost:8080/agents/getfilteredtickets/${agentId}`,filter)
  }

  I: any;
  assignTickets(agentId: any) {
    this.I = agentId;
    const storedValue = sessionStorage.getItem("particularTicketId");
    const partTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.put(`http://localhost:8080/agents/${partTicketId}/assign/${this.I}`, { responseType: 'text' })
  }

  closeTicketss() {
    const storedValue = sessionStorage.getItem("customerTicketId");
    const custTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const agentId = parseInt(decryptedData, 10);
    return this.http.put(`http://localhost:8080/agents/${custTicketId}/closedticket/${agentId}`, { responseType: 'text' })
  }


// Agent
  getAgentList() {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const userId = parseInt(decryptedData, 10);
    return this.http.get(`http://localhost:8080/agents/agentsCategory/${userId}`)
  }

  getSpecificTicket() {
    const storedValue = sessionStorage.getItem("particularTicketId");
    const partTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.get(`http://localhost:8080/agents/specificticket/${partTicketId}`)
  }

  getAssiginedTickets() {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const agentId = parseInt(decryptedData, 10);
    return this.http.get(`http://localhost:8080/agents/assignedTickets/${agentId}`)
  }

  getTicketChat() { 
    const storedValue = sessionStorage.getItem("agentChatTicketId");
    const agentId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.get<Ticket>(`http://localhost:8080/agents/specificticket/${agentId}`)
  }




// Customer View
  getCustomerSingleTicketsDetails() {
    const storedValue = sessionStorage.getItem("customerViewTicketId");
    const custTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    // this.customerTicket
    return this.http.get(`http://localhost:8080/ticket/getTicket/${custTicketId}`)
  }

  getCustomerSearchTickets(search: SearchCriteria){
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('customerId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const userId = parseInt(decryptedData, 10);
    // this.customerTicket
    return this.http.post(`http://localhost:8080/customer/search/${userId}`,search)
  
  }

  // Added New To get Open Tickets


  getSearch(search: SearchCriteria) {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const agentId = parseInt(decryptedData, 10);
    //  this.agentId
    return this.http.post(`http://localhost:8080/agents/search/${agentId}`, search)
  }


  // Customer Setting Ticket Rating
  setTicketRating(rating: number) {

    const storedValue = sessionStorage.getItem("customerViewTicketId");
    const custTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    // this.customerTicket
    return this.http.put(`http://localhost:8080/customer/setSatisfactoryRating/${custTicketId}/${rating}`, {})
  }



  // Customer Functionalities Filter Open And Closed Tickets

  getCustomerFilterTickets(filter: any) {

    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('customerId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const customerId = parseInt(decryptedData, 10);
    return this.http.post(`http://localhost:8080/customer/filtercustomertickets/${customerId}`, filter)
  }

  //SuperVisor Section

  getSolvedUnsolvedTickets() {
    return this.http.get("http://localhost:8080/supervisor/agents/alltickets")
  }

  getProiorityCount() {
    return this.http.get("http://localhost:8080/supervisor/priotityCounts")
  }

  getSupervisorOpenAssigined() {
    return this.http.get('http://localhost:8080/supervisor/report/percategoriestickets')
  }
  // /report/percategoriestickets


  getTicketVolumes() {
    return this.http.get("http://localhost:8080/supervisor/report/tickets")
  }



  generateHtmlReportBetweenDates(start: Date, end: Date) {
    return this.http.get(`http://localhost:8080/supervisor/date/${start}/${end}`)
  }

  // Comment Section

  getChatMessage(chat: Chat) {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const agentId = parseInt(decryptedData, 10);

    const storeValue = sessionStorage.getItem("agentChatTicketId");
    const agentTicket = storeValue !== null ? parseInt(storeValue, 10) : 0;
    
    return this.http.post(`http://localhost:8080/comment/addcomments/${agentId}/${agentTicket}`, chat)
  }


  addAgentChatMessage(chat: Chat) {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const agentId = parseInt(decryptedData, 10);

    const storeValue = sessionStorage.getItem("agentChatTicketId");
    const agentTicket = storeValue !== null ? parseInt(storeValue, 10) : 0;
    
    return this.http.post(`http://localhost:8080/comment/addcomments/${agentId}/${agentTicket}`,chat)
  }

  getAllTicketComments() {

    const storedValue = sessionStorage.getItem("particularTicketId");
    const partTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.get(`http://localhost:8080/comment/ticketcomments/${partTicketId}`)
  }
  // Customer Adding Comments
  setCustomerChat(chats: Chat) {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('customerId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const customerId = parseInt(decryptedData, 10);

    const storValue = sessionStorage.getItem("customerViewTicketId");
    const acustomerTicketId = storValue !== null ? parseInt(storValue, 10) : 0;
    return this.http.post(`http://localhost:8080/comment/addcustomercomments/${customerId}/${acustomerTicketId}`, chats)
  }
  // Customer View
    getAgentCustomerChat() {
    const storedValue = sessionStorage.getItem("customerViewTicketId");
    const acustomerTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.get(`http://localhost:8080/comment/agentCustomerConversation/${acustomerTicketId}`)
  }
  

  getAgentCustomerChats() {
    const storedValue = sessionStorage.getItem("agentChatTicketId");
    const acustomerTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.get(`http://localhost:8080/comment/agentCustomerConversation/${acustomerTicketId}`)
  }

}

