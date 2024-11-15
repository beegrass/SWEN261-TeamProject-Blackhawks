import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';

/**
 * @Author Hayden, Vince and Ethan 
 */
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

  selectColorMode(): string {
    let colorData = localStorage.getItem('colorblindKey');
    let parsed = JSON.parse(colorData!);
    let color = parsed.color;

    
    return color;
  } 

  getJerseys(): void {
    this.jerseyService.getJerseys()
      .subscribe(jerseys => this.jerseys = jerseys);
  }

  /**
   * adds a new jersey to the json file 
   * 
   * @param name - name of the player for the jersey
   * @param num  - number of the jersey
   * @param p - price 
   * @param color - color of the jersey 
   * @param size - size of jersey 
   * @param image - image of jersey 
   */
  add(name: string, num: string, p: string, color: string, size: string, image: string): void {
    let number = parseFloat(num);
    let price = parseFloat(p);
    console.log("added jersey")
  
    name = name.trim();
    if (!name || !number || !color || !size || !image || price < 0 || number > 99) { 
      alert("Invalid input given jersey has not been added")
      return; 
    } 
    this.jerseyService.addJersey({ name, number, price, color, size, image } as Jersey)
      .subscribe(jersey => {
        this.jerseys.push(jersey);
        console.log("this is the jerseys new id: " + jersey.id)
      });
  }


  /**
   * deletes the jersey from the inventory -- asks for confirmation before doing it 
   * @param jersey - jersey wanted to be deleted from inventory 
   */
  delete(jersey: Jersey): void {
    if(window.confirm('Are you sure you want to delete this jersey?') == true) {
      this.jerseys = this.jerseys.filter(h => h !== jersey);
      this.jerseyService.deleteJersey(jersey.id).subscribe();
    }
  }
  
}
