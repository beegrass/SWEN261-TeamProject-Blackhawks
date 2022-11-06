import { Observable } from "rxjs";
import { Cart } from "./cart";

export interface Customer {
    userCart: Observable<Cart>;
    username: string;
    id: number;
   
}
