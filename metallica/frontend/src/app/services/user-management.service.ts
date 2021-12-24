import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class UserManagementService {
  
  authURL = "/oauthserver/oauth/token";

  getUserUrl = "/oauthserver/api/user";

  user: User;

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string): Observable<AuthResponse>{

    let oauthClientUserName: string = 'metallica-client';
    let oauthClientSecret: string = 'metallica-client-secret';
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.set("Authorization", "Basic " + btoa(oauthClientUserName + ":" + oauthClientSecret)); 
    headers = headers.append("Content-Type", "application/x-www-form-urlencoded");

    let data = `grant_type=password&username=${username}&password=${password}`;   
    return this.http.post<AuthResponse>(this.authURL,data,{headers: headers});
  }

  getUserDetails(): Observable<GetUserResponse>{

    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.set("Authorization", "Bearer " + this.user.access_token); 
    return this.http.get<GetUserResponse>(this.getUserUrl,{headers: headers});

  }
  

  getUser(): User{
    return this.user;
  }

  setUser(user:User){
    this.user = user;
  }

}

export interface AuthResponse{

  access_token: string;
  
  token_type: string;
  
  refresh_token: string;
  
  expires_in: string;
  
  scope: string;

}


export class User{

  access_token: string;

  name: string
}

export class GetUserResponse{

  name: string
}
