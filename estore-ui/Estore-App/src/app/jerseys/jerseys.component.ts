import { Component, Input, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';
import { CartService } from 'app/cart.service';
import { LoginComponent } from 'app/login/login.component';

@Component({
  selector: 'app-jerseys',
  templateUrl: './jerseys.component.html',
  styleUrls: ['./jerseys.component.css']
})
export class JerseysComponent implements OnInit {
  jerseys: Jersey[] = [];

  constructor(private jerseyService: JerseyService, private cartService: CartService) { }

  ngOnInit(): void {
    this.getJerseys();
  }

  getJerseys(): void {
    this.jerseyService.getJerseys()
    .subscribe(Jerseys => this.jerseys = Jerseys);
  }

  addJerseyToCart(jersey: Jersey, quantity: Number): void {
    
  }

  // public isCool(): boolean {
  //   return LoginComponent.isDefault()
  // }


  



}
