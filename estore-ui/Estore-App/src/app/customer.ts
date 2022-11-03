import { Cart } from "./cart";

export class Customer {
    userCart: Cart;
    username: string;
    type: boolean;
    id: number;
    constructor(userCart: Cart, username : string, type: boolean , id :number){
        this.userCart = userCart; 
        this.username = username; 
        this.type = type;
        this.id = id; 
    }
}
