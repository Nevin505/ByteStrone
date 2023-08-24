import { AddressDetails } from "./address-details";
import { CategoryDetails } from "./category-details";

export class AgentDetails {

    id:number=0;
    name:String='';
    userName:String='';
    email:String='';
    role:String='';
    userPassword:String='';
    addres:AddressDetails=new AddressDetails();
    category:CategoryDetails[]=[];

}

// Added on 13-08-2023
