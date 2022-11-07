import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { CartService } from '../cart.service';
import { CustomerService} from '../customer.service';
import { Cart } from 'app/cart';
import { Jersey } from "app/jersey";
import { Customer } from 'app/customer';
import { Observable } from 'rxjs/internal/Observable';
import { JerseyService } from 'app/jersey.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
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
    console.warn("username: " + this.username) 
    return this.username;
  }

  /**
   * Method that returns if the user is the admin or not
   * @returns true if admin, false is customer
   */
  isAdmin(): boolean {
    var name = this.onSubmit().toLowerCase();
    var result = name == "admin";
    if (name == "") {
      alert("Enter a valid username")
    } else {
      console.warn(result)
      if (result) {
        this.router.navigate(["/inventory"])
      } else {
        this.router.navigate(["/jerseys"])
      }
    }
    return result;
  }

  /**
   * This allows the user to login using a string username 
   */
  login(username: string): void {
    username = username.trim().toLowerCase();
    let cart_list: Jersey[] = new Array();
    let total_price = 0;
    let userCart = {cart_list, total_price} as Cart;
    this.cartService.createCart(userCart); 
    
    if (!username && username.toLowerCase() != 'admin') {
      return; // returns early if username is admin or doesn't exist otherwise create new cust and add to array of customer
    }
    this.customerService.createCustomer({userCart, username} as Customer)
      .subscribe(cust => {
      this.customers.push(cust);
    })
    console.log(this.customers)
  }
}