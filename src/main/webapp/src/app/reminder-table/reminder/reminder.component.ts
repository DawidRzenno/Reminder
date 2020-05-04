import {Component, Input, OnInit} from '@angular/core';
import {Reminder} from './reminder';

@Component({
  selector: 'app-reminder',
  templateUrl: './reminder.component.html',
  styleUrls: ['./reminder.component.scss']
})
export class ReminderComponent implements OnInit {

  @Input() reminder: Reminder;
  whenFinished: any; // TODO: calculate reminder.targetTime + reminder.targetDate for TimerComponent

  constructor() { }

  ngOnInit(): void {}

}
