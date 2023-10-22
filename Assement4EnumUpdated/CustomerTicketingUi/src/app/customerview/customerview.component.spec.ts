import { ComponentFixture, TestBed, } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { CustomerviewComponent } from './customerview.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ApiService } from '../services/api.service'; // Import your service
import { inject } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
// import * as jasmine from 'jasmine';
describe('CustomerviewComponent', () => {
  let service:ApiService;
  
  let component: CustomerviewComponent;
  let fixture: ComponentFixture<CustomerviewComponent>;
  let httpMock:HttpTestingController;
  let mockApiService: jasmine.SpyObj<ApiService>;
  beforeEach(() => {
    // service = jasmine.createSpyObj<ApiService>("ApiService");
    // service=jasmine.createSpyObj("ApiService")
      TestBed.configureTestingModule({
      providers:[ApiService,{provide:ApiService,useValue:service}]   ,
      declarations: [CustomerviewComponent],
      imports:[ReactiveFormsModule,HttpClientTestingModule]
    });
    fixture = TestBed.createComponent(CustomerviewComponent);
    component = fixture.componentInstance;
    httpMock=TestBed.inject(HttpTestingController);
    service=TestBed.inject(ApiService);
    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
 
  // it("Should add tickets",()=>{
  //   service.addTicket
  // })

  // it('should alert a success message if the ticket is added successfully', () => {
  //   const api = jasmine.createSpyObj('ApiService', ['addTicket']);
  //   api.addTicket.and.returnValue({
  //     success: true,
  //     mssg: 'Ticket added successfully'
  //   });

  //   const ticketForm = jasmine.createSpyObj('ticketForm', ['value']);
  //   ticketForm.value.title = 'My new ticket';
  //   ticketForm.value.description = 'This is my new ticket description.';

  //   const component = new CustomerviewComponent(api, ticketForm);
  //   component.addedValue();

  //   expect(alert).toHaveBeenCalledWith('Ticket added successfully');
  // });

  // it('should call the API and reset the form on success', () => {

  //   const ticketFormValue = {
  //     subject: 'Sample Ticket Subject',
  //     description: 'Sample Ticket Description',
  //     priority: 'High', // Replace with the appropriate value
  //     categoryName: 'General', // Replace with the appropriate value
  //   };
    

  //   const successResponse = { success: true, mssg: 'Ticket added successfully' };

  //   // Call the method
  //   component.ticketForm.setValue(ticketFormValue); // Set the form value

  //   component.addedValue();

  //   // Expect an HTTP request to be made
  //   const req = httpMock.expectOne('`http://localhost:8080/ticket/addticket/${userId}`, ticket'); // Replace with your API endpoint
  //   expect(req.request.method).toBe('POST'); // Assuming you're making a POST request

  //   // Respond with a success response
  //   req.flush(successResponse);

  //   // Expect the form to be reset
  //   expect(component.ticketForm.value).toEqual({}); // Check if the form is reset
  //   expect(window.alert).toHaveBeenCalledWith('Ticket added successfully'); // Check if the alert is called
  // });

});




