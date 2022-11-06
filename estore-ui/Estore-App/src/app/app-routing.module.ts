import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { JerseysComponent } from './jerseys/jerseys.component';
import { CustDashboardComponent } from './cust-dashboard/cust-dashboard.component';
import { JerseyDetailComponent } from './jersey-detail/jersey-detail.component';
import { CartComponent } from './cart/cart.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'dashboard', component: CustDashboardComponent },
  { path: 'detail/:id', component: JerseyDetailComponent },
  { path: 'jerseys', component: JerseysComponent },
  // { path: '/customer/cart/?userId=:userId', component: CartComponent} //might be wrong

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
  
}
