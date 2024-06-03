import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderHistory } from '../commons/order-history';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrderHistoryService {

  private orderUrl = environment.shopApiUrl +  "/orders";

  constructor(private httpClient: HttpClient) { }


  getOrderHistory(theEmail: string): Observable<GetResponseOrderHistory>{
    const orderHistoryUrl = `${this.orderUrl}/customerEmail?email=${theEmail}&sort=dateCreated,DESC`;
    return this.httpClient.get<GetResponseOrderHistory>(orderHistoryUrl);
  }
}

interface GetResponseOrderHistory{
  orderDtos: OrderHistory[],
  pageNum: number,
  pageSize: number,
  totalPages: number,
  totalElements: number,
  last: boolean

}
