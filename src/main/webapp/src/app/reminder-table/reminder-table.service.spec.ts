import { TestBed } from '@angular/core/testing';

import { ReminderTableService } from './reminder-table.service';

describe('ReminderTableService', () => {
  let service: ReminderTableService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReminderTableService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
