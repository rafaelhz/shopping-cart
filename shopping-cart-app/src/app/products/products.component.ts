import { Component, OnInit } from '@angular/core';
import { FlashMessagesService } from 'angular2-flash-messages';
import { Product } from './product';
import { ShoppingCartItem } from '../shopping-cart/shoppingCartIItem';
import { ProductService } from './product.service';
import { ShoppingCartService } from '../shopping-cart/shopping-cart.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Product[];
  cartItems: ShoppingCartItem[] = [];

  constructor(
    private productService: ProductService,
    private shoppingCartService: ShoppingCartService,
    private flashMessagesService: FlashMessagesService) {}

  ngOnInit() {
    this.productService.getProducts()
      .subscribe(
        products => this.products = products,
        error => this.flashMessagesService.show('Error loading products.', { cssClass: 'alert-danger' })
      );
  }

  isProductAdded(product: Product): boolean {
    return this.shoppingCartService.isProductAdded(product);
  }

  addToCart(product: Product) {
    this.shoppingCartService.addItem(product, 1);
  }

  remove(product: Product) {
    this.shoppingCartService.removeItem(product);
  }
}
