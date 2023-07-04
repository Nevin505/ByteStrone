import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ChosePageComponent } from './chose-page/chose-page.component';
import { CustomerComponent } from './customer/customer.component';
import { RouterModule } from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AgentComponent } from './agent/agent.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { AgentLoginComponent } from './agent-login/agent-login.component';
import { AgentdisplayComponent } from './agentdisplay/agentdisplay.component';
import { ShowDetailsComponent } from './show-details/show-details.component';



@NgModule({
  declarations: [
    AppComponent,
    ChosePageComponent,
    CustomerComponent,
    AgentComponent,
    CustomerLoginComponent,
    AgentLoginComponent,
    AgentdisplayComponent,
    ShowDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,RouterModule,HttpClientModule,FormsModule,ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
