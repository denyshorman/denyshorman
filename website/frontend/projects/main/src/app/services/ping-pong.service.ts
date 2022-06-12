import { Injectable } from '@angular/core';
import { PingPongClient } from '../protocol/ping-pong.pbsc';
import { PingReply, PingRequest } from '../protocol/ping-pong.pb';
import { Timestamp } from '@ngx-grpc/well-known-types';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PingPongService {
  constructor(private pingPongClient: PingPongClient) {}

  ping(): Observable<PingReply> {
    const req = new PingRequest({ time: Timestamp.fromDate(new Date()) });
    return this.pingPongClient.ping(req);
  }
}
