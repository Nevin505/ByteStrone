import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-show-details',
  templateUrl: './show-details.component.html',
  styleUrls: ['./show-details.component.css']
})
export class ShowDetailsComponent {
  constructor(private route:Router,private httpcilent:HttpClient,private loginservice:LoginService){

  }
  data:any;
  ngOnInit(){
    this.loginservice.getCustomerTicketDetails().subscribe(res=>{
      // console.log(res);
      this.data=res;
      console.log(res);
      // this.data.
      // this.data[0].
      
      
    })
  }

}
