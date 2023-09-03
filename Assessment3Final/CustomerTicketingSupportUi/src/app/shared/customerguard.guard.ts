import {  inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const customerguardGuard: CanActivateFn = () => {
  const router=inject(Router);
  if(sessionStorage.getItem("customerRoutes")){
    return true;
  }
  else{
    router.navigate(['']);
    return false;
  }
 
};
