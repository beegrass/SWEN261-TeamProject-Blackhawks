import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';
import { CartService } from 'app/cart.service';

@Component({
  selector: 'app-jerseys',
  templateUrl: './jerseys.component.html',
  styleUrls: ['./jerseys.component.css']
})
export class JerseysComponent implements OnInit {

  jerseys: Jersey[] = [];

  loops: Number[] = [0, 0, 0];

  constructor(private jerseyService: JerseyService, private cartService: CartService) { }

  ngOnInit(): void {
    this.getJerseys();
  }

  getJerseys(): void {
    this.jerseyService.getJerseys()
      .subscribe(jerseys => this.jerseys = jerseys);
  }

  addJerseyToCart(jersey: Jersey, quantity: Number): void {
    
  }

}
