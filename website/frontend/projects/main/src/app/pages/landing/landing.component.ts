import { Component } from '@angular/core';
import { SocialService } from '../../services/social.service';

@Component({
  selector: 'dh-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss'],
})
export class LandingComponent {
  contacts: any;

  constructor(private socialService: SocialService) {
    this.contacts = socialService.contacts;
  }
}
