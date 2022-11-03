import { Jersey } from "./jersey";

export interface Cart {
  id: number
  total_price: number;
  cartArray: Jersey[];


}