import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cart } from './cart';

/**
 * Service for angular elements to talk to the api with cart objects.
 * 
 * @author Hayden Cabral
 */

@Injectable({
    providedIn: 'root'
  })
  export class CartService {
  
  
    private CartsUrl = 'http://localhost:8080/Cart' // URL to our api
    httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    messageService: any;
  
    constructor(
      private http: HttpClient
      ) { }

    createCart(cart: Cart): Observable<Cart> {
      return this.http.post<Cart>(this.CartsUrl, cart, this.httpOptions)
      .pipe(
        catchError(this.handleError<Cart>('getCart'))
      );
    }

    decrementJerseyTypeAmount(cartId: number, jerseyId: number): Observable<any> {
      const url = "PUT /cart/decrement/?cartId=" + cartId + "&jerseyId=" + jerseyId
      return this.http.put(url, this.httpOptions).pipe(
        catchError(this.handleError<Cart>('decrementJerseyTypeAmount'))
      );
    }

    addJerseyToCart(cartId: number, jerseyId: number): Observable<any> {
      const url = "PUT /cart/increment/?cart=" + cartId + "&jerseyId=" + jerseyId
      return this.http.put(url, this.httpOptions).pipe(
        catchError(this.handleError<Cart>('addJerseyToCart'))
      );
    }

    deleteJerseyType(cartId: number, jerseyId: number) {
      const url = "PUT /cart/deleteJerseyType/?cart=" + cartId + "&jerseyId=" + jerseyId
      return this.http.put(url, this.httpOptions).pipe(
        catchError(this.handleError<Cart>('deleteJerseyType'))
      );
    }

    deleteEntireCart(cartId: number): Observable<Cart> {
      const url = "PUT /cart/deleteEntireCart/?cart=" + cartId
      return this.http.put<Cart>(url, this.httpOptions).pipe(
        catchError(this.handleError<Cart>('deleteEntireCart'))
      );
    }

    getSpecificCart(cartId: number): Observable<Cart> {
      const url = "GET /cart/" + cartId
      return this.http.get<Cart>(url, this.httpOptions).pipe(
        catchError(this.handleError<Cart>('deleteEntireCart'))
      );
    }


    private log(message: string) {
        this.messageService.add(`CartService: ${message}`);
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
    