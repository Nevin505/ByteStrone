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
import { customerguardGuard } from './shared/customerguard.guard';
import { ageentguardGuard } from './shared/ageentguard.guard';


const routes: Routes = [
  // {path:'',component:LandingComponent},
  { path: 'customerview', component:CustomerviewComponent,canActivate:[authGuard,customerguardGuard]},
  { path: 'ticketRegister', component:CustomerviewComponent,canActivate:[authGuard,customerguardGuard]},  
  { path: 'customerticketShow', component:CustomerShowTicketsComponent,canActivate:[authGuard,customerguardGuard]},
  { path: 'agentView', component:AgentViewComponent,canActivate:[authGuard,ageentguardGuard]}, 
  { path: 'agentTicketAssigination', component:AgentAssiginationComponent,canActivate:[authGuard,ageentguardGuard]},
  { path: 'agentViewTickets', component:AgentTicketsComponent,canActivate:[authGuard,ageentguardGuard]},
  { path: 'agentchat', component:AgentchatComponent,canActivate:[authGuard,ageentguardGuard]},
  { path: 'customerchat', component:ViewChatsComponent,canActivate:[authGuard,customerguardGuard]},
  { path: 'supervisor', component:SuperVisorComponent,canActivate:[authGuard]},
  {path:'landingPage',component:LoginComponent},
  { path: '', component:LoginComponent}, 
  { path: '**', component:LoginComponent,canActivate:[authGuard]}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
