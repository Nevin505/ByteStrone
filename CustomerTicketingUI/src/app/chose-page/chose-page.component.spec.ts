import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChosePageComponent } from './chose-page.component';

describe('ChosePageComponent', () => {
  let component: ChosePageComponent;
  let fixture: ComponentFixture<ChosePageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChosePageComponent]
    });
    fixture = TestBed.createComponent(ChosePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
