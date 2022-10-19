import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';

import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';

@Component({
  selector: 'app-jersey-search',
  templateUrl: './jersey-search.component.html',
  styleUrls: ['./jersey-search.component.css']
})
export class JerseySearchComponent implements OnInit {

  jerseys$!: Observable<Jersey[]>;
  private searchTerms = new Subject<string>();

  constructor(private jerseyService: JerseyService) { }

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.jerseys$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.jerseyService.searchJerseys(term)),
    );
  }

}
