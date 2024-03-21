import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../environment/environment";
import { catchError, tap } from 'rxjs/operators';
import {BehaviorSubject, Observable} from "rxjs";
import {Router} from "@angular/router";
import {JwtHelperService} from "@auth0/angular-jwt";
//import {catchError, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = `${environment.urlApi}`

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  private roleSource = new BehaviorSubject<string | null>(this.getAccountRole());
  currentRole = this.roleSource.asObservable();

  constructor(private http: HttpClient, private router: Router,
              private jwtHelper: JwtHelperService) { }

  register(email: string, password: string, noSiren: number):Observable<any>{
    const role = {
      roleType: 'ROLE_USER_EMPLOYER'
    };

    return this.http.post(`${(this.url)}/signup`,
      {
        email,
        password,
        noSiren,
        role
      },
      this.httpOptions
    );

  }

  login(email: string, password: string):Observable<any>{
    return this.http.post(`${(this.url)}/signin`,
      {
        email,
        password
      },
      this.httpOptions
      )
      .pipe(
        tap((response:any) =>{
          if(response && response.accessToken){
            localStorage.setItem(('access_token'), response.accessToken);
            localStorage.setItem(('account_role'), response.roles);

            console.log("idEmpl from response:", response.idEmpl);
            localStorage.setItem(('employer_id'),response.idEmpl);

            this.roleSource.next(localStorage.getItem('account_role'));
          }
        }),
        catchError((error) => {
          throw error;
        })
      );
  }

  getAccountRole(): string | null {
    return localStorage.getItem('account_role');
  }

  getToken() {
    return localStorage.getItem('access_token');
  }

  logout(): void {
    localStorage.clear();
    this.roleSource.next(null);
  }

  // This could be part of your AuthService or a dedicated AuthGuard
  isLoggedIn(): boolean {
    const token = localStorage.getItem('access_token');
    if (!token) {
      return false;
    }
    return true;
  }


  // just to check token form
  decodeToken() {
    const token = localStorage.getItem('access_token');
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      console.log(decodedToken);
    }
  }

}
