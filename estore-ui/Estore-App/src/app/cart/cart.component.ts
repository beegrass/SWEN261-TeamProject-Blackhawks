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
  totalCost : number = 0;
  
  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private customerService : CustomerService,
    private loginComponent : LoginComponent,
    private loginService : LoginService
  ) { }

  ngOnInit(): void {
    this.getCart();
    console.log("this is what is printing on intialization")
    console.log(this.cart)

  }

  get getCustId():number {
    return this.loginService.customerId
  }
  
  set updateTheCartTest(cart : Array<Jersey>){
    this.cart = cart; 
  }

  get getTheCartTest() : Array <Jersey> {
    return this.cart;
  } 

  /**
   * This gets the cart of the current user that is logged in 
   */
   getCart(): void {
    this.customerService.getCart(this.getCustId)
    .subscribe(Jerseys => this.cart = Jerseys);
  }

  removeJerseyFromCart(jersey: Jersey): void {
    console.warn(this.cart)
    if(window.confirm('Are you sure you want to remove this jersey?') == true) {
      let customerId : number = this.getCustId
      this.cart = this.cart.filter(h => h !== jersey);
      this.customerService.removeJerseyFromCart(customerId, jersey)
      .subscribe( updatedCustomer => {
        this.customerService.getCart(updatedCustomer.id).subscribe(Jerseys => this.cart = Jerseys)
      }
    );
    //this.ngOnInit()
    console.warn("this is the cart after the delete")
    console.log(this.cart)

    console.warn("this is the cart when calling the method getCart() on init")
    this.getCart()
    console.warn(this.cart)
  }

  }



}
