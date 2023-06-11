import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChosePageComponent } from './chose-page/chose-page.component';
import { CustomerComponent } from './customer/customer.component';
import { AgentComponent } from './agent/agent.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { AgentLoginComponent } from './agent-login/agent-login.component';
import { AgentdisplayComponent } from './agentdisplay/agentdisplay.component';
import { ShowDetailsComponent } from './show-details/show-details.component';


const routes: Routes = [
{ path: 'home', component: ChosePageComponent },
{ path: 'agentlogin', component: AgentLoginComponent },
{ path: 'agent', component: AgentComponent },  
{ path: 'ticketraisse', component: CustomerComponent},
{ path: 'customerlogin', component: CustomerLoginComponent},
{ path: '', component:ChosePageComponent },
{ path: 'ticketShow', component:ShowDetailsComponent },

// { path: 'agentreoute', component: AgentdisplayComponent},
// { path: '**', redirectTo: 'home', pathMatch: 'full' },
{path:'agentDisplay',component:AgentdisplayComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
