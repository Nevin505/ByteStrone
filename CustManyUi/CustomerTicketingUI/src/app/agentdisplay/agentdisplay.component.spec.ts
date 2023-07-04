import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentdisplayComponent } from './agentdisplay.component';

describe('AgentdisplayComponent', () => {
  let component: AgentdisplayComponent;
  let fixture: ComponentFixture<AgentdisplayComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AgentdisplayComponent]
    });
    fixture = TestBed.createComponent(AgentdisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
