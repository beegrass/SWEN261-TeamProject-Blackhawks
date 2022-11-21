import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Cart } from 'app/cart';
import { CartService } from '../cart.service';
import { CustomerService } from 'app/customer.service';
import { LoginService } from 'app/login.service';
import { CartComponent } from 'app/cart/cart.component';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  totalCost : number = 0
  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private customerService : CustomerService,
    private loginService : LoginService, 
    private cartComponent : CartComponent
  ) { }

  ngOnInit(): void {
    this.totalCost  = this.cartComponent.getTotalCost; 
  }

  selectColorMode(): string {
    let colorData = localStorage.getItem('colorblindKey');
    let parsed = JSON.parse(colorData!);
    let color = parsed.color;

    console.log(color);
    return color;
  }

  goBack(): void {
    this.location.back();
  }

  onClick(): void {
    alert("Thank you for your purchase!")
  }

  emptyCart() : void {
    this.customerService.emptyCart(this.getCustId).subscribe(
      updatedCustomer => {
        this.cartComponent.updateTheCartTest = updatedCustomer.cart; 
      }
    )
  }

  get getCustId():number {
    return this.loginService.customerId
  }

  get getTotalCost() : number { 
    return this.totalCost; 
  }

}
