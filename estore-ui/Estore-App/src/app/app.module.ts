import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { InventoryComponent } from './inventory/inventory.component';

import { JerseysComponent } from './jerseys/jerseys.component';
import { MessagesComponent } from './messages/messages.component';
import { JerseySearchComponent } from './jersey-search/jersey-search.component';
import { JerseyDetailComponent } from './jersey-detail/jersey-detail.component';
import { CustDashboardComponent } from './cust-dashboard/cust-dashboard.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,

  ],
  declarations: [
    AppComponent,
    CustDashboardComponent,
    JerseysComponent,
    JerseyDetailComponent,
    MessagesComponent,
    JerseySearchComponent,
    CartComponent,
    CheckoutComponent,
    JerseysComponent,
    InventoryComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }