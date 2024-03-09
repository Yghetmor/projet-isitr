import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../environment/environment";

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = `${environment.urlApi}`

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };


  constructor(private http: HttpClient) { }

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

}
