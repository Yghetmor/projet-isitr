import { Injectable } from '@angular/core';
import {environment} from "../environment/environment";
import {CommandModel} from "../model/command";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommandService {

  url = `${environment.urlApi}`

  constructor(private http: HttpClient) { }

  findByEmployerId(employerId: number): Observable<CommandModel[]> {
    return this.http.get<CommandModel[]>(`${this.url}/employer/${employerId}`);
  }

  findOne(id : number): Observable<CommandModel> {
    return this.http.get<CommandModel>(`${this.url}/commande/${id}`)
  }

  cancel(id: number) {
    return this.http.put(`${this.url}/annuler/${id}`,{})
  }

  add(value: CommandModel): Observable<CommandModel> {
    console.log(value)
    return this.http.post<CommandModel>(`${this.url}/commande`,value)
  }






}
