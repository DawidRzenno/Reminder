import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-timer',
  templateUrl: './timer.component.html',
  styleUrls: ['./timer.component.scss']
})
export class TimerComponent implements OnInit {

  @Input() whenStarted: any;
  @Input() whenFinished: any;

  constructor() { }

  ngOnInit(): void {
    // TODO: calculate time left and update view on every second
  }

}
