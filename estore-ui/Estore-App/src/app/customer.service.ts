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

  private customersUrl = 'http://localhost:8080/Customers' // URL to our api
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

  createCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.customersUrl, customer, this.httpOptions)
    .pipe(
      catchError(this.handleError<Customer>('getCustomer'))
    );
  }

  getCustomerCart(customer: Customer): Observable<Cart>{
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
