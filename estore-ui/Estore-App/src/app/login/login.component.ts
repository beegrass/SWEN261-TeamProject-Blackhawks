import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { CartService } from '../cart.service';
import { CustomerService} from '../customer.service';
import { Cart } from 'app/cart';
import { Jersey } from "app/jersey";
import { Customer } from 'app/customer';
import { Observable } from 'rxjs/internal/Observable';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  carts : Cart[] = [];
  customers : Customer[] = []; 
  username: string = "";

  // Example 1: <input [(ngModel)]="person.firstName" name="first">

  constructor(
    private router: Router,
    private cartService : CartService,
    private customerService : CustomerService
  ) { }

  ngOnInit(): void {
  }

  /**
   * Method that returns the username from the text form
   * navigates user to logout page for now but will be changed to 
   * main page later.
   * @returns Returns entered username
   */
  onSubmit(): string {
    // this.router.navigate(['/logout'])
    console.warn("username: " + this.username) 
    return this.username;
  }

  /**
   * Method that returns if the user is the admin or not
   * @returns true if admin, false is customer
   */
  isAdmin(): boolean {
    console.warn("admin" == this.onSubmit().toLowerCase());
    return "admin" == this.onSubmit().toLowerCase();
  }

// this is NOT done 
  login(username : String) : void{
    // make a cart
    // 
    if(!username){
      alert("Invalid input must put in username")
      return ; 
    }
    let cartArray = new Array<Jersey>;
    let total_price = 0.0;
    
    this.cartService.createCart({ cartArray, total_price} as Cart)
      .subscribe(cart => {
        this.carts.push(cart);
      });
    let userCart = this.carts[this.carts.length-1];
    let type = false; 
    //making new customer 
    this.customerService.createCustomer({userCart, username , type}as Customer)
      .subscribe(customer => {
        this.customers.push(customer); 
      });
    
   
    return this.customerService.userLogin(username)

      // error handling if the customer already exists 
    
  }
}
