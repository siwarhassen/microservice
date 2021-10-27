import { Component, OnInit } from '@angular/core';
import { Panier } from 'src/app/models/Panier';

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent implements OnInit {
  panier?: Panier;
  constructor() { }

  ngOnInit(): void {


    this.panier={
      'id':1,
      'totalPrice':500,
      'items':[
        {'quantity':1,
        'produit':"butter"
          
      },
      {'quantity':2,
      'produit':"cat"    
    }
      ],
     
    }
  }
  

}
