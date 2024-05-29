import { Injectable } from '@angular/core';
import { CartItem } from '../commons/cart-item';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  

  cartItem: CartItem[] = [];

  totalPrice: Subject<number> = new BehaviorSubject<number>(0);

  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);


  storage: Storage = sessionStorage;

  constructor() {
      let data = JSON.parse(this.storage.getItem('cartItem')!);

      if(data != null){
        this.cartItem = data;
        this.computeCartTotals();
      }
   }

  addToCart(theCartItem: CartItem){
    let alreadyExitsInCart: boolean = false;
    let existingCartItem: CartItem = theCartItem!;

    if(this.cartItem.length > 0){
      for(let tempCartItem of this.cartItem){
        if(tempCartItem.id == theCartItem.id){
          
            existingCartItem = tempCartItem;
            alreadyExitsInCart = true;
            break;
        }
      }  
    }
    if(alreadyExitsInCart){
        existingCartItem.quantity++;
    }
    else{
      this.cartItem.push(existingCartItem);
    }
    this.computeCartTotals();
  }

  computeCartTotals() {
    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;


    for(let currentCartItem of this.cartItem){
      totalPriceValue += currentCartItem.quantity * currentCartItem.unitPrice;
      totalQuantityValue += currentCartItem.quantity;
    
    }

    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);

    this.logCartData(totalPriceValue, totalQuantityValue);

    this.persistCartItems();
  }

  persistCartItems(){
    this.storage.setItem('cartItem', JSON.stringify(this.cartItem));
  }

  logCartData(totalPriceValue: number, totalQuantityValue: number) {
    console.log(`Content of the cart`);
    for(let tempCartItem of this.cartItem){
      const subTotalPrice = tempCartItem.quantity * tempCartItem.unitPrice;
      console.log(`name: ${tempCartItem.name}, quantity: ${tempCartItem.quantity}, 
      unitPrice: ${tempCartItem.unitPrice}, subTotalPrice: ${subTotalPrice}`);
    }
    console.log(`totalPriceValue: ${totalPriceValue.toFixed(2)}, 
    totalQuantity: ${totalQuantityValue}`);
    console.log(`===============`);
  }


  decrementQuantity(cartItem: CartItem) {
    cartItem.quantity--;
    if(cartItem.quantity === 0){
      this.remove(cartItem);
    }
    else{
      this.computeCartTotals();
    }
  }

  remove(cartItem: CartItem) {
    const index = this.cartItem.findIndex(tempCartItem => tempCartItem.id === cartItem.id);
    if(index > -1){
      this.cartItem.splice(index, 1);
      this.computeCartTotals();
    }
    
  }

}
