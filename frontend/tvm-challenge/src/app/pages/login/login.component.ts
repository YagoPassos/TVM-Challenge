import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router'
import { LoginService } from '../login.service';

interface User {
  id: number,
  username: string,
  password: string
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private baseUrl = "http://localhost:8080";


  constructor(private http: HttpClient, private router: Router, private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginService

    console.log(this.loginService.isLoggedIn)
  }

  token: string
  username: string;
  password: string;

  onSubmit() {

    const user = {
      username: this.username,
      password: this.password,
    }

    this.http.post(`${this.baseUrl}/login`, user).subscribe(results => {
      if (results) {
        this.loginService.isLoggedIn = true
        this.loginService.saveToken(results)
        this.router.navigate(['/clients'])
      }
    });
  }
}
