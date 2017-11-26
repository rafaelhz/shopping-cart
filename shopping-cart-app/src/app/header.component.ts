import { Component} from '@angular/core';
import { ShoppingCartService } from './shopping-cart/shopping-cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent {

  constructor(
    private shoppingCartService: ShoppingCartService) {}

  get totalCartItems(): number {
    return this.shoppingCartService.items.length;
  }
}
