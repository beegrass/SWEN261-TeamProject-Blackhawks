import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './cust-dashboard.component.html',
  styleUrls: [ './cust-dashboard.component.css' ]
})
export class CustDashboardComponent implements OnInit {
  jerseys: Jersey[] = [];

  constructor(private jerseyService: JerseyService) { }

  ngOnInit(): void {
    this.getJerseys();
  }

  getJerseys(): void {
    this.jerseyService.getJerseys()
      .subscribe(jerseys => this.jerseys = jerseys.slice(1, 5));
  }
}