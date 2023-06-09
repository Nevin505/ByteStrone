import { Agentlogin } from './../model/agentlogin';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service'
import { Router } from '@angular/router';
import { CustomerLoginComponent } from '../customer-login/customer-login.component';
@Component({
  selector: 'app-agent-login',
  templateUrl: './agent-login.component.html',
  styleUrls: ['./agent-login.component.css']
})
export class AgentLoginComponent {

  agent1:Agentlogin=new Agentlogin

  agentName!:string;
  agentPassword!:string;
  response:any;
  // AgentForm!:FormGroup;
  // data:any;
  // AgentForm = new FormGroup({
  //   username: new FormControl('', Validators.required),
  //   userpassword: new FormControl('', Validators.required)})

  constructor(private loginService:LoginService, private route: Router) {

     
    }

    loginAgent(){
      this.agent1.agentName=this.agentName;
      this.agent1.agentPassword=this.agentPassword;
      this.loginService.agentloginService(this.agent1).subscribe(res=>{
        this.response=res;
        const agentid=this.response.agentID
        if(this.response!=null){
          this.route.navigate(['/agentDisplay',{ queryParams:  agentid  } ]);
        }
        else{
          alert('Invalid Login')
        }
        console.log(this.response);
      })

        }
    }
 

