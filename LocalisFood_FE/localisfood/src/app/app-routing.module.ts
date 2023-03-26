import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProductComponent } from './product-liste/product/product.component';
import { UploadComponent } from './upload/upload.component';
import { AuthGuard } from './auth/authguard.guard';
import { CreateProductComponent} from './create-product/create-product.component';


const routes: Routes = [
  { path: 'sign-up', component: SignUpComponent },
  { path: 'login', component: LoginComponent },
  { path: 'Dashboard', component: DashboardComponent , canActivate: [AuthGuard]},
  { path: 'Product', component: ProductComponent },
  { path: 'Upload', component: UploadComponent , canActivate: [AuthGuard]},
  { path: 'CreateProductComponent', component: CreateProductComponent },
  { path: '', redirectTo: '/Dashboard', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }