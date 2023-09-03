import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { customerguardGuard } from './customerguard.guard';

describe('customerguardGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => customerguardGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
