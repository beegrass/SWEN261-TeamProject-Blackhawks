import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap, Observable, of } from 'rxjs';
import { Jersey } from './jersey';

@Injectable({
  providedIn: 'root'
})
export class JerseyService {


  private JerseysUrl = 'http://localhost:8080/Jerseys' // URL to our api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: JerseyService) { }

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

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }


}
