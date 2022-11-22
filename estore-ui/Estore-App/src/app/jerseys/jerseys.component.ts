import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';
import { CartService } from 'app/cart.service';
import { CartComponent } from 'app/cart/cart.component';
import { LoginComponent } from 'app/login/login.component';
@Component({
  selector: 'app-jerseys',
  templateUrl: './jerseys.component.html',
  styleUrls: ['./jerseys.component.css']
})
export class JerseysComponent implements OnInit {
  jerseys: Jersey[] = [];

  constructor(private jerseyService: JerseyService,
    private cartComponent : CartComponent,
  ) { }

  ngOnInit(): void {
    this.getJerseys();

    this.cartComponent.getCart();
    this.cartComponent.getTotalCostInternal(); 
    
  }

  getJerseys(): void {
    this.jerseyService.getJerseys()
    .subscribe(Jerseys => this.jerseys = Jerseys);
  }

  
  selectColorMode(): string {
    let colorData = localStorage.getItem('colorblindKey');
    let parsed = JSON.parse(colorData!);
    let color = parsed.color;

    return color;
  }
  
  
}
