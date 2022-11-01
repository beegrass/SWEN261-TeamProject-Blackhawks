import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {
  jerseys: Jersey[] = [];

  showAdd: boolean = false;
  showModify: boolean = false;

  constructor(private jerseyService: JerseyService) { }

  ngOnInit(): void {
    this.getJerseys();
  }

  getJerseys(): void {
    this.jerseyService.getJerseys()
      .subscribe(jerseys => this.jerseys = jerseys);
  }
}
