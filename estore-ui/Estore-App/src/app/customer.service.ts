import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { Customer } from './customer';
import { Jersey } from './jersey';
import { MessageService } from './message.service';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private customersUrl = 'http://localhost:8080/customers' // URL to our api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService : MessageService
  ) { }


  private log(message: string) {
    this.messageService.add(`CustomerService: ${message}`)
  }

   /**
   * Creates a new customer from given body 
   * @param customer 
   * @returns Observable<Customer> 
   */
    createCustomer(customer : Customer): Observable<Customer> {
      console.log("this is the customer: " + customer)
      return this.http.post<Customer>(this.customersUrl, customer, this.httpOptions).pipe(
        tap((newCustomer : Customer) => this.log(`created new Customer w/ id=${newCustomer.id}`)),
        catchError(this.handleError<Customer>('createCustomer'))
      );
    }


  /**
   * This gets the specific Customer based on the id number given
   * @param id 
   * @returns Observable<Customer>
   */
  getCustomer(id: number): Observable<Customer> {
    const url = `${this.customersUrl}/${id}`;
    return this.http.get<Customer>(url).pipe(
      tap(_ => this.log(`fetched Customer id=${id}`)),
      catchError(this.handleError<Customer>(`getCustomer id=${id}`))
    );
  }

  /**
   * This gets the cart of a specific Customer based on the id number given
   * @param id of the given customer
   * @returns Observable<Jersey[]> a list of jersey's in the cart
   */
   getCart(id: number): Observable<Jersey[]> {
    const url = `${this.customersUrl}/cart/${id}`;
    return this.http.get<Jersey[]>(url).pipe(
      tap(_ => this.log(`fetched Customer id=${id}`)),
      catchError(this.handleError<Jersey[]>(`getCart id=${id}`))
    );
  }

  /**
   * adds a new Jersey to the Customers cart 
   * @param customer 
   * @param jersey 
   * @returns Observable<any> returns the observable customer 
   */
  addJerseyToCart(customer : Customer, jersey: Jersey): Observable<any> {
    // const url = `${this.customersUrl}/add/${customer.id}/${jersey.id}`
    // return this.http.put(url,this.httpOptions).pipe(
    //   tap(_ => this.log(`add Jersey to Customer Cart=${customer.id}`)),
    //   catchError(this.handleError<any>('addJerseyToCart'))
    // );

    // code below works for presetting the customer id
    const url = `${this.customersUrl}/add/${1}/${jersey.id}`
    return this.http.put(url,this.httpOptions).pipe(
      tap(_ => this.log(`add Jersey to Customer Cart=${1}`)),
      catchError(this.handleError<any>('addJerseyToCart'))
    );
  }

  /**
   * removes jersey from the Customers cart 
   * @param customer 
   * @param jersey 
   * @returns Observable<any> returns the observable customer 
   */
   removeJerseyFromCart(customer : Customer, jersey: Jersey): Observable<any> {
    const url = `${this.customersUrl}/remove/${customer.id}/${jersey.id}`
    return this.http.put(url,this.httpOptions).pipe(
      tap(_ => this.log(`remove Jersey from Customer Cart=${customer.id}`)),
      catchError(this.handleError<any>('removeJerseyFromCart'))
    );
  }

  /**
   * empties the customers cart through the server 
   * @param customer 
   * @returns Observable<any> 
   */
  emptyCart(customer : Customer): Observable<any> {
    const url = `${this.customersUrl}/${customer.id}`
    return this.http.put(url, this.httpOptions).pipe(
      tap(_ => this.log(`emptied customers cart=${customer.id}`)),
      catchError(this.handleError<any>('emptyCart'))
    );
  }


  /**
   * This gets the total cost of the Customers cart 
   * @param id of the Customer
   * @returns Observable<Number>
   */
   getTotalCost(id: number): Observable<Number>{
    const url = `${this.customersUrl}/cost/${id}`;
    return this.http.get<Number>(url).pipe(
      tap(_ => this.log(`get total cost of customer =${id}`)),
      catchError(this.handleError<Number>(`getTotalCost customer=${id}`))
    );
  }


  
  /**
   * returns an array list of Customers from the server 
   * @returns 
   */
  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.customersUrl)
      .pipe(
        tap(_ => this.log('fetched customers')),
        catchError(this.handleError<Customer[]>('getCustomers', []))
      );
  }


  /** GET Customer by id. Return `undefined` when id not found */
  getCustomerNo404<Data>(id: number): Observable<Customer> {
    const url = `${this.customersUrl}/?id=${id}`;
    return this.http.get<Customer[]>(url)
      .pipe(
        map(customers => customers[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} customer id=${id}`);
        }),
        catchError(this.handleError<Customer>(`getCustomer id=${id}`))
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

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  
}
