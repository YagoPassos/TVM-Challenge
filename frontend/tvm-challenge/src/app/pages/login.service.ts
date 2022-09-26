import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})

export class LoginService {

  constructor(private http: HttpClient) { }

  token: any;
  isLoggedIn: boolean ;
  aux = 0;



  userIsLoggedIn() : Observable<boolean>{
    const loggedIn = of (!!this.token)
    return loggedIn;
  }

  getToken(): String {
    const token = localStorage.getItem('token')

      this.isLoggedIn = (!!token);
    
    return token;
  }

  saveToken(token): any {

    localStorage.setItem('token', (token.token))

    this.token = token;
  }



}
