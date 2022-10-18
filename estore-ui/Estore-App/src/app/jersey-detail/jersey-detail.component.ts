import { Component, OnInit, Input } from '@angular/core';
import { Jersey } from '../jersey';

@Component({
  selector: 'app-jersey-detail',
  templateUrl: './jersey-detail.component.html',
  styleUrls: ['./jersey-detail.component.css']
})
export class JerseyDetailComponent implements OnInit {
  @Input() jersey?: Jersey;

  constructor() { }

  ngOnInit(): void {
  }

}
