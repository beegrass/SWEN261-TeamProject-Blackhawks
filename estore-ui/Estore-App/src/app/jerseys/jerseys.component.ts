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
    private loginComponent : LoginComponent
  ) { }

  ngOnInit(): void {
    this.getJerseys();
  }
// // gets the customer via the id and then cart throuhg
//   getCurrentCustomer() : Number{

//   }
  getJerseys(): void {
    this.jerseyService.getJerseys()
    .subscribe(Jerseys => this.jerseys = Jerseys);
  }

  
}
