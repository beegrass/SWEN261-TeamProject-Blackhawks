import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { CartService } from 'app/cart.service';
import { CustomerService } from 'app/customer.service';
import { Jersey } from 'app/jersey';
import { Cart } from 'app/cart';
import { Customer } from 'app/customer';

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
    private cartService: CartService,
    private customerService: CustomerService
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
  isAdmin(): boolean { // rename to handleLogin() later
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
        this.login(name);
      }
    }
    return result;
  }

  login(username: string): void {
    let jersey_list: Jersey[] = [];
    let total_price: number = 0;
    let userCart = {jersey_list, total_price} as Cart;

    if (!username) {
      return;
    }

    this.customerService.createCustomer({userCart, username} as Customer)
      .subscribe(cust => {
        this.customers.push(cust);
      })
      console.log(this.customers);
    }
}
