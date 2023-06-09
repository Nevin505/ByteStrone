import { customer } from './../model/customer';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ticket } from '../model/ticket';
import { Category } from '../model/category';
import { Router } from '@angular/router';
import { Agentlogin } from '../model/agentlogin';
import { CustomerLoginComponent } from '../customer-login/customer-login.component';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  customerId!: number;


  merId!:number;
  idSetter(id:number){
    this.merId=id;
  }
  idGetter(){
    return this.merId;
  }
  // customerid!:number;
  aId!: number;
  // getAgentId(){
  //   this.agentId=this.agentId
  // }
  constructor(private httpClient: HttpClient, private route: Router) {

  }
  agentIdSetter(agentID:number){
     this.aId=agentID;
  }
  agentIdGetter(){
    return this.aId;
  }
  ngOnIt(){

  }
  // To Grant Login Based On the User Login
  userLoginAccess(login: any) {
    console.log(login + " from LoginService");
    this.httpClient.post<number>('http://localhost:8080/login/Access', login).subscribe(
      (response: number) => {
        if (response !== 0) {
          this.customerId = response;

          
          // alert(this.customerId);
          // console.log(this.customerId);         
          this.route.navigate(['ticketraisse'])
        } else {
          // Invalid username or password
          alert('Invalid username or password');
        }
      },
      (error) => {
        // Handle error
        console.error(error);
      }
    );
  }
   
  agentTickets(){
   return this.httpClient.get<Ticket>('http://localhost:8080/agents/tickets')
  }

  // To do the Login 
  agentloginService(agentlog: Agentlogin){
     return this.httpClient.post('http://localhost:8080/agents/Access',agentlog)
  }

  // agentloginService(agentlog: any) {
  //   console.log("Hi From Here");
  //   this.httpClient.post('http://localhost:8080/agents/Access',agentlog).subscribe(
  //     (response:any) => {
  //       if (response != 0) {
  //         this.agentId = response;
  //         console.log(this.agentId); 

  //         // sessionStorage.setItem('customerId',String(this.customerId));
  //         // alert(this.agentId);
  //         // console.log(this.customerId);         
  //         // this.route.navigate(['Customer'])

  //       } else {
  //         // Invalid username or password
  //         alert('Invalid username or password');
  //         this.route.navigate(['agentreoute']);
  //       }

  //     })
  // }



  // To Add Tickets which is being Raised By a Particular Customer
  createTicket(ticket: Ticket) {
    ticket.customerid = this.customerId
    console.log(ticket.customerid)
    return this.httpClient.post('http://localhost:8080/ticket/addticket/' + ticket.customerid, ticket);
  }
  //To List Of Categories
  getCategory() {
    return this.httpClient.get<Category[]>('http://localhost:8080/ticket/values');
  }

  getTicketList() {
    return this.httpClient.get<Ticket[]>('http://localhost:8080/ticket/query')
  }

  getTicket(agentid:number){
    // console.log(this.agentId)
    // ${this.agentId}
    return this.httpClient.get(`http://localhost:8080/agents/categorytickets/${agentid}`)
  }
  getSearchTickets(searchtickId:any){
    return this.httpClient.get(`http://localhost:8080/agents/specificticket/${searchtickId}`)
  }

  getCustomerTicketDetails(){
    return this.httpClient.get(`http://localhost:8080/customer/getCustomerTicketDetails/${this.customerId}`)
  }

}
