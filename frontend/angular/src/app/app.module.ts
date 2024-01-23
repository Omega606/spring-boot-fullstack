import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import { CustomerComponent } from './components/customer/customer.component';
import { MenuBarComponent } from './components/menu-bar/menu-bar.component';
import {AvatarModule} from "primeng/avatar";
import { MenuItemComponent } from './components/menu-item/menu-item.component';
import { HeaderBarComponent } from './components/header-bar/header-bar.component';
import {MenuModule} from "primeng/menu";
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {SidebarModule} from "primeng/sidebar";
import { ManageCustomerComponent } from './components/manage-customer/manage-customer.component';
import { LoginComponent } from './components/login/login.component';
import {InputTextModule} from "primeng/inputtext";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MessageModule} from "primeng/message";
import {HttpInterceptorService} from "./services/interceptor/http-interceptor.service";
import { CustomerCardComponent } from './components/customer-card/customer-card.component';
import {CardModule} from "primeng/card";
import {BadgeModule} from "primeng/badge";
import { RegisterComponent } from './components/register/register.component';
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {ToastModule} from "primeng/toast";
import {ConfirmationService, MessageService} from "primeng/api";

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    MenuBarComponent,
    MenuItemComponent,
    HeaderBarComponent,
    ManageCustomerComponent,
    LoginComponent,
    CustomerCardComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    AvatarModule,
    MenuModule,
    ButtonModule,
    RippleModule,
    SidebarModule,
    InputTextModule,
    HttpClientModule,
    MessageModule,
    CardModule,
    BadgeModule,
    ConfirmDialogModule,
    ToastModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    },
    MessageService,
    ConfirmationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
