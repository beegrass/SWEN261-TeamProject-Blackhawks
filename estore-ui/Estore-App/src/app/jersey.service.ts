import { Injectable } from '@angular/core';
import { Jersey } from './jersey';
import { JERSEYS } from './mock-jerseys';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class JerseyService {


  private JerseysUrl = 'http://localhost:8080/Jerseys' // URL to our api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  messageService: any;

  constructor(
    private http: HttpClient
    ) { }

  /** GET Jerseys from the server */
  getJerseys(): Observable<Jersey[]> {
    return this.http.get<Jersey[]>(this.JerseysUrl)
      .pipe(
        catchError(this.handleError<Jersey[]>('getJerseys', []))
      );
  }

  /** GET Jersey by id. Return `undefined` when id not found */
  getJerseyNo404<Data>(id: number): Observable<Jersey> {
    const url = `${this.JerseysUrl}/?id=${id}`;
    return this.http.get<Jersey[]>(url)
      .pipe(
        map(Jerseys => Jerseys[0]), // returns a {0|1} element array
        catchError(this.handleError<Jersey>(`getJersey id=${id}`))
      );
  }

  /** GET Jersey by id. Will 404 if id not found */
  getJersey(id: number): Observable<Jersey> {
    const url = `${this.JerseysUrl}/${id}`;
    return this.http.get<Jersey>(url).pipe(
      catchError(this.handleError<Jersey>(`getJersey id=${id}`))
    );
  }

  /* GET Jerseys whose name contains search term */
  searchJerseys(term: string): Observable<Jersey[]> {
    if (!term.trim()) {
      // if not search term, return empty Jersey array.
      return of([]);
    }
    return this.http.get<Jersey[]>(`${this.JerseysUrl}/?name=${term}`).pipe(
      tap(x => x.length ?
        this.log(`found jerseys matching "${term}"`) :
        this.log(`no jerseys matching "${term}"`)),
      catchError(this.handleError<Jersey[]>('searchJerseys', []))
    );
  }

  //////// Save methods //////////

  /** POST: add a new Jersey to the server */
  addJersey(Jersey: Jersey): Observable<Jersey> {
    return this.http.post<Jersey>(this.JerseysUrl, Jersey, this.httpOptions).pipe(
      catchError(this.handleError<Jersey>('addJersey'))
    );
  }

  /** DELETE: delete the Jersey from the server */
  deleteJersey(id: number): Observable<Jersey> {
    const url = `${this.JerseysUrl}/${id}`;

    return this.http.delete<Jersey>(url, this.httpOptions).pipe(
      catchError(this.handleError<Jersey>('deleteJersey'))
    );
  }

  /** PUT: update the Jersey on the server */
  updateJersey(Jersey: Jersey): Observable<any> {
    return this.http.put(this.JerseysUrl, Jersey, this.httpOptions).pipe(
      catchError(this.handleError<any>('updateJersey'))
    );
  }
  /** Log a JerseyService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`JerseyService: ${message}`);
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
