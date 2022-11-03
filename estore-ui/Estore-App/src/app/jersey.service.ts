import { Injectable } from '@angular/core';
import { Jersey } from './jersey';
// import { JERSEYS } from './mock-jerseys';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class JerseyService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  private jerseysUrl = 'http://localhost:8080/jerseys';  // URL to web api

  /** GET jersey by id. Will 404 if id not found */
  getJersey(id: number): Observable<Jersey> {
    const url = `${this.jerseysUrl}/${id}`;
    return this.http.get<Jersey>(url).pipe(
      tap(_ => this.log(`fetched jersey id=${id}`)),
      catchError(this.handleError<Jersey>(`getJersey id=${id}`))
    );
  }

  /** GET jerseys from the server */
  getJerseys(): Observable<Jersey[]> {
    return this.http.get<Jersey[]>(this.jerseysUrl)
      .pipe(
        tap(_ => this.log('fetched jerseys')),
        catchError(this.handleError<Jersey[]>('getJerseys', []))
      );
  }

  /** GET jersey by id. Return `undefined` when id not found */
  getJerseyNo404<Data>(id: number): Observable<Jersey> {
    const url = `${this.jerseysUrl}/?id=${id}`;
    return this.http.get<Jersey[]>(url)
      .pipe(
        map(jerseys => jerseys[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} jersey id=${id}`);
        }),
        catchError(this.handleError<Jersey>(`getJersey id=${id}`))
      );
  }

  /* GET Jersey whose name contains search term */
  searchJerseys(term: string): Observable<Jersey[]> {
    if (!term.trim()) {
      // if not search term, return empty jersey array.
      return of([]);
    }
    return this.http.get<Jersey[]>(`${this.jerseysUrl}/searchByName/?name=${term}`).pipe(
      tap(x => x.length ?
        this.log(`found jerseys matching "${term}"`) :
        this.log(`no jerseys matching "${term}"`)),
      catchError(this.handleError<Jersey[]>('searchJerseys', []))
    );
  }

  /** Log a JerseyService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`JerseyService: ${message}`);
  }

  addJersey(jersey: Jersey): Observable<Jersey> {
    return this.http.post<Jersey>(this.jerseysUrl, jersey, this.httpOptions).pipe(
      tap((newJersey: Jersey) => this.log(`added Jersey w/ id=${newJersey.id}`)),
      catchError(this.handleError<Jersey>('addJersey'))
    );
  }

  deleteHero(id: number): Observable<Jersey> {
    const url = `${this.jerseysUrl}/${id}`;

    return this.http.delete<Jersey>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted jersey id=${id}`)),
      catchError(this.handleError<Jersey>('deleteJersey'))
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