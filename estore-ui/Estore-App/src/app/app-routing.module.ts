import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';

import { JerseysComponent } from './jerseys/jerseys.component';

import { JerseyDetailComponent } from './jersey-detail/jersey-detail.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { InventoryComponent } from './inventory/inventory.component';
import { AdminDetailComponent } from './admin-detail/admin-detail.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'detail/:id', component: JerseyDetailComponent },
  { path: 'jerseys', component: JerseysComponent },
  { path: 'cart', component: CartComponent},
  { path: 'checkout', component: CheckoutComponent},
  { path: 'inventory', component: InventoryComponent },
  { path: 'admin-detail/:id', component: AdminDetailComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
  
}
