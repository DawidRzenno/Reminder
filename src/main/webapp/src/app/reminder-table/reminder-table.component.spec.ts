import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReminderTableComponent } from './reminder-table.component';

describe('ReminderTableComponent', () => {
  let component: ReminderTableComponent;
  let fixture: ComponentFixture<ReminderTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReminderTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReminderTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
