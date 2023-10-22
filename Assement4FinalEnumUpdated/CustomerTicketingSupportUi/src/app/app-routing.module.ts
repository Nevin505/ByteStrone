import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './landing/landing.component';
import { CustomerviewComponent } from './customerview/customerview.component';
import { CustomerShowTicketsComponent } from './customer-show-tickets/customer-show-tickets.component';
import { AgentViewComponent } from './agent-view/agent-view.component';
import { AgentAssiginationComponent } from './agent-assigination/agent-assigination.component';
import { AgentTicketsComponent } from './agent-tickets/agent-tickets.component';
import { AgentchatComponent } from './agentchat/agentchat.component';
import { ViewChatsComponent } from './view-chats/view-chats.component';
import { LoginComponent } from './login/login.component';
import { SuperVisorComponent } from './super-visor/super-visor.component';
import { guardGuard } from './shared/guard.guard';


const routes: Routes = [
  // {path:'',component:LandingComponent},
  { path: 'customerview', component:CustomerviewComponent,canActivate:[guardGuard],data: { roles: 'Customer' }},
  { path: 'ticketRegister', component:CustomerviewComponent,canActivate:[guardGuard],data: { roles: 'Customer' }},  
  { path: 'customerticketShow', component:CustomerShowTicketsComponent,canActivate:[guardGuard],data: { roles: 'Customer'}},
  { path: 'agentView', component:AgentViewComponent,canActivate:[guardGuard],data: { roles: 'Agent' }}, 
  { path: 'agentTicketAssigination', component:AgentAssiginationComponent,canActivate:[guardGuard],data: { roles: 'Agent' }},
  { path: 'agentViewTickets', component:AgentTicketsComponent,canActivate:[guardGuard],data: { roles: 'Agent' }},
  { path: 'agentchat', component:AgentchatComponent,canActivate:[guardGuard],data: { roles: 'Agent' }},
  { path: 'customerchat', component:ViewChatsComponent,canActivate:[guardGuard],data: { roles: 'Customer' }},
  { path: 'supervisor', component:SuperVisorComponent,canActivate:[guardGuard],data: { roles: 'Supervisor' }},
  {path:'landingPage',component:LoginComponent},
  { path: '', component:LoginComponent}, 
  { path: '**', component:LoginComponent,canActivate:[guardGuard]}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
