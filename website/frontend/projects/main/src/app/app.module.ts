import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LandingComponent } from './pages/landing/landing.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgxGoogleAnalyticsModule } from 'ngx-google-analytics';
import { environment } from '../environments/environment';
import { GrpcCoreModule } from '@ngx-grpc/core';
import { GrpcWebClientModule } from '@ngx-grpc/grpc-web-client';

@NgModule({
  declarations: [AppComponent, LandingComponent],
  imports: [
    GrpcCoreModule.forRoot(),
    GrpcWebClientModule.forRoot({
      settings: {
        host: environment.apiUrl,
        format: 'binary',
      },
    }),
    NgxGoogleAnalyticsModule.forRoot(environment.gaMeasurementId),
    BrowserModule,
    FontAwesomeModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
