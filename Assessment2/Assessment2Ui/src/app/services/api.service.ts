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

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  customerId!: number;
  agentId!: number;
  agentCategory!: string;


  constructor(private http: HttpClient) {

  }



  // customerIdGetter() {
  //   return this.customerId;
  // }
  // customerIdSetter(Id: number) {
  //   this.customerId = Id;
  // }

  // agentIdGetter() {
  //   return this.agentId;
  // }
  // agentIdSetter(Id: number) {
  //   this.agentId = Id;
  // }
  // agentCategoryGetter() {
  //   return this.agentCategory
  // }
  // agentCategorySetter(category: string) {
  //   this.agentCategory = category;
  // }

  // agentTicketId!: any;
  // agentTicketIdGetter() {
  //   return this.agentTicketId;
  // }
  // agentTicketIdSetter(Id: any) {
  //   this.agentTicketId = Id;
  // }
  // AgentName: String = '';

  // setAgentName(agentName: String) {
  //   this.AgentName = agentName;
  // }
  // getAgentName() {
  //   return this.AgentName;
  // }
  // agentcha!: any;
  // getagentChat() {
  //   return this.agentcha;
  // }
  // setagentChat(agechat: any) {
  //   this.agentcha = agechat;
  // }

  // particularTicketId!: any;

  // getparticularTicketId() {
  //   return this.particularTicketId
  // }
  // particularTicketSetter(Id: any) {
  //   this.particularTicketId = Id;
  // }
  // customerTicket!: any;


  // setCustomerTicket(ticketCustomerId: any) {
  //   this.customerTicket = ticketCustomerId;
  // }
  // getCustomerTicketInfo() {
  //   return this.customerTicket;
  // }



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
    return this.http.get(`http://localhost:8080/customer/getCustomerTicketDetails/${userId}`)
  }

  // agentloginService(agent: Agentlogin) {
  //   return this.http.post('http://localhost:8080/agents/Access', agent)
  // }

  //// Agent Section 
  getTicket() {
    // this.agentId
     // const storedValue = localStorage.getItem("agentUserId");
    // const agentId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const agentId = parseInt(decryptedData, 10);
    return this.http.get(`http://localhost:8080/agents/categorytickets/${agentId}`)
  }
  // Added New To get Open Tickets
  getOpenTicke() {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const agentId = parseInt(decryptedData, 10);
    return this.http.get(`http://localhost:8080/agents/getOpentickets/${agentId}`)
  }
  // Close Tickets
  getCloseFullTickets() {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const agentId = parseInt(decryptedData, 10);
    return this.http.get(`http://localhost:8080/agents/getCloseTickets/${agentId}`)
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
    //  http://localhost:8080/agents/${this.customerTicket}/closedticket/${this.agentId}`,{ responseType:'text' })
    return this.http.put(`http://localhost:8080/agents/${custTicketId}/closedticket/${agentId}`, { responseType: 'text' })
  }



  getAgentList() {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const userId = parseInt(decryptedData, 10);
    // const storedValue = localStorage.getItem("agentUserId");
    // const userId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    // // this.agentId
    return this.http.get(`http://localhost:8080/agents/agentsCategory/${userId}`)
  }

  getSpecificTicket() {
    const storedValue = sessionStorage.getItem("particularTicketId");
    const partTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    // http://localhost:8080/agents/specificticket/${this.particularTicketId}
    return this.http.get(`http://localhost:8080/agents/specificticket/${partTicketId}`)
  }

  getAssiginedTickets() {
    // this.agentId
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const agentId = parseInt(decryptedData, 10);
    return this.http.get(`http://localhost:8080/agents/assignedTickets/${agentId}`)
  }

  getTicketChat() {
    // agentChatId
    // this.http.get<Ticket>(`http://localhost:8080/agents/specificticket/${this.agentcha}`)
    const storedValue = sessionStorage.getItem("agentChatTicketId");
    const agentId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    return this.http.get<Ticket>(`http://localhost:8080/agents/specificticket/${agentId}`)
  }




// Customer View
  getCustomerSingleTicketsDetails() {
    const storedValue = localStorage.getItem("customerViewTicketId");
    const custTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    // this.customerTicket
    return this.http.get(`http://localhost:8080/ticket/getTicket/${custTicketId}`)
  }


  // getOpenTickets() {
  //   return this.http.get(`http://localhost:8080/agents/getOpenTickets/${this.agentId}`)
  // }
  // getClosedTickets() {
  //   return this.http.get(`http://localhost:8080/agents/getCloseTickets/${this.agentId}`)
  // }



  // Added New To get Open Tickets


  getSearch(search: SearchCriteria) {
    const secretKey = 'your-secret-key';
    const encryptedData1 = sessionStorage.getItem('AgentId') || ' ';
    const decryptedData = CryptoJS.AES.decrypt(encryptedData1, secretKey).toString(CryptoJS.enc.Utf8);
    const agentId = parseInt(decryptedData, 10);
    //  this.agentId
    return this.http.post(`http://localhost:8080/agents/search/${agentId}`, search)
  }



  // getTicketBetweenDays(start:Date,end:Date){
  //   return this.http.get(`http://localhost:8080/supervisor/date/${start}/${end}`)
  // }

  // Customer Setting Ticket Rating
  setTicketRating(rating: number) {

    const storedValue = localStorage.getItem("customerViewTicketId");
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

    // const storedValue = localStorage.getItem("customerUserId");
    // const customerId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    //  this.customerId

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

    const storedValue = localStorage.getItem("particularTicketId");
    const partTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    // http://localhost:8080/comment/ticketcomments/${this.particularTicketId}
    return this.http.get(`http://localhost:8080/comment/ticketcomments/${partTicketId}`)
  }
  // Customer Adding Comments
  setCustomerChat(chats: Chat) {
    const storedValue = localStorage.getItem("customerUserId");
    const customerId = storedValue !== null ? parseInt(storedValue, 10) : 0;

    const storValue = localStorage.getItem("customerViewTicketId");
    const acustomerTicketId = storValue !== null ? parseInt(storValue, 10) : 0;
    // this.customerId   this.customerTicket
    return this.http.post(`http://localhost:8080/comment/addcustomercomments/${customerId}/${acustomerTicketId}`, chats)
  }
  // Customer View
    getAgentCustomerChat() {
    const storedValue = localStorage.getItem("customerViewTicketId");
    const acustomerTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    // http://localhost:8080/comment/agentCustomerConversation/${ this.customerTicket}
    return this.http.get(`http://localhost:8080/comment/agentCustomerConversation/${acustomerTicketId}`)
  }

  getAgentCustomerChats() {
    const storedValue = sessionStorage.getItem("agentChatTicketId");
    const acustomerTicketId = storedValue !== null ? parseInt(storedValue, 10) : 0;
    // http://localhost:8080/comment/agentCustomerConversation/${ this.customerTicket}
    return this.http.get(`http://localhost:8080/comment/agentCustomerConversation/${acustomerTicketId}`)
  }

}
  // getSearchTicket(searchtickId:number){
  //   return this.http.get(`http://localhost:8080/agents/categorytickets/${this.agentId}/${searchtickId}`)
  // }


  // generateHtmlReport(date:Date){
  //   return this.http.get(`http://localhost:8080/supervisor/htmlreportdate/${date}`)
  // }

   // getSupervisorTicketPerDate(date:Date){
  //   return this.http.get(`http://localhost:8080/supervisor/date/${date}`)
  // }