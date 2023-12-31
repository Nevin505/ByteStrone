import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentViewComponent } from './agent-view.component';

describe('AgentViewComponent', () => {
  let component: AgentViewComponent;
  let fixture: ComponentFixture<AgentViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AgentViewComponent]
    });
    fixture = TestBed.createComponent(AgentViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
