import { Injectable } from '@angular/core';
import { ShoppingCartItem } from './shoppingCartIItem';
import { Product } from '../products/product';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

@Injectable()
export class ShoppingCartService {

  public items: ShoppingCartItem[] = [];

  constructor() { }

  addItem(product: Product, quantity: number) {
    this.items.push(new ShoppingCartItem(product, quantity));
  }

  removeItem(product: Product) {
    this.items = this.items.filter(i => i.product.id !== product.id);
  }

  isProductAdded(product: Product) {
    return this.items.filter(i => i.product.id === product.id).length > 0;
  }

  removeAll() {
    this.items = [];
  }
}
