import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent {

  constructor(private route:Router)
  {
  }
  goCustomer(){
    this.route.navigate(['customer'])

  }
  goAgent(){
    this.route.navigate(['agentlogin'])
  }
}
