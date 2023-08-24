import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';


export const authGuard: CanActivateFn = (route, state) => {
  const token=sessionStorage.getItem('token')||' ';

  const router=inject(Router);
  if(token==='t'){
    return true;
  }
  else{
   router.navigate(['']);
   return false;
  }
  
};
