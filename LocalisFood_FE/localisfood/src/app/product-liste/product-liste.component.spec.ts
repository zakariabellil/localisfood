import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductListeComponent } from './product-liste.component';

describe('ProductListeComponent', () => {
  let component: ProductListeComponent;
  let fixture: ComponentFixture<ProductListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductListeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
