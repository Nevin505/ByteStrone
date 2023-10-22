import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router } from '@angular/router';

export const guardGuard: CanActivateFn = (route:ActivatedRouteSnapshot) => {
  const router=inject(Router);
   
  var role=sessionStorage.getItem('role');

  if(role===route.data['roles']){
    return true;
  }
  else{
    alert("Permission Denied")
    router.navigate(['']);
    return false;
  }
  
};
