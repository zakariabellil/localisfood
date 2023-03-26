import { Injectable } from '@angular/core';
import { product } from '../model/product.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateProductPayload } from '../create-product/create-product.payload';
@Injectable({
    providedIn: 'root'
  })
  export class ProductsService {
    constructor(private http: HttpClient) { }

    getAllProducts(): Observable<Array<product>> {
      return this.http.get<Array<product>>('http://localhost:8080/api/products/');
    }
  
    createProduct(productPayload: CreateProductPayload): Observable<any> {
      return this.http.post('http://localhost:8080/api/products/', productPayload);
    }
  
    getProduct(id: number): Observable<product> {
      return this.http.get<product>('http://localhost:8080/api/products/' + id);
    }
  
    getAllProductsByUser(name: string): Observable<product[]> {
      return this.http.get<product[]>('http://localhost:8080/api/products/by-user/' + name);
    }
  }