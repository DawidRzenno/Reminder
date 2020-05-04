import { Component, OnInit } from '@angular/core';
import {ReminderTableService} from "./reminder-table.service";

@Component({
  selector: 'app-reminder-table',
  templateUrl: './reminder-table.component.html',
  styleUrls: ['./reminder-table.component.scss']
})
export class ReminderTableComponent implements OnInit {

  reminderId: string;

  constructor(private reminderTableService: ReminderTableService) { }

  ngOnInit(): void {


  }

  getReminder(id: string) {
    this.reminderTableService.getReminder(id).subscribe({
      next: response => {
        console.log('reminder #' + id + ' =>', response);
      }
    });
  }

  getReminders() {
    this.reminderTableService.getReminders().subscribe({
      next: response => {
        console.log('reminders =>', response);
      }
    });
  }

}
