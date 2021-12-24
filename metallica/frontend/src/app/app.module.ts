import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatNativeDateModule } from '@angular/material';
import { FormsModule } from '@angular/forms'

import { AppComponent } from './app.component';
import { MaterialModule } from './material.module';
import { MarketDataTickerComponent } from './market-data-ticker/market-data-ticker.component';
import { TradeSearchComponent } from './trade-search/trade-search.component';
import { UserActionsComponent, LoginDialog } from './user-actions/user-actions.component';
import { HttpClientModule } from '@angular/common/http';
import { UserManagementService } from './services/user-management.service';
import { TradeService } from './services/trade.service';
import { RefdataService } from './services/refdata-service';
import { StompConfig, StompService } from '@stomp/ng2-stompjs';
//import * as SockJS from 'sockjs-client';


// export function socketProvider() {
//   return new SockJS('http://localhost:8192/notificationservice/notifications');
// }

const stompConfig: StompConfig = {
  // Which server?
  url: 'ws://localhost:8093/notifications',

  // Headers
  // Typical keys: login, passcode, host
  headers: {
    login: 'guest',
    passcode: 'guest',
  },

  // How frequent is the heartbeat?
  // Interval in milliseconds, set to 0 to disable
  heartbeat_in: 0, // Typical value 0 - disabled
  heartbeat_out: 20000, // Typical value 20000 - every 20 seconds

  // Wait in milliseconds before attempting auto reconnect
  // Set to 0 to disable
  // Typical value 5000 (5 seconds)
  reconnect_delay: 5000,

  // Will log diagnostics on console
  debug: true
};

@NgModule({
  declarations: [
    AppComponent,
    MarketDataTickerComponent,
    TradeSearchComponent,
    UserActionsComponent,
    LoginDialog
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    MatNativeDateModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    UserManagementService,
    TradeService,
    RefdataService,
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }
  ],
  bootstrap: [AppComponent],
  entryComponents: [LoginDialog]
})
export class AppModule { }


