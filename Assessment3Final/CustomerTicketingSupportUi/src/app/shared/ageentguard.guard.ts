import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const ageentguardGuard: CanActivateFn = () => {
  const router=inject(Router);
  if(sessionStorage.getItem("agentRoutes")){
    return true;
  }
  else{
    router.navigate(['']);
    return false;
  }
 
};
