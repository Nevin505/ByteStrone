import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerLoginPageComponent } from './customer-login-page/customer-login-page.component';
import { LandingComponent } from './landing/landing.component';
import { CustomerviewComponent } from './customerview/customerview.component';
import { CustomerShowTicketsComponent } from './customer-show-tickets/customer-show-tickets.component';
import { AgentLoginComponent } from './agent-login/agent-login.component';
import { AgentViewComponent } from './agent-view/agent-view.component';
import { AgentAssiginationComponent } from './agent-assigination/agent-assigination.component';
import { AgentTicketsComponent } from './agent-tickets/agent-tickets.component';
import { AgentchatComponent } from './agentchat/agentchat.component';
import { ViewChatsComponent } from './view-chats/view-chats.component';
import { LoginComponent } from './login/login.component';
import { SuperVisorComponent } from './super-visor/super-visor.component';


const routes: Routes = [
  // {path:'',component:LandingComponent},
  { path: 'customer', component: CustomerLoginPageComponent},
  { path: 'customerview', component:CustomerviewComponent},
  { path: 'ticketRegister', component:CustomerviewComponent},  
  { path: 'customerticketShow', component:CustomerShowTicketsComponent},
  { path: 'agentlogin', component:AgentLoginComponent},
  { path: 'agentView', component:AgentViewComponent}, 
  { path: 'agentTicketAssigination', component:AgentAssiginationComponent},
  { path: 'agentViewTickets', component:AgentTicketsComponent},
  { path: 'agentchat', component:AgentchatComponent},
  { path: 'customerchat', component:ViewChatsComponent},
  { path: 'supervisor', component:SuperVisorComponent},
  { path: '', component:LoginComponent}, 
  { path: '**', component:LoginComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
