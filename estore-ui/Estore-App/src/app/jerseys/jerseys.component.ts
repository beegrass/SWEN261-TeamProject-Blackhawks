import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JERSEYS } from '../mock-jerseys';
import { JerseyService } from '../jersey.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-jerseys',
  templateUrl: './jerseys.component.html',
  styleUrls: ['./jerseys.component.css']
})
export class JerseysComponent implements OnInit {
  jerseys: Jersey[] = [];

  loops: Number[] = [0, 0, 0];

  constructor(private jerseyService: JerseyService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.getJerseys();
  }

  getJerseys(): void {
    this.jerseyService.getJerseys()
    .subscribe(Jerseys => this.jerseys = Jerseys);
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
