import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingComponent } from './landing/landing.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CustomerviewComponent } from './customerview/customerview.component';
import { CustomerShowTicketsComponent } from './customer-show-tickets/customer-show-tickets.component';
import { AgentViewComponent } from './agent-view/agent-view.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { SearchPipe } from './pipes/search.pipe';
import { FilteropenPipe } from './pipes/filteropen.pipe';
import { FilterByStatusPipe } from './pipes/filter-by-status.pipe';
import { AgentAssiginationComponent } from './agent-assigination/agent-assigination.component';
import { AgentTicketsComponent } from './agent-tickets/agent-tickets.component';
import { AgentchatComponent } from './agentchat/agentchat.component';
import { ViewChatsComponent } from './view-chats/view-chats.component';
import { LoginComponent } from './login/login.component';
import { SuperVisorComponent } from './super-visor/super-visor.component';
import { DateorderPipe } from './pipes/dateorder.pipe';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    CustomerviewComponent,
    CustomerShowTicketsComponent,
    AgentViewComponent,
    SearchPipe,
    FilteropenPipe,
    FilterByStatusPipe,
    AgentAssiginationComponent,
    AgentTicketsComponent,
    AgentchatComponent,
    ViewChatsComponent,
    LoginComponent,
    SuperVisorComponent,
    DateorderPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    // NgChartsModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
