import { Injectable } from '@angular/core';
import {environment} from "../environment/environment";
import {CommandModel} from "../model/command";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommandService {

  url = `${environment.urlApi}`

  constructor(private http: HttpClient) { }

  findByEmployerId(employerId: number): Observable<CommandModel[]> {
    return this.http.get<CommandModel[]>(`${this.url}/employer/${employerId}`);
  }






}
