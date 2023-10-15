import { Route, Router } from '@angular/router';
import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Category } from '../model/category';
import { Ticket } from '../model/ticket';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-customerview',
  templateUrl: './customerview.component.html',
  styleUrls: ['./customerview.component.css']
})
export class CustomerviewComponent {
  value1: number = 0;
  raiseValue: boolean = false;
  categoryList!: Category[];
  Subject!: string;
  Description!: string;
  priority!: string;

  categoryName!: string;

  response: any;

  cateName!: string;
  category: Category = new Category();
  details: any;

  ticketForm = new FormGroup({
    subject: new FormControl('', [Validators.required, Validators.pattern(/\S/)]),
    description: new FormControl('',[Validators.required, Validators.pattern(/\S/)]),
    
    priority: new FormControl('', Validators.required),
    categoryName: new FormControl('', Validators.required),
  })

  constructor(private api: ApiService, private route: Router) {

  }
  customerName: String = '';
  time!: Date;


  ngOnInit() {
    this.api.getCategory().subscribe({
      next: (res: any) => {
        if (res.success) {
          this.categoryList = res.data;
        }
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error.mssg)
      }
    })
    this.time = new Date();

    this.customerName = sessionStorage.getItem("customerName") || ' ';

  }

  onRaiseTicket() {
    this.raiseValue = true;
  }
  removeData() {
    sessionStorage.clear();
    this.route.navigate(['/landingPage'])

  }
  addedValue() {
    this.api.addTicket(this.ticketForm.value).subscribe({
      next: (res: any) => {
        if (res.success) {
          alert(res.mssg)
          this.ticketForm.reset();
        }
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error.mssg)
      }

    });
  }



}




