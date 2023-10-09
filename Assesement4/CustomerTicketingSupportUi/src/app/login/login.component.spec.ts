import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login.component';
import { ReactiveFormsModule ,FormBuilder  } from '@angular/forms';

import { Router } from '@angular/router';
import { of } from 'rxjs';

import { ApiService } from '../services/api.service'; // Import your ApiService
describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [HttpClientModule,],
    });
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  describe('LoginComponent', () => {
    let component: LoginComponent;
    let fixture: ComponentFixture<LoginComponent>;
    let mockApiService: jasmine.SpyObj<ApiService>;
    let mockRouter: jasmine.SpyObj<Router>;
  
    beforeEach(() => {
      mockApiService = jasmine.createSpyObj('ApiService', ['validatelogin']);
      mockRouter = jasmine.createSpyObj('Router', ['navigate']);
  
      TestBed.configureTestingModule({
        declarations: [LoginComponent],
        imports: [ReactiveFormsModule],
        providers: [
          FormBuilder,
          { provide: ApiService, useValue: mockApiService },
          { provide: Router, useValue: mockRouter }
        ]
      });
  
      fixture = TestBed.createComponent(LoginComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  
    // it('should login agent and navigate to agentView', () => {
    //   // Mock the response from ApiService
    //   const mockResponse = {
    //     success: true,
    //     data: { id: 123, role: 'Agent', userName: 'agentName', category: [] }
    //   };
    //   mockApiService.validatelogin.and.returnValue(of(mockResponse));
  
    //   // Trigger the login function
    //   component.loginAgent();
  
    //   // Assertions
    //   expect(sessionStorage.getItem('token')).toBe('t');
    //   expect(sessionStorage.getItem('AgentId')).toBeTruthy();
    //   expect(mockRouter.navigate).toHaveBeenCalledWith(['agentView']);
    // });
  
    // it('should handle login failure', () => {
    //   // Mock the response from ApiService
    //   const mockResponse = { success: false, mssg: 'Login failed' };  
    //   mockApiService.validatelogin.and.returnValue(of(mockResponse));
  
    //   // Trigger the login function
    //   component.loginAgent();
  
    //   // Assertions
    //   expect(sessionStorage.getItem('token')).toBeNull();
    //   expect(mockRouter.navigate).not.toHaveBeenCalled();
    //   // You can also test that the alert is displayed or not using spyOn(window, 'alert');
    // });
  });
});
