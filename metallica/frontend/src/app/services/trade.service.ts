import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Trade } from '../models/trade';
import { RefData } from '../models/RefData';
import { User } from '../services/user-management.service';
import { SearchCriteria } from '../models/search-criteria';
import { CommonUtilities } from '../models/common-utilities';


@Injectable()
export class TradeService {


  tradeURL = "/tradeservice/api/trade";

  constructor(private http: HttpClient) { }

  searchTrades(user: User,searchCriteria: SearchCriteria): Observable<Trade[]>{
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.set("Authorization", "Bearer " + user.access_token); 

    this.setformattedTradeDates(searchCriteria);

    let params = new URLSearchParams();
    for(let key in searchCriteria){
      if(searchCriteria[key] instanceof Date){
        continue;
      }
      params.set(key, searchCriteria[key]) 
    }
    let queryPraams = params.toString();
    console.log(queryPraams);
    return this.http.get<Trade[]>(this.tradeURL + "?" + queryPraams ,{headers: headers});
  }

  createTrade(user: User, trade: Trade): Observable<Trade>{
      let headers: HttpHeaders = new HttpHeaders();
      headers = headers.set("Authorization", "Bearer " + user.access_token); 
      return this.http.post<Trade>(this.tradeURL,trade,{headers: headers});
  }

  updateTrade(user: User, trade: Trade): Observable<Trade>{
      let headers: HttpHeaders = new HttpHeaders();
      headers = headers.set("Authorization", "Bearer " + user.access_token); 
      return this.http.put<Trade>(this.tradeURL + "/" + trade.tradeId ,trade,{headers: headers});
  }

  deleteTrade(user: User, trade: Trade): Observable<any>{
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.set("Authorization", "Bearer " + user.access_token); 
    return this.http.delete<Trade>(this.tradeURL + "/" + trade.tradeId,{headers: headers});
  }


  setformattedTradeDates(searchCriteria: SearchCriteria) {
    searchCriteria.toDate = CommonUtilities.formatDate(searchCriteria.toTradeDate);
    searchCriteria.fromDate = CommonUtilities.formatDate(searchCriteria.fromTradeDate);
  }

}
