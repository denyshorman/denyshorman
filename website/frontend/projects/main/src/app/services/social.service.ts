import { Injectable } from '@angular/core';
import {
  faHackerrank,
  faTelegram,
  faGithub,
  faGitlab,
  faTwitter,
  faLinkedin,
  faSkype,
} from '@fortawesome/free-brands-svg-icons';
import { faEnvelope } from '@fortawesome/free-solid-svg-icons';

@Injectable({
  providedIn: 'root',
})
export class SocialService {
  contacts = [
    {
      name: 'Email',
      value: 'mailto:denys@horman.tech',
      enabled: true,
      icon: faEnvelope,
    },
    {
      name: 'LinkedIn',
      value: 'https://linkedin.com/in/denyshorman',
      enabled: true,
      icon: faLinkedin,
    },
    {
      name: 'Twitter',
      value: 'https://twitter.com/denyshorman',
      enabled: true,
      icon: faTwitter,
    },
    {
      name: 'Github',
      value: 'https://github.com/denyshorman',
      enabled: true,
      icon: faGithub,
    },
    {
      name: 'Gitlab',
      value: 'https://gitlab.com/denyshorman',
      enabled: false,
      icon: faGitlab,
    },
    {
      name: 'Telegram',
      value: 'https://t.me/denyshorman',
      enabled: true,
      icon: faTelegram,
    },
    {
      name: 'Skype',
      value: 'skype:live:fa79ffe04e1fa024_1',
      enabled: false,
      icon: faSkype,
    },
    {
      name: 'HackerRank',
      value: 'https://hackerrank.com/denyshorman',
      enabled: false,
      icon: faHackerrank,
    },
  ];
}
