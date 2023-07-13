import { Component } from '@angular/core';
import { Agentlogin } from '../model/agentlogin';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { Category } from '../model/category';

@Component({
  selector: 'app-agent-login',
  templateUrl: './agent-login.component.html',
  styleUrls: ['./agent-login.component.css']
})
export class AgentLoginComponent {

  agent:Agentlogin=new Agentlogin();
  agentName!:string;
  agentPassword!:string;
  cate:Category=new Category();

  constructor(private api:ApiService,private router:Router){
  }


  loginAgent(){
   this.agent.agentName=this.agentName;
   this.agent.agentPassword=this.agentPassword;
    this.api.agentloginService(this.agent).subscribe((res:any)=>{
      // console.log(res);
      
      if(res.data!=null){
        console.log(res.data.agentID);
        this.api.agentIdSetter(res.data.agentID);
        console.log(res.data);
       
        this.api.agentCategorySetter(res.data.category);
        
        this.router.navigate(['agentView']);
      }
      else{
          alert(res.mssg);
      }
   
    })

  }

}
