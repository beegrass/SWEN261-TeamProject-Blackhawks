import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { Location } from '@angular/common';
import { Jersey } from '../jersey';
import { CustomerService } from 'app/customer.service';
import { LoginComponent } from 'app/login/login.component';
import { Customer } from 'app/customer';
import { LoginService } from 'app/login.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart : Jersey[] = [];
  
  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private customerService : CustomerService,
    private loginComponent : LoginComponent,
    private loginService : LoginService
  ) { }

  ngOnInit(): void {
    this.getCart();

  }

  getCustId():number {
    return this.loginService.customerId
  }

  /**
   * This gets the cart of the current user that is logged in 
   */
   getCart(): void {
    this.customerService.getCart(this.getCustId())
    .subscribe(Jerseys => this.cart = Jerseys);
  }



}
