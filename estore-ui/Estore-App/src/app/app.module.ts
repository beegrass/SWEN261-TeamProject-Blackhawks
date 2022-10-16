import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CartComponent } from './cart/cart.component';
import { BrowseProductsComponent } from './browse-products/browse-products.component';

@NgModule({
  declarations: [
    AppComponent,
    CartComponent,
    BrowseProductsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
