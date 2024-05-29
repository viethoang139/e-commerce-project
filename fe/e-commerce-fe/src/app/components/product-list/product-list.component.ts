import { Component, OnInit } from '@angular/core';
import { ProductList } from '../../commons/product-list';
import { ProductListService } from '../../services/product-list.service';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { CartItem } from '../../commons/cart-item';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit{



  productList: ProductList[] = [];

  currentCategoryId: number = 1;
  previousCategoryId: number = 1;

  previousKeyword: string = "";

  searchMode: boolean = false;

  pageNum: number = 1;
  
  pageSize: number = 10;

  totalElements: number = 0;


  
  constructor(private productService: ProductListService,
    private route: ActivatedRoute, private cartService: CartService
  ){

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.getAllProducts();
    });
      
  }

  getAllProducts(){
      this.searchMode = this.route.snapshot.paramMap.has('keyword');
      if(this.searchMode){
        this.handleSearchProduct();
      }
      else{
        this.handleListProduct();
      }
      
  }
  handleSearchProduct() {
     const theKeyword: string = this.route.snapshot.paramMap.get("keyword")!;


     if(this.previousKeyword != theKeyword){
      this.pageNum = 1;
     }

     this.previousKeyword = theKeyword;


     this.productService.searchProductsPaginate(theKeyword, this.pageNum - 1, this.pageSize)
     .subscribe(data => {
        this.productList = data.productDtos;
        this.pageNum = data.pageNum + 1;
        this.pageSize = data.pageSize;
        this.totalElements = data.totalElements;
     })

  }

  handleListProduct(){
    const hasCategoryId: boolean =
    this.route.snapshot.paramMap.has('id');

    if(hasCategoryId){
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
    }
    else{
      this.currentCategoryId = 1;
    }

    if(this.previousCategoryId != this.currentCategoryId){
      this.pageNum = 1;
    }

    this.previousCategoryId = this.currentCategoryId;

    


      return this.productService.getAllProductPaginate(this.pageNum - 1, this.pageSize, this.currentCategoryId)
      .subscribe(data => {
         this.productList = data.productDtos;
         this.pageNum = data.pageNum + 1;
         this.pageSize = data.pageSize;
          this.totalElements = data.totalElements;
        
      })
  }

  updatePageSize(event: any) {
      this.pageSize = +event.target.value;
      this.pageNum = 1;  
      this.getAllProducts();
  }

  addToCart(product: ProductList) {
    console.log(`Product price: ${product.unitPrice}`);
    console.log(`Product name: ${product.name}`);

    const theCartItem = new CartItem(product);

    this.cartService.addToCart(theCartItem);
  }

    
}
