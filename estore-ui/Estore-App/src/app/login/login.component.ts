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

  /**
   * This allows the user to login using a string username 
   */
  login(username: string): void {
    // alert(`username: ` + username)
    username = username.trim().toLowerCase();
    let type = false;
    if (!username) {return ;} // check to see if this is filled 

    if(this.customerService.getCustomerByUsername(username) == undefined){
      // create a new one 
      this.customerService.createCustomer({username, type} as Customer)
      .subscribe(cust => {
        this.customers.push(cust);
      })
      alert(`created a new user ${username}`)
    }else if(this.customerService.getCustomerByUsername(username) != undefined){
      this.customerService.getCustomerByUsername(username).subscribe(cust => {
        this.customers.push(cust); 
      })
      alert(`added user: ${username} to the customers array`)
    }
   
  }
}
