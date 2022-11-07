import { Observable } from "rxjs";
import { Cart } from "./cart";

export interface Customer {
    userCart: Cart;
    username: string;
    id: number;
   
}
