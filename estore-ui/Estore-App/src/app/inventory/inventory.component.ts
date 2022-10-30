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

  constructor(private jerseyService: JerseyService) { }

  ngOnInit(): void {
    this.getJerseys();
  }

  getJerseys(): void {
    this.jerseyService.getJerseys()
      .subscribe(jerseys => this.jerseys = jerseys);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.jerseyService.addJersey({ name } as Jersey)
      .subscribe(Jersey => {
        this.jerseys.push(Jersey);
      });
  }

  delete(jersey: Jersey): void {
    this.jerseys = this.jerseys.filter(h => h !== jersey);
    this.jerseyService.deleteJersey(jersey.id).subscribe();
  }
}
