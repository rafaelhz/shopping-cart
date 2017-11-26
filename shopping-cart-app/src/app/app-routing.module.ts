import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { PaymentComponent } from './payment/payment.component';
import { PaymentSuccessComponent } from './payment/payment-success.component';
import { HeaderComponent } from './header.component';

const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', component: ProductsComponent },
     ],
    component: HeaderComponent
  },
  { path: 'pay', component: PaymentComponent },
  { path: 'success', component: PaymentSuccessComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
