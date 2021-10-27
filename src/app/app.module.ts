import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { ProductsComponent } from './components/products/products.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { AnnonceComponent } from './components/annonce/annonce.component';
import { ConseilComponent } from './components/conseil/conseil.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { PanierComponent } from './components/panier/panier.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AuthService } from './services/auth.service';
import { AuthGuard } from './auth.guard';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductsComponent,
    HeaderComponent,
    FooterComponent,
    AnnonceComponent,
    ConseilComponent,
    ProductDetailComponent,
    PanierComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,

    HttpModule,
  ],
  providers: [AuthService,AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
