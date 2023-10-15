import { Category } from './../model/category';
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { AgentTicketsComponent } from './agent-tickets.component';
import { HttpClientModule } from '@angular/common/http';
import { PaginatePipe, PaginationService } from 'ngx-pagination';
import { ApiService } from '../services/api.service';


describe('AgentTicketsComponent', () => {
  let component: AgentTicketsComponent;
  let fixture: ComponentFixture<AgentTicketsComponent>;
  let service:ApiService
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AgentTicketsComponent,PaginatePipe],
      imports:[HttpClientModule],
      providers: [
        PaginationService, // Add PaginationService to the providers array
      ],
    });
    fixture = TestBed.createComponent(AgentTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    service = TestBed.inject(ApiService);

  //   spyOn(service, 'getAssiginedTickets').and.returnValue(
  //     of({
  //       success: true,
  //       data: [{ ticketId: 1, subject: 'Ticket 1' }, { ticketId: 2, subject: 'Ticket 2' }],
  //     })
  //   );
   });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  
  // it('should fetch assigned tickets and update properties', fakeAsync(() => {
  //   // Call the getTicket method
  //   component.getTicket();
  //   tick(); // Advance the virtual clock to allow for the asynchronous operation to complete

  //   // Check if agentAssiginedTickets and lengthTickets are updated correctly
  //   expect(component.agentAssiginedTickets).toEqual([
  //     { ticketId: 1, subject: 'Ticket 1',description:'hello 1',category:{categoryId:57},priority:'high',status:'open'},
  //     { ticketId: 2, subject: 'Ticket 2',description:'hello 2',category:{categoryId:55},priority:'low',status:'open' },
  //   ]);
  //   expect(component.lengthTickets).toBe(2);

  //   // Check if setTicketCount method of ApiService was called
  //   expect(service.setTicketCount).toHaveBeenCalledWith(2);
   });


