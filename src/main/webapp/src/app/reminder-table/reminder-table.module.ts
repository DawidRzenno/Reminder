import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReminderTableComponent } from './reminder-table.component';
import { ReminderComponent } from './reminder/reminder.component';
import {FormsModule} from "@angular/forms";
import { TimerComponent } from './reminder/timer/timer.component';

@NgModule({
  declarations: [
    ReminderTableComponent,
    ReminderComponent,
    TimerComponent
  ],
  exports: [
    ReminderTableComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ]
})
export class ReminderTableModule { }

