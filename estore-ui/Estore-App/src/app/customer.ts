import { Cart } from "./cart";

export interface Customer {
    userCart: Cart;
    username: string;
    type: boolean;
    id: number;
}
