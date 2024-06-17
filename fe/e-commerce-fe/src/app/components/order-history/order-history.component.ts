import { Component, OnInit } from '@angular/core';
import { OrderHistory } from '../../commons/order-history';
import { OrderHistoryService } from '../../services/order-history.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrl: './order-history.component.css'
})
export class OrderHistoryComponent implements OnInit{

  orderHistoryList: OrderHistory[] = [];


  constructor(private orderHistoryService: OrderHistoryService){

  }

  ngOnInit(): void {
        this.handleOrderHistory();
  }

  handleOrderHistory() {
      const theEmail = sessionStorage.getItem('userEmail');
      this.orderHistoryService.getOrderHistory(theEmail)
      .subscribe(data => {
         this.orderHistoryList = data.orderDtos;
      })
  }
}
