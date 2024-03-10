import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {ReactiveFormsModule} from "@angular/forms";

import { AppComponent } from './app.component';

import {HttpClientModule} from "@angular/common/http";
import { PortalComponent } from './portal/portal.component';
import { AppRoutingModule } from './app-routing.module';
import {RouterModule, Routes} from "@angular/router";
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';



const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'home/portal', component: PortalComponent},
  {path: 'home/portal/signup', component: SignupComponent},
  {path: 'home/portal/signin', component: SigninComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
];
@NgModule({
  declarations: [
    AppComponent,
    PortalComponent,
    HomeComponent,
    SignupComponent,
    SigninComponent,

  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
