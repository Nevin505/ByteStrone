import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentAssiginationComponent } from './agent-assigination.component';

describe('AgentAssiginationComponent', () => {
  let component: AgentAssiginationComponent;
  let fixture: ComponentFixture<AgentAssiginationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AgentAssiginationComponent]
    });
    fixture = TestBed.createComponent(AgentAssiginationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
