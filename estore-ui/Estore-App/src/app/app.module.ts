import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { InventoryComponent } from './inventory/inventory.component';

import { LoginComponent } from './login/login.component';

import { Router, RouterModule } from '@angular/router';
import { JerseysComponent } from './jerseys/jerseys.component';
import { MessagesComponent } from './messages/messages.component';
import { JerseySearchComponent } from './jersey-search/jersey-search.component';
import { JerseyDetailComponent } from './jersey-detail/jersey-detail.component';

import { AdminDetailComponent } from './admin-detail/admin-detail.component';


@NgModule({
  declarations: [
    AppComponent,
    CartComponent,
    CheckoutComponent,
    LoginComponent,
    JerseysComponent,
    MessagesComponent,
    JerseyDetailComponent,
    JerseySearchComponent,
    CartComponent,
    CheckoutComponent,
    JerseysComponent,
    InventoryComponent,

    AdminDetailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    FormsModule,
    HttpClientModule,
  ],

  bootstrap: [ AppComponent ]
})

export class AppModule { }