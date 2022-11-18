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

  }

  get getCustId():number {
    return this.loginService.customerId
  }

  get getTotalCost():number {
    return this.customerService.totalCost
  }

  set setTotalCost(cost : number) {
    if(this.customerService.totalCost < 0){
      this.customerService.setTotalCostCustService = 0
    }else{
      this.customerService.setTotalCostCustService = cost 
    }
    
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
      // this.cart = this.cart.filter(h => h !== jersey);
      this.customerService.removeJerseyFromCart(customerId, jersey)
      .subscribe( observer => 
        console.log("this is what returned after remove from cart : " + observer)
      );
      this.customerService.getTotalCost(customerId)
        .subscribe( observer =>
          this.setTotalCost = observer
        )
    }
    console.log("total cost is now: $" + this.customerService.totalCost)
  }



}
