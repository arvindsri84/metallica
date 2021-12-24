import { Injectable } from "@angular/core";
import { RefData } from "../models/refdata";
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { User } from "./user-management.service";

@Injectable()
export class RefdataService{

    comodities = <RefData[]>[];

    counterparties = <RefData[]>[];
  
    locations = <RefData[]>[];
      

     
    comodityURL = "/refdataservice/api/ref/commodity";

    locationURL = "/refdataservice/api/ref/location";
  
    counterpartyURL = "/refdataservice/api/ref/counterparty"

    constructor(private http: HttpClient){}

    getRefdata(user: User){

        this.loadData(user,this.comodityURL).subscribe(
            result => {
                this.comodities = result;
            },
            error => {
                console.log("Error occurred while getting commodities",error);
            }
            );

            this.loadData(user,this.locationURL).subscribe(
            result => {
                this.locations = result;
            },
            error => {
                console.log("Error occurred while getting locations",error);
            }
            );

            this.loadData(user,this.counterpartyURL).subscribe(
            result => {
                this.counterparties = result;
            },
            error => {
                console.log("Error occurred while getting counterparties",error);
            }
        );
    }

    loadData(user: User,url: string): Observable<RefData[]>{
        let headers: HttpHeaders = new HttpHeaders();
        headers = headers.set("Authorization", "Bearer " + user.access_token); 
        return this.http.get<RefData[]>(url,{headers: headers});
        
    }


    getComodityDescription(code: string): string{
        if(this.comodities == null || this.comodities.length <=0 ){
            return "";
        }
        let data = this.comodities.find( c => {
            return c.code == code;
        });
        return data.description;
    }
    
}

