import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Cart } from '../cart';
import { Jersey } from '../jersey';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  jerseys: Jersey[] = [];

  constructor(
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getJerseys();
  }

  getCart() : void {
    // gets the cart
  }

  getJerseys(): void {
    // get jerseys in the cart
  }

  selectColorMode(): string {
    let colorData = localStorage.getItem('colorblindKey');
    let parsed = JSON.parse(colorData!);
    let color = parsed.color;

    console.log(color);
    return color;
  }

}
