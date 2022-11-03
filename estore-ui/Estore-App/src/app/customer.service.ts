import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClientBackendService } from 'angular-in-memory-web-api';
import { catchError, Observable, of } from 'rxjs';
import { Cart } from './cart';
import { Customer } from './customer';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private customersUrl = 'http://localhost:8080/customers' // URL to our api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  messageService: any;


  constructor(
    private http: HttpClient
  ) { }

  /**
   * Method that creates a customer and assigns them an
   * empty cart
   * @param customer - customer to be create
   * @returns - newly created customer
   */
  
  private log(message: string) {
    this.messageService.add(`CartService: ${message}`)
  }


  /**
   * Creates a new customer with the given username 
   * otherwise returns a get of the customer that already exists
   * @param customer 
   * @returns 
   */
  // userLogin(customer: Customer): Observable<Customer> {
  //   const admin_id = 1; 
  //   const url_get = "GET /customer/" + customer.id;

  //   if(customer.id == admin_id){
  //     return this.http.get<Customer>(url_get, this.httpOptions);
  //   } else if (customer.id != admin_id && this.http.get<Customer>(url_get, this.httpOptions) == null ) {
  //     return this.http.post<Customer>(this.customersUrl, customer, this.httpOptions)
  //     .pipe(
  //       catchError(this.handleError<Customer>('createdCustomer'))
  //     );
  //   } else {
  //     return this.http.get<Customer>(url_get, this.httpOptions);
  //   }
  // }

  getCustomerCart(customer: Customer): Observable<Cart> {
      const url = "GET /customer/cart/?userId=" + customer.id;
      return this.http.get<Cart>(url, this.httpOptions).pipe(
        catchError(this.handleError<Cart>('deleteEntireCart'))
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
   private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
