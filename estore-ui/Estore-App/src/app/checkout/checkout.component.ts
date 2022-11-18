import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Cart } from 'app/cart';
import { CartService } from '../cart.service';
import { CustomerService } from 'app/customer.service';
import { LoginService } from 'app/login.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  totalCost : number = 0;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private customerService : CustomerService,
    private loginService : LoginService
  ) { }

  ngOnInit(): void {
  }

  goBack(): void {
    this.location.back();
  }

  onClick(): void {
    alert("Thank you for your purchase!")
  }

  get getCustId():number {
    return this.loginService.customerId
  }

  // getTotalCost(): number {
  //   this.customerService.getTotalCost(this.getCustId).subscribe(
  //     observableNumber => this.totalCost = observableNumber.valueOf()
  //   );
  //   return this.totalCost
  // }


}
