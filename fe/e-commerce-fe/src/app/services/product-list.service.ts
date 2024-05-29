import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductList } from '../commons/product-list';
import { ProductCategory } from '../commons/product-category';

@Injectable({
  providedIn: 'root'
})
export class ProductListService {
 

  

  constructor(private httpClient: HttpClient) { }

  categoryUrl: string = "http://localhost:8080/api/categories";
  productUrl: string = "http://localhost:8080/api/products";

  getAllProduct(theCategoryId: number): Observable<ProductList[]>{
    const searchUrl = `${this.categoryUrl}/products/${theCategoryId}`;
    return this.httpClient.get<ProductList[]>(`${searchUrl}`);
  }

  getAllProductPaginate(pageNum: number, pageSize: number,categoryId: number): Observable<GetResponseProduct>{
    const searchUrl = `${this.categoryUrl}/products/${categoryId}?pageNum=${pageNum}&pageSize=${pageSize}`;
    return this.httpClient.get<GetResponseProduct>(`${searchUrl}`);
  }

  getProductCategories(): Observable<ProductCategory[]> {
    return this.httpClient.get<ProductCategory[]>(this.categoryUrl);
  }

  searchProducts(theKeyword: string): Observable<ProductList[]> {
    const searchUrl =  `${this.productUrl}/search?name=${theKeyword}`;
    return this.httpClient.get<ProductList[]>(searchUrl);
  }

  searchProductsPaginate(theKeyword: string, pageNum: number, pageSize: number): Observable<GetResponseProduct> {
    const searchUrl =  `${this.productUrl}/search?name=${theKeyword}&pageNum=${pageNum}&pageSize=${pageSize}`;
    return this.httpClient.get<GetResponseProduct>(searchUrl);
  }

  getProductDetails(productId: number): Observable<ProductList> {
    const searchUrl = `${this.productUrl}/${productId}`;
    return this.httpClient.get<ProductList>(searchUrl);
  }
}

interface GetResponseProduct{
  productDtos: ProductList[];
  pageNum: number;
  pageSize: number;
  totalPages: number;
  totalElements: number,
  last: boolean
}
