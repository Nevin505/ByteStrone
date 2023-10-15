import { ComponentFixture, TestBed, fakeAsync } from '@angular/core/testing';

import { AgentAssiginationComponent } from './agent-assigination.component';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';
import { ApiService } from '../services/api.service';
import { of } from 'rxjs';


describe('AgentAssiginationComponent', () => {
  let component: AgentAssiginationComponent;
  let fixture: ComponentFixture<AgentAssiginationComponent>;
  let service:ApiService
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AgentAssiginationComponent],
      imports:[HttpClientModule],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({ /* your route parameters here */ }),
            },
          },
        },
      ],
    });
    fixture = TestBed.createComponent(AgentAssiginationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load ticket data on ngOnInit', () => {
    // Mock the response from the ApiService
    const mockResponse = {
      success: true,
      data: {
        categoryId: {
          categoryId: 101,
          category_name: 'Category Name',
        },
      },
      mssg: 'This is a success message', // Add the required 'mssg' property
    };
    
    spyOn(service, 'getSpecificTicket').and.returnValue(of(mockResponse));

    // Call ngOnInit
    component.ngOnInit();

    // Check that ticket data is set correctly
    // expect(component.ticketDa).toEqual(mockResponse.data);
    expect(component.ticketCategory).toEqual(mockResponse.data.categoryId.category_name);
  });

 

});
