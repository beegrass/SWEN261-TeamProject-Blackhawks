import { Component, OnInit } from '@angular/core';
import { Cart } from 'app/cart';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
  }

  // getTotalCost(): number {
  //   return this.cartService.getTotalCost();
  // }

}
