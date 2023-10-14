import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateorder'
})
export class DateorderPipe implements PipeTransform {

  transform(value: any[]) {
    if (!Array.isArray(value)) {
      return value;
    }
    
    return value.sort((a,b)=>{
      let c=new Date(a.creation_Date).getTime();
      let d=new Date(b.creation_Date).getTime();
      return d-c
    })

  }


}
