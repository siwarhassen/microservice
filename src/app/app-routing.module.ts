import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnnonceComponent } from './components/annonce/annonce.component';
import { ConseilComponent } from './components/conseil/conseil.component';
import { HomeComponent } from './components/home/home.component';
import { PanierComponent } from './components/panier/panier.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { ProductsComponent } from './components/products/products.component';

const routes: Routes = [

  {path: 'home', component: HomeComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'products', component: ProductsComponent},
  {path: 'conseils', component: ConseilComponent},
  {path: 'annonces', component: AnnonceComponent},
  {path: 'detailproduct', component: ProductDetailComponent},
  {path: 'panier', component: PanierComponent},
  { path: '*', redirectTo: 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
