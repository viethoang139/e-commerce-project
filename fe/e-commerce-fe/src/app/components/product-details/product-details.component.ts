import { Component, OnInit } from '@angular/core';
import { ProductListService } from '../../services/product-list.service';
import { ActivatedRoute } from '@angular/router';
import { ProductList } from '../../commons/product-list';
import { CartService } from '../../services/cart.service';
import { CartItem } from '../../commons/cart-item';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent implements OnInit{


  productId: number = 1;

  product!: ProductList;



  constructor(private productService: ProductListService,
    private route: ActivatedRoute, private cartService: CartService
  ){

  }

  ngOnInit(): void {
      this.route.paramMap.subscribe(() => {
        this.getProductDetails();
      })
  }

  getProductDetails(){
    this.productId = +this.route.snapshot.paramMap.get("id")!;

   
     
      this.productService.getProductDetails(this.productId)
      .subscribe(data => {
        this.product = data;
      });

    }

    addToCart() {
        const theCartItem = new CartItem(this.product);
        this.cartService.addToCart(theCartItem);
    }

  }
