import { Component, OnInit } from '@angular/core';
import { ProductCategory } from '../../commons/product-category';
import { ProductListService } from '../../services/product-list.service';

@Component({
  selector: 'app-product-category-menu',
  templateUrl: './product-category-menu.component.html',
  styleUrl: './product-category-menu.component.css'
})
export class ProductCategoryMenuComponent implements OnInit{

  productCategories: ProductCategory[] = [];

  constructor(private productService: ProductListService){
    this.listProductCategories();
  }

  ngOnInit(): void {

  }

  listProductCategories(){
      this.productService.getProductCategories()
      .subscribe(data => {
        this.productCategories = data;
      })


  }

}
