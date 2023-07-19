import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterByStatus'
})
export class FilterByStatusPipe implements PipeTransform {

  transform(value: any, args: any) {
    if (!args || args === '') {
      return value;
    }
    return value.filter((item:any)=>{
      return JSON.stringify(item.status).includes(args)
    });
   
  }

}
