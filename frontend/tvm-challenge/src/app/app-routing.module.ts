import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from './pages/auth.guard.service';
import { ClientsComponent } from './pages/clients/clients.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'clients', component: ClientsComponent, canActivate: [AuthGuardService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
