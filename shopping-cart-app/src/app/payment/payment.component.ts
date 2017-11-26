import { Component } from '@angular/core';
import { ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { FlashMessagesService } from 'angular2-flash-messages';
import { Payment } from './payment';
import { OrderService } from '../shopping-cart/order.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html'
})
export class PaymentComponent {

  payment: Payment = new Payment();

  @ViewChild('paymentForm') form;

  constructor(
    private orderService: OrderService,
    private router: Router,
    private flashMessagesService: FlashMessagesService) {}

  submit() {
    this.orderService.saveOrder(this.payment)
      .then(res => {
        this.form.resetForm();
        this.router.navigate(['./success']);
      })
      .catch(err => {
        this.flashMessagesService.show('Error sending order.', { cssClass: 'alert-danger' });
      });
  }

  onlyNumbers(event: any) {
    const pattern = /[0-9\+\-\ ]/;
    const inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }
}
