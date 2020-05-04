import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ReminderTableService {

  readonly serverAddress = 'http://192.168.1.2:8080' // TODO: temporary

  constructor(private http: HttpClient) { }

  getReminders() {
    return this.http.get(`${this.serverAddress}/reminder`);
  }

  getReminder(id: string) {
    return this.http.get(`${this.serverAddress}/reminder/${id}`);
  }
}
