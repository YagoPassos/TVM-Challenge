import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router'

interface User{
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


  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {

  }

  username: string;
  password: string;

  // onSubmit(){

  //   const user = {
  //     username: this.username,
  //     password: this.password,
  //   }

  //   const results = this.http.post(`${this.baseUrl}/users/login`, user).subscribe(results => {
  //     return results
  //   });

  //   this.router.navigate(['/clients'])
  //   return this.http.post(`${this.baseUrl}/users/login`, user).subscribe()
  // }

}
