import { Product } from '../products/product';
import { ShoppingCartItem } from './shoppingCartIItem';
import { Payment } from '../payment/payment';

export class Order {
  clientName: string;
  creditCardNumber: string;
  creditCardExpirationMonth: number;
  creditCardExpirationYear: number;
  items: ShoppingCartItem[];

  constructor(payment: Payment, items: ShoppingCartItem[]) {
    this.clientName = payment.clientName;
    this.creditCardNumber = payment.creditCardNumber;
    this.creditCardExpirationMonth = payment.creditCardExpirationMonth;
    this.creditCardExpirationYear = payment.creditCardExpirationYear;
    this.items = items;
  }
}
