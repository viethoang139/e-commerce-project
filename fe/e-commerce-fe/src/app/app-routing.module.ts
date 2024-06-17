import {  NgModule } from '@angular/core';
import {  RouterModule, Routes } from '@angular/router';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { LoginComponent } from './components/login/login.component';
import { MembersPageComponent } from './components/members-page/members-page.component';
import { OrderHistoryComponent } from './components/order-history/order-history.component';
import {RegisterComponent} from "./components/register/register.component";
import {ActivateAccountComponent} from "./components/activate-account/activate-account.component";

const routes: Routes = [
  {
    path: 'order-history',
    component: OrderHistoryComponent,
  },
  {
    path: 'members',
    component: MembersPageComponent,
  },
  {
    path: 'activate-account',
    component: ActivateAccountComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: "checkout",
    component: CheckoutComponent
  },
  {
    path: "cart-details",
    component: CartDetailsComponent
  },
  {
    path: "search/:keyword",
    component: ProductListComponent
  },
  {
    path: "category/:id", component: ProductListComponent
  },
  {
    path: "category", component: ProductListComponent
  },
  {
    path: "product", component: ProductListComponent
  },
  {
    path: "product/:id",
    component: ProductDetailsComponent
  },
  {
    path: "", redirectTo: "/product", pathMatch: "full"
  },
  {
    path: "**", redirectTo: "/product", pathMatch: "full"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
