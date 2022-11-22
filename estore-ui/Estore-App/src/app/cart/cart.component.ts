import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { CustomerService } from 'app/customer.service';
import { LoginService } from 'app/login.service';

/**
 * This component is responsible for showing the cart features like removing jersey from your cart  and getting the total cost 
 * @Author Angela Ngo
 */
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart : Jersey[] = [];
  totalCost : number = 0;
  
  constructor(
    private customerService : CustomerService,
    private loginService : LoginService
  ) { }

  ngOnInit(): void {
    this.getCart();
    this.getTotalCostInternal(); 
    console.log("this is what is printing on intialization")
    console.log(this.cart)

  }

  /**
   * returns the customers id 
   * @return  number - the id of the customer 
   */
  get getCustId():number {
    return this.loginService.customerId
  }
  
  /**
   * updates the cart 
   * @param cart - the cart to set to 
   */
  set updateTheCartTest(cart : Array<Jersey>){
    this.cart = cart; 
  }

  /**
   * This gets the cart in the html 
   * @param cart - the array of jerseys 
   */
  get getTheCartTest() : Array <Jersey> {
    return this.cart;
  } 

 

  /**
   * This gets the cart of the current user that is logged in 
   */
   getCart(): void {
    this.customerService.getCart(this.getCustId)
    .subscribe(Jerseys => this.cart = Jerseys);
  }

  /**
   * removes the given jersey from the cart
   * 
   * @param jersey - the jersey you want to remove from the cart 
   */
  removeJerseyFromCart(jersey: Jersey): void {
    console.warn(this.cart)
    if(window.confirm('Are you sure you want to remove this jersey?') == true) {
      let customerId : number = this.getCustId
      this.cart = this.cart.filter(h => h !== jersey);
      this.customerService.removeJerseyFromCart(customerId, jersey)
      .subscribe( updatedCustomer => {
        this.customerService.getCart(updatedCustomer.id).subscribe(Jerseys => this.cart = Jerseys)
        }
      );
    
    this.customerService.getTotalCost(this.getCustId).subscribe(
      total => {
        this.setTotalCost = this.totalCost - jersey.price
        console.log("this is the updated total cost in the remove function: " + this.totalCost)
      }
    );
    //this.ngOnInit()
    // console.warn("this is the cart after the delete")
    // console.log(this.cart)

    // console.warn("this is the cart when calling the method getCart() on init")
    //this.getCart()
    // console.warn(this.cart)
    
    // console.warn("this is the updated price: " + this.totalCost); 
    this.ngOnInit();

  }

  }

  /**
   * sets the total cost of the cart 
   * @param total - the total cost to set to 
   */
  set setTotalCost(total : number){
    this.totalCost = total; 
  }

  /**
   * accessor for the total cost - is used in the html 
   * @returns totalCost - the total cost of the cart 
   */
  get getTotalCost() : number{
    return this.totalCost; 
    
  }

  /**
   * this gets the total cost from the customer service and assigns it to the total in this component 
   */
  getTotalCostInternal(){
    this.customerService.getTotalCost(this.getCustId).subscribe(
      total => this.totalCost = total
    );
    
  }

  selectColorMode(): string {
    let colorData = localStorage.getItem('colorblindKey');
    let parsed = JSON.parse(colorData!);
    let color = parsed.color;


    return color;
  }

}