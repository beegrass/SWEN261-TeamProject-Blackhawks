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
import { NONE_TYPE } from '@angular/compiler';
import { LoginService} from '../login.service'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  customers : Customer[] = []; 
  currentId : number = -1; 
  username: string = "";

  // Example 1: <input [(ngModel)]="person.firstName" name="first">

  constructor(
    private router: Router,
    private customerService : CustomerService,
    private loginService : LoginService
  ) { }

  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers(): void{
    this.customerService.getCustomers()
      .subscribe(customers => this.customers = customers); 
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

  get getCustId():number {
    return this.loginService.customerId
  }

  set setCustId(id : number) {
    this.loginService.customerId = id;
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
    if(!username ){
      alert("Invalid input given: input a username")
    }
    
    for(var customer of this.customers){
      if(customer.username == username){
        //this.setCustId(customer.id)
        this.setCustId = customer.id
        //this.currentId = customer.id
        console.warn(this.getCustId)
        return; 
      }
    }
    // if the user doesnt exist -> create a new account and add it to the array
    this.customerService.createCustomer({username} as Customer)
      .subscribe(customer => {
        this.customers.push(customer);
        //this.getCustomers();
        this.setCustId = customer.id
      });
    console.warn("this is the id of the new customer: "  + this.getCustId)

    return;

      
  }

  // getCart(): void {
  //   this.customerService.getCart()
  //   .subscribe(Jerseys => this.cart = Jerseys);
  // }

  /**
   * this gets the current customers username 
   * @returns 
   */
  getCurrentCustomersUsername(): String{
    let username : String = ""; 
    this.customerService.getCustomer(this.currentId)
      .subscribe(customerObservable => {
        username = customerObservable.username;
        console.warn(username)
        return username; 
      });
    return username; 
  }

  get getCurrentCustomerId(): number{
    return this.currentId;
  }

  get getCurrentCustomer(): Customer{
    let customer : Customer = {} as Customer; 
    this.customerService.getCustomer(this.currentId)
      .subscribe(customerObservable => {
        customer = customerObservable;
      });
    return customer;
  }
  
}