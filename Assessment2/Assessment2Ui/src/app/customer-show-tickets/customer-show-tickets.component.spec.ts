import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerShowTicketsComponent } from './customer-show-tickets.component';

describe('CustomerShowTicketsComponent', () => {
  let component: CustomerShowTicketsComponent;
  let fixture: ComponentFixture<CustomerShowTicketsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerShowTicketsComponent]
    });
    fixture = TestBed.createComponent(CustomerShowTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
