import { AddressDetails } from "./address-details";
import { CategoryDetails } from "./category-details";
import { Skills } from "./skills";

export class Agent {
    id: number = 0;
    name: String = '';
    userName: String = '';
    userPassword: any = '';
    email: String = '';
    role: String = '';
    agentCount: number = 0;
    active: String = '';
    experiance: number = 0;
    address:AddressDetails=new AddressDetails();
    category:CategoryDetails[]=[];
	skills:Skills[]=[];
}
// Modified on 28-08-2023
