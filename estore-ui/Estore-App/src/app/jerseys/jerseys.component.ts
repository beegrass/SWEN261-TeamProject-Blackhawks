import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JERSEYS } from '../mock-jerseys';

@Component({
  selector: 'app-jerseys',
  templateUrl: './jerseys.component.html',
  styleUrls: ['./jerseys.component.css']
})
export class JerseysComponent implements OnInit {

  jerseys = JERSEYS;
  selectedJersey?: Jersey;

  constructor() { }

  ngOnInit(): void {
  }

  onSelect(jersey: Jersey): void {
    this.selectedJersey = jersey;
  }

}
