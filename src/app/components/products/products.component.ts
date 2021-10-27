import { Component, OnInit } from '@angular/core';
import { Produit } from 'src/app/models/Produit';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  produits?: Produit[];
  constructor() { }

  ngOnInit(): void {
  }


}
