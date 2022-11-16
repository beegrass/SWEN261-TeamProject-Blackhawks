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

@Component({
  selector: 'app-jersey-detail',
  templateUrl: './jersey-detail.component.html',
  styleUrls: ['./jersey-detail.component.css']
})
export class JerseyDetailComponent implements OnInit {
  // @Input() jersey?: Jersey;
  jersey: Jersey | undefined;


  constructor(
    private route: ActivatedRoute,
    private jerseyService: JerseyService,
    private location: Location,
    protected cartService: CartService,
    private cartComponent : CartComponent, 
    private loginComponent : LoginComponent,
    private customerService : CustomerService
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
      .subscribe(jersey => this.jersey = jersey);
  }



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
     addToCart(jersey : Jersey, quantity : number): void{
      let customer : Customer = this.loginComponent.getCurrentCustomer(); 
      for(let i = 0; i < quantity ; i++){
        this.customerService.addJerseyToCart(customer, jersey)
        .subscribe(); // note for vince: not sure if you need to do subscribe to get cust obj 
      }
      
    }

  save(): void {
    if (this.jersey) {
      this.jerseyService.updateJersey(this.jersey)
        .subscribe(() => this.goBack());
    }
  }
}