import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { Location } from '@angular/common';
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

  cart : Jersey[] = [];
  
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
   getCart(): void {
    this.customerService.getCart(1)
    .subscribe(Jerseys => this.cart = Jerseys);
  }



}
