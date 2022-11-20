import { Observable } from "rxjs";
import { Jersey } from "./jersey";


export interface Customer {
    cart: Array<Jersey>;
    username: string;
    id: number;
    totalCost: number;
}
