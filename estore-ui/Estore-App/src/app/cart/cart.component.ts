import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { Location } from '@angular/common';
import { Cart } from '../cart';
import { Jersey } from '../jersey';
import { CustomerService } from 'app/customer.service';
import { LoginComponent } from 'app/login/login.component';
import { Customer } from 'app/customer';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart : Array<Jersey> = [];
  
  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private customerService : CustomerService,
    private loginComponent : LoginComponent
  ) { }

  ngOnInit(): void {
    this.getCart();

  }

  /**
   * This gets the cart of the current user that is logged in 
   */
  getCart() : void {
    // gets the cart
    // needs to access the login component in order to get the current 
    this.cart = this.loginComponent.getCurrentCustomerCart()
  }


  // getJerseys(): void {
  //   // loads the jerseys into the cart 
  //   for()
  // }



}
