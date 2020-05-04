import { Component, OnInit } from '@angular/core';
import { ReminderTableService } from './reminder-table.service';
import { Reminder } from './reminder/reminder';

class RestResponse {
  // tslint:disable:variable-name
  _embedded: any;
  _links: any;
}

@Component({
  selector: 'app-reminder-table',
  templateUrl: './reminder-table.component.html',
  styleUrls: ['./reminder-table.component.scss']
})
export class ReminderTableComponent implements OnInit {

  reminders: Reminder[];

  constructor(private reminderTableService: ReminderTableService) { }

  ngOnInit(): void {
    this.loadReminders();
  }

  loadReminders() {
    this.reminderTableService.getReminders().subscribe({
      next: (response: RestResponse) => {
        this.reminders = response._embedded.reminderList;
      },
      error: (response => console.error(response)),
      complete: (() => console.log('this.reminders = ', this.reminders))
    });
  }

}
