import { Component, OnInit, Input } from '@angular/core';
import { Jersey } from '../jersey';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { JerseyService } from '../jersey.service';
import { CartService } from 'app/cart.service';
import { Cart } from 'app/cart';
import { CartComponent } from 'app/cart/cart.component';
import { LoginComponent } from 'app/login/login.component';
import { Customer } from 'app/customer';
import { CustomerService } from 'app/customer.service';
import { LoginService } from 'app/login.service';

@Component({
  selector: 'app-jersey-detail',
  templateUrl: './jersey-detail.component.html',
  styleUrls: ['./jersey-detail.component.css']
})
export class JerseyDetailComponent implements OnInit {
  // @Input() jersey?: Jersey;
  jersey: Jersey | undefined;
  cart: Jersey[] = [];


  constructor(
    private route: ActivatedRoute,
    private jerseyService: JerseyService,
    private location: Location,
    protected cartService: CartService,
    private cartComponent : CartComponent, 
    private loginComponent : LoginComponent,
    private customerService : CustomerService,
    private loginService : LoginService
  ) {}

  ngOnInit(): void {
    this.getJersey();
  }

  goBack(): void {
    this.location.back();
  }

  getJersey(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.jerseyService.getJersey(id)
      .subscribe(jersey => {this.jersey = jersey
        console.log("this is the id of the jersey: " + jersey.id)});
  }

  getCart(): void {
    this.customerService.getCart(this.loginService.customerId)
      .subscribe(cart => this.cart = cart);
  }

  get getCustId():number {
    return this.loginService.customerId
  }

  // get getTotalCost():number {
  //   return this.customerService.totalCost
  // }

  // set setTotalCost(cost : number) {
  //   let total : number = 0
    

  // }

    /**
   * Not entirely sure  what this method will take in 
   * it should either take in the entire jersey or the jersey id 
   * 
   * gets the current customer from the login component in order 
   * to utilize the customer service add Jersey to cart method
   * 
   * calls get cart at the end to update the current cart data 
   * @param jersey 
   */
     addToCart(jersey : Jersey, quantity : string): void{
      let quantity_number  = parseInt(quantity)
      let customerId : number = this.getCustId
      for(let i = 0; i < quantity_number ; i++){
        this.customerService.addJerseyToCart(customerId, jersey)
        .subscribe( observer => 
          console.log("this is what returned after add to cart : " + observer)
        );
        this.cart.push(jersey);
      }

      this.customerService.getTotalCost(customerId).subscribe(
        total => {
          this.cartComponent.setTotalCost = total
          console.log("this is the total cost:" + total)
        }
      );

    }

  save(): void {
    if (this.jersey) {
      this.jerseyService.updateJersey(this.jersey)
        .subscribe(() => this.goBack());
    }
  }
}