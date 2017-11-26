import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Order } from './order';
import { Payment } from '../payment/payment';
import { ShoppingCartItem } from './shoppingCartIItem';
import { ShoppingCartService } from './shopping-cart.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class OrderService {

  private apiUrl = 'http://localhost:8080/api/order';

  constructor(
    private http: HttpClient,
    private shoppingCartService: ShoppingCartService) { }

  saveOrder(payment: Payment) {
    const order: Order = new Order(payment, this.shoppingCartService.items);

    return this.http.post(this.apiUrl, order, httpOptions)
      .toPromise()
      .then(response => {
        this.shoppingCartService.removeAll();
        return response;
      });
  }
}
