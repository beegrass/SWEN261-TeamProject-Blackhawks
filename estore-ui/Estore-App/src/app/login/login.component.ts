import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { Cart } from 'app/cart';
import { CustomerService } from 'app/customer.service';
import { CartService } from 'app/cart.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = "";
  // Example 1: <input [(ngModel)]="person.firstName" name="first">

  constructor(
    private customerService: CustomerService,
    private cartService: CartService,
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

  create(username: string, type: boolean): void {
    this.customerService.userLogin(username);
    
  }

}
