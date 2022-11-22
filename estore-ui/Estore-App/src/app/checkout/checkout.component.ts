import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { CustomerService } from 'app/customer.service';
import { LoginService } from 'app/login.service';
import { CartComponent } from 'app/cart/cart.component';
/**
 * This is the component that is responsible for mocking a checkout process
 * @Author Hayden 
 */
@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  totalCost : number = 0
  constructor(
    private location: Location,
    private customerService : CustomerService,
    private loginService : LoginService, 
    private cartComponent : CartComponent
  ) { }

  /**
   * on init it gets the total cost from the cartComponent so its most updated 
   */
  ngOnInit(): void {
    this.totalCost  = this.cartComponent.getTotalCost; 
  }

  /**
   * allows for user to go back to the previous page 
   */
  goBack(): void {
    this.location.back();
  }

  /**
   * Alerts the user that they have made their purchase 
   */
  onClick(): void {
    alert("Thank you for your purchase!")
  }

  /**
   * This method is called when you proceed to checkout, it empties the entire cart 
   */
  emptyCart() : void {
    this.customerService.emptyCart(this.getCustId).subscribe(
      updatedCustomer => {
        this.cartComponent.updateTheCartTest = updatedCustomer.cart; 
      }
    )
  }

  /**
   * returns the customers id 
   * @return number 
   */
  get getCustId():number {
    return this.loginService.customerId
  }

  /**
   * this returns the total cost
   * @return number - totalCost
   */
  get getTotalCost() : number { 
    return this.totalCost; 
  }

}
