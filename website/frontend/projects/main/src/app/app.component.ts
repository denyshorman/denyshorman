import { Component, OnInit } from '@angular/core';
import { PingPongService } from './services/ping-pong.service';

@Component({
  selector: 'dh-root',
  template: `<router-outlet></router-outlet>`,
  styles: [],
})
export class AppComponent implements OnInit {
  constructor(private pingPongService: PingPongService) {}

  ngOnInit(): void {
    this.pingPongService.ping().subscribe(pong => {
      console.log('Pong received at ' + pong?.time?.toISOString() ?? '?');
    });
  }
}
