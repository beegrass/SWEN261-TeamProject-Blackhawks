import { Component, OnInit, Input } from '@angular/core';
import { Jersey } from '../jersey';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { JerseyService } from '../jersey.service';

@Component({
  selector: 'app-admin-detail',
  templateUrl: './admin-detail.component.html',
  styleUrls: ['./admin-detail.component.css']
})
export class AdminDetailComponent implements OnInit {
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

  save(): void {
    if (this.jersey) {
      this.jerseyService.updateJersey(this.jersey).subscribe(() => this.goBack());
    }
  }
}