import { Component, OnInit } from '@angular/core';
import { Payment } from './payment';
import { OrderService } from '../shopping-cart/order.service';
import { ViewChild } from '@angular/core';

@Component({
  selector: 'app-payment-success',
  templateUrl: './payment-success.component.html'
})
export class PaymentSuccessComponent {}
