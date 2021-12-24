import { Component, OnInit } from '@angular/core';
import { StompService } from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import { MarketDataTicker } from '../models/market-data-ticker';
import { UserManagementService } from '../services/user-management.service';
import { RefdataService } from '../services/refdata-service';

@Component({
  selector: 'app-market-data-ticker',
  templateUrl: './market-data-ticker.component.html',
  styleUrls: ['./market-data-ticker.component.scss']
})
export class MarketDataTickerComponent implements OnInit {

  tickerData: MarketDataTicker[] = [];

  constructor(
    private stompService: StompService,
    private usermanagementService: UserManagementService,
    public refdataService: RefdataService
  ) { }

  ngOnInit() {
    let marketDataStream = this.stompService.subscribe('/topic/market');

    marketDataStream.subscribe((message: Message) => {
      console.log(`Received: ${message.body}`);
      let user = this.usermanagementService.getUser();
      
      if(user != null){
        let tickerMessage = <MarketDataTicker>JSON.parse(message.body);
        tickerMessage.description = this.refdataService.getComodityDescription(tickerMessage.code);
        
        if( tickerMessage.description != "" ){
          if(this.tickerData.length > 4){
            this.tickerData.pop();
          }
          this.tickerData.unshift(tickerMessage); 
        }  
      }
    });
  }

}
