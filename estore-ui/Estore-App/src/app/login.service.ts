import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { Customer } from './customer';
import { Jersey } from './jersey';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  customerId : number = -1;

  constructor(private http: HttpClient) { }

  
}