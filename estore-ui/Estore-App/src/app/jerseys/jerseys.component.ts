import { Component, OnInit } from '@angular/core';
import { JerseyService } from '../jersey.service';

@Component({
  selector: 'app-jerseys',
  templateUrl: './jerseys.component.html',
  styleUrls: ['./jerseys.component.css']
})
export class JerseysComponent implements OnInit {

  constructor(private jerseyService: JerseyService) { }

  ngOnInit(): void {
  }

  add(): void {
    
  }

  delete(): void {

  }

  getJerseys(): void {

  }

}
