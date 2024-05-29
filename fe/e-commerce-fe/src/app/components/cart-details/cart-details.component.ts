import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { CartItem } from '../../commons/cart-item';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrl: './cart-details.component.css'
})
export class CartDetailsComponent implements OnInit{




  cartItems: CartItem[] = [];
  totalPrice: number = 0.00;
  totalQuantity: number = 0;

  constructor(private cartService: CartService){

  }

  ngOnInit(): void {
      this.listCartDetails();
  }

  listCartDetails() {
    this.cartItems = this.cartService.cartItem;

    this.cartService.totalPrice.subscribe(data => {
      this.totalPrice = data;
    })

    this.cartService.totalQuantity.subscribe(data => {
      this.totalQuantity = data;
    })

    this.cartService.computeCartTotals();
  }

  increment(cartItem: CartItem) {
    this.cartService.addToCart(cartItem);
  }

  decrement(cartItem: CartItem) {
    this.cartService.decrementQuantity(cartItem);
  }

  remove(cartItem: CartItem) {
    this.cartService.remove(cartItem);
  }

}
