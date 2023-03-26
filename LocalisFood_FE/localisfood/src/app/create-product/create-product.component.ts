import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CreateProductPayload } from './create-product.payload';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HttpClient, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {


  
  createProductPayload: CreateProductPayload;
  creatProductForm!: FormGroup;
  selectedFile!: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message!: string;
  imageName: any;

  constructor(private router: Router, private toastr: ToastrService, private httpClient: HttpClient) {
this.createProductPayload = {
  name: '',
  description: '',
  prix : ''
}

   }

  ngOnInit(): void {
    this.creatProductForm = new FormGroup({
      name: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      prix : new FormControl('', Validators.required),
    });
  }
creerProd(){
  this.createProductPayload.description = this.creatProductForm.get('name')?.value;
  this.createProductPayload.name =  this.creatProductForm.get('description')?.value;
  this.createProductPayload.prix =   this.creatProductForm.get('prix')?.value;
}

public onFileChanged(event: any) {
  //Select File
  this.selectedFile = event.target.files[0];
}

//Gets called when the user clicks on submit to upload the image
onUpload() {
  console.log(this.selectedFile);
  
  //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
  const uploadImageData = new FormData();
  uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);

  //Make a call to the Spring Boot Application to save the image
  this.httpClient.post('http://localhost:8080/api/image/upload', uploadImageData, { observe: 'response' })
    .subscribe((response) => {
      if (response.status === 200) {
        this.message = 'Image uploaded successfully';
      } else {
        this.message = 'Image not uploaded successfully';
      }
    }
    );
}

}
