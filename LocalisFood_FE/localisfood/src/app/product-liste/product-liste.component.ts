import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/shared/products.service';
import { product } from 'src/app/model/product.model';

@Component({
  selector: 'app-product-liste',
  templateUrl: './product-liste.component.html',
  styleUrls: ['./product-liste.component.css']
})
export class ProductListeComponent implements OnInit {

  productList: product[] = []

  constructor( private productsService: ProductsService) { }

  ngOnInit(): void {
  }

  loadProducts() {
    this.productsService.getAllProducts().subscribe((products) => {
    this.productList = products;
    })
  }
}
