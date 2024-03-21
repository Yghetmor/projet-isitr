import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthService} from "../auth.service";

@Injectable({
  providedIn: 'root'
})
export class NoRoleGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    const accountRole = this.authService.getAccountRole();

    if (accountRole === 'ROLE_USER_EMPLOYER') {
      return this.router.createUrlTree(['/employer/home']); // Redirect employers
    } else if (accountRole === 'ROLE_ADMIN') {
      return this.router.createUrlTree(['/admin/home']); // Redirect admins
    }

    return true; // Allow access if there's no role
  }

}
