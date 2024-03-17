import { TestBed } from '@angular/core/testing';

import { NoRoleGuard } from './no-role.guard';

describe('NoRoleGuard', () => {
  let guard: NoRoleGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(NoRoleGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
