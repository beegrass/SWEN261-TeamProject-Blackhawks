import { Component, OnInit, Input } from '@angular/core';
import { Jersey } from '../jersey';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { JerseyService } from '../jersey.service';

@Component({
  selector: 'app-jersey-detail',
  templateUrl: './jersey-detail.component.html',
  styleUrls: ['./jersey-detail.component.css']
})
export class JerseyDetailComponent implements OnInit {
  // @Input() jersey?: Jersey;
  jersey: Jersey | undefined;


  constructor(
    private route: ActivatedRoute,
    private jerseyService: JerseyService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getJersey();
  }

  goBack(): void {
    this.location.back();
  }

  getJersey(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.jerseyService.getJersey(id)
      .subscribe(jersey => this.jersey = jersey);
  }
}
