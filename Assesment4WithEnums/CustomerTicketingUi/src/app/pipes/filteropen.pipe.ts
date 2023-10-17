import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filteropen'
})
export class FilteropenPipe implements PipeTransform {

  transform(value:any,args?:any) {
    if(!args){
      return value;
    }

    return value.filter((item:any)=>{
       return JSON.stringify(item.status)
       ==='open'
    })
  }

}
