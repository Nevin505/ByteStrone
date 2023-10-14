import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentchatComponent } from './agentchat.component';

describe('AgentchatComponent', () => {
  let component: AgentchatComponent;
  let fixture: ComponentFixture<AgentchatComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AgentchatComponent]
    });
    fixture = TestBed.createComponent(AgentchatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
