import { Component, OnInit, Input } from '@angular/core';
import { product } from 'src/app/model/product.model';
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  @Input() productItem !: product;
  constructor() { }

  ngOnInit(): void {
  }

}
