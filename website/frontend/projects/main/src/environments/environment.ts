import { config } from './config';

export const environment = {
  ...config,
  production: false,
  apiUrl: 'http://localhost:8080',
};
