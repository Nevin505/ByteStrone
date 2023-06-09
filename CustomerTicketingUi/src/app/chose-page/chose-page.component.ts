import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chose-page',
  templateUrl: './chose-page.component.html',
  styleUrls: ['./chose-page.component.css']
})
export class ChosePageComponent {
  constructor(private route:Router)
  {

  }
  goCustomer(){
    this.route.navigate(['customerlogin'])

  }
  goAgent(){
    this.route.navigate(['agentlogin'])
  }
  
}
