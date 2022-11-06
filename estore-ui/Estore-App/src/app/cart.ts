import { Jersey } from "./jersey";

export interface Cart {
  total_price: number;
  id : number; 
  cartArray : Array<Jersey>

}

