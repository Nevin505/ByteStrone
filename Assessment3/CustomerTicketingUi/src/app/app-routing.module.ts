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
import { authGuard } from './shared/auth.guard';


const routes: Routes = [
  // {path:'',component:LandingComponent},
  { path: 'customerview', component:CustomerviewComponent,canActivate:[authGuard]},
  { path: 'ticketRegister', component:CustomerviewComponent,canActivate:[authGuard]},  
  { path: 'customerticketShow', component:CustomerShowTicketsComponent,canActivate:[authGuard]},
  { path: 'agentView', component:AgentViewComponent,canActivate:[authGuard]}, 
  { path: 'agentTicketAssigination', component:AgentAssiginationComponent,canActivate:[authGuard]},
  { path: 'agentViewTickets', component:AgentTicketsComponent,canActivate:[authGuard]},
  { path: 'agentchat', component:AgentchatComponent,canActivate:[authGuard]},
  { path: 'customerchat', component:ViewChatsComponent,canActivate:[authGuard]},
  { path: 'supervisor', component:SuperVisorComponent,canActivate:[authGuard]},
  {path:'landingPage',component:LoginComponent,canActivate:[authGuard]},
  { path: '', component:LoginComponent}, 
  { path: '**', component:LoginComponent,canActivate:[authGuard]}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
