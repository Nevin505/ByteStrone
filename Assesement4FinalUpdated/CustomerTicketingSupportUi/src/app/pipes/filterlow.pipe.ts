import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterlow'
})
export class FilterlowPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
