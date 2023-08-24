// import { ComponentFixture, TestBed } from '@angular/core/testing';
// import { HttpClientJsonpModule, HttpClientModule } from '@angular/common/http';
// import { CustomerviewComponent } from './customerview.component';
// import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
// import { of, throwError } from 'rxjs';
// import { HttpErrorResponse } from '@angular/common/http';

// import { ApiService } from '../services/api.service'; // Import your service
// describe('CustomerviewComponent', () => {
//   let service:ApiService;
//   let component: CustomerviewComponent;
//   let fixture: ComponentFixture<CustomerviewComponent>;
//   // let httpMock:HttpTestingController;
//   beforeEach(() => {
//     TestBed.configureTestingModule({
//       providers:[ApiService,],
//       declarations: [CustomerviewComponent],
//       imports:[HttpClientModule]
//     });
//     fixture = TestBed.createComponent(CustomerviewComponent);
//     component = fixture.componentInstance;
//     component.ticketForm = new FormGroup({
//       // Define your form controls here
//       subject: new FormControl('', Validators.required),
//       description: new FormControl('', Validators.required),
//       priority: new FormControl('', Validators.required),
//       categoryName: new FormControl('', Validators.required),
//     });
//     service=TestBed.inject(ApiService);
//     fixture.detectChanges();
//   });

//   it('should create', () => {
//     expect(component).toBeTruthy();
//   });
//   // describe('addedValue()', () => {
//   //   let component: TicketComponent;
//   //   let api: ApiService;
  
//   //   beforeEach(() => {
//   //     TestBed.configureTestingModule({
//   //       declarations: [TicketComponent],
//   //       providers: [ApiService]
//   //     });
//   //     component = TestBed.createComponent(TicketComponent);
//   //     api = TestBed.inject(ApiService);
//   //   });

//   it('should call the api.addTicket() method with the value of the ticketForm', () => {
//     // Set the value of the ticketForm
//     component.ticketForm.setValue = {
//       subject: 'Test ticket',
//       description: 'This is a test ticket'
//     };

//     // Call the addedValue() function
//     component.addedValue();

//     // Expect the api.addTicket() method to be called
//     expect(service.addTicket).toHaveBeenCalledWith({
//       title: 'Test ticket',
//       description: 'This is a test ticket'
//     });
//   });

//   it('should reset the ticketForm after the api.addTicket() method is called', () => {
//     // Set the value of the ticketForm
//     component.ticketForm.value = {
//       title: 'Test ticket',
//       description: 'This is a test ticket'
//     };

//     // Call the addedValue() function
//     component.addedValue();

//     // Expect the ticketForm to be reset
//     expect(component.ticketForm.value).toEqual({});
//   });
// });
// //   it('should add ticket and reset form on success', () => {
// //     const mockResponse = { success: true, mssg: 'Ticket added successfully' };
// //     spyOn(service,'addTicket').and.returnValue(of(mockResponse));

// //     // Set form values
// //      component.ticketForm.setValue({
// //     subject: 'Mock Subject',
// //     description: 'Mock Description',
// //     priority: 'High', // Example priority value
// //     categoryName: 'Mock Category' // Example category name
// //   });


// //   fixture.detectChanges();


// //     component.addedValue();

// //     // Assertions
// //     expect(service.addTicket).toHaveBeenCalledWith(component.ticketForm.value);
// //     expect(component.ticketForm.value).toEqual(component.ticketForm.value); // Form should be reset
// //     // You can also test if the alert was called using spyOn(window, 'alert');
// //   });
// // });
