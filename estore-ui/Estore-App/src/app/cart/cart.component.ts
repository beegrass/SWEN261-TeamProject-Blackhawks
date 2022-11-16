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

  /**
   * Not entirely sure  what this method will take in 
   * it should either take in the entire jersey or the jersey id 
   * 
   * gets the current customer from the logincomponent in order 
   * to utilize the customer servcice add Jersey to cart method
   * 
   * calls get cart at the end to update the current cart data 
   * @param jersey 
   */
  addToCart(jersey : Jersey): void{
    let customer : Customer = this.loginComponent.getCurrentCustomer(); 
    this.customerService.addJerseyToCart(customer, jersey)
      .subscribe(); // note for vince: not sure if you need to do suscribe to get cust obj
    
    this.getCart(); 
  }

  // getJerseys(): void {
  //   // loads the jerseys into the cart 
  //   for()
  // }



}
