import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReminderTableComponent } from './reminder-table.component';
import { ReminderComponent } from './reminder/reminder.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    ReminderTableComponent,
    ReminderComponent
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

