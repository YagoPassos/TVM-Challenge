import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientsComponent } from './pages/clients/clients.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'clients', component: ClientsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }