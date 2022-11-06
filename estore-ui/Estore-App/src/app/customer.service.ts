import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClientBackendService } from 'angular-in-memory-web-api';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { Cart } from './cart';
import { CartService } from './cart.service';
import { CartComponent } from './cart/cart.component';
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
  
  /**
   * Creates an observable customer that can be used in angular
   * @param customer - customer that will become observable
   * @returns - the observable customer
   */
  createCustomer(customer : Customer): Observable<Customer> {
    return this.http.post<Customer>(this.customersUrl, customer, this.httpOptions).pipe(
      tap((newCustomer : Customer) => this.log(`added Jersey w/ id=${newCustomer.id}`)),
      catchError(this.handleError<Customer>('create Customer'))
    );
  } 

  /**
   * GET customer by id. Will return `undefined` if not found
   */
  getCustomer<Data>(id: number): Observable<Customer> {
    const url = `${this.customersUrl}/?id=${id}`;
    return this.http.get<Customer[]>(url)
      .pipe(
        map(customers => customers[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} hero id=${id}`);
        }),
        catchError(this.handleError<Customer>(`getHero id=${id}`))
      );
  }

  /**
   * GET customer by username. Will return `undefined` if not found
   */
   getCustomerByUsername<Data>(username : string ): Observable<Customer> {
    const url = `${this.customersUrl}/customer/${username}`;
    return this.http.get<Customer[]>(url)
      .pipe(
        map(customers => customers[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} customer=${username}`);
        }),
        catchError(this.handleError<Customer>(`get customer =${username}`))
      );
  }

  deleteCustomer(custId: number) {
    const url = `${this.customersUrl}/${custId}`;
    return this.http.delete<Customer>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted hero id=${custId}`)),
      catchError(this.handleError<Customer>('deleteHero'))
    );
  }

  /**
   * Creates a new customer with the given username 
   * otherwise returns a get of the customer that already exists
   * this is get customer 
   * @param customer 
   * @returns 
   */
  userLogin(username : String): Observable<Customer> {
    const url = `${this.customersUrl}/${username}`;
    return this.http.get<Customer>(url).pipe(
      tap(_ => this.log(`fetched customer=${username}`)),
      catchError(this.handleError<Customer>(`gettingSpecificCustomer customer=${username}`))
    );
  }

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
