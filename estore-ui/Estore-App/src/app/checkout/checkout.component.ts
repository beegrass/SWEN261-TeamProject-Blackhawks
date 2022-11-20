import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Cart } from 'app/cart';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit(): void {
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
  // getTotalCost(): number {
  //   return this.cartService.getTotalCost();
  // }

}
