import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

interface Client {
  id: number;
  name: string,
  email: string,
  phone: string,
}

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.scss']
})
export class ClientsComponent implements OnInit {

  private baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  clients: Client[];
  client_found: Client = { id: 0, name: '', email: '', phone: '' };
  isReady: boolean = false;
  search_id: number;
  id: number;
  name: string = ''
  email: String = ''
  phone: string = ''

  ngOnInit(): void {
    this.getClients().subscribe(e => {
      this.clients = e;
    });
  }

  public getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(`${this.baseUrl}/client`);
  }

  public getClientById(e: number) {
    return this.http.get<Client>(`${this.baseUrl}/client/${e}`)
  }

  public addClient() {
    const client = {
      name: this.name,
      email: this.email,
      phone: this.phone,
    }
    return this.http.post<Client[]>(`${this.baseUrl}/client`, client).subscribe(results => {
      this.getClients().subscribe(e => {
        this.clients = e;
      });

      this.closeModal('create-modal')
      this.name = ''
      this.email = ''
      this.phone = ''
    },
      erro => {
        if (erro.status == 400) {
          console.log(erro);
        }
      })


  }

  public updateUser() {

    const client = {
      id: this.id,
      name: this.name,
      email: this.email,
      phone: this.phone,
    }
    this.http.put(`${this.baseUrl}/client`, client).subscribe(results => {
      this.getClients().subscribe(e => {
        this.clients = e;
      });
    },
      erro => {
        if (erro.status == 400) {
          console.log(erro);
        }
      })

    this.closeModal('update-modal')
    this.name = ''
    this.email = ''
    this.phone = ''
    this.id = 0;
  }

  deleteUser(e: number) {
    console.log(e)
    return this.http.delete(`${this.baseUrl}/client/${e}`).subscribe(results => {
      this.getClients().subscribe(e => {
        this.clients = e;
      });
    },
      erro => {
        if (erro.status == 400) {
          console.log(erro);
        }
      })
  }

  closeModal(e: string) {
    if (e === 'update-modal') {
      this.name = ''
      this.email = ''
      this.phone = ''
    }
    document.getElementById(e).style.display = 'none'
  }

  openModal(e: string, id: number) {
    if (e === 'update-modal') {
      this.id = id;

      this.getClientById(this.id).subscribe(results => {
        this.name = results.name,
          this.email = results.email
        this.phone = results.phone
      })

    }

    document.getElementById(e).style.display = 'block'
  }

  findModal(e: number) {
    this.getClientById(e).subscribe(results => {
      this.client_found = results

      if (this.client_found) document.getElementById('find-modal').style.display = 'block'
      else this.client_found= { id: 0, name: '', email: '', phone: '' }
    })

  }
} 
