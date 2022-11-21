import { Component, OnInit} from '@angular/core';
import { Jersey } from '../jersey';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { JerseyService } from '../jersey.service';
/**
 * This component is the component that controls the jersey details for the admin, this is different from jersey detail which just 
 * gets the information of the jersey and not actually updating anything like in admin detail.
 */
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

  /**
   * On initialization it gets the most current jersey information
   */
  ngOnInit(): void {
    this.getJersey();
  }

  /**
   * Allows for the user to go back to the previous page 
   */
  goBack(): void {
    this.location.back();
  }

  /**
   * gets most current jersey information from the id taken from the inventory html page 
   */
  getJersey(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.jerseyService.getJersey(id)
      .subscribe(jersey => {this.jersey = jersey
      console.warn("this is the id of the jersey : " + jersey.id)});
  }

  /**
   * this saves any changes made to the jerseys
   */
  save(): void {
    if (this.jersey) {
      this.jerseyService.updateJersey(this.jersey).subscribe(() => this.goBack());
    }
  }
}