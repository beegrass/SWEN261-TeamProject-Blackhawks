import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CartComponent } from './cart/cart.component';
import { JerseysComponent } from './jerseys/jerseys.component';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here

@NgModule({
  declarations: [
    AppComponent,
    CartComponent,
    JerseysComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
