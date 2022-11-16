import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';
import { CartService } from 'app/cart.service';
import { CartComponent } from 'app/cart/cart.component';
@Component({
  selector: 'app-jerseys',
  templateUrl: './jerseys.component.html',
  styleUrls: ['./jerseys.component.css']
})
export class JerseysComponent implements OnInit {
  jerseys: Jersey[] = [];

  constructor(private jerseyService: JerseyService,
    private cartComponent : CartComponent
  ) { }

  ngOnInit(): void {
    this.getJerseys();
  }

  getJerseys(): void {
    this.jerseyService.getJerseys()
    .subscribe(Jerseys => this.jerseys = Jerseys);
  }

  /**
   * adds a certain quantity of type jersey to the current customers cart 
   * @param jersey 
   * @param quantity 
   */
  addJerseyToCart(jersey: Jersey, quantity: Number): void {
    for(let i = 0; i < quantity ; i++){
      this.cartComponent.addToCart(jersey);
    }
  }

}
