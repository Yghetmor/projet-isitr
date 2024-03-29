import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {ReactiveFormsModule} from "@angular/forms";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';

import {HttpClientModule} from "@angular/common/http";
import { PortalComponent } from './portal/portal.component';
import { AppRoutingModule } from './app-routing.module';
import {RouterModule, Routes} from "@angular/router";
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';
import { HomeAdminComponent } from './home-admin/home-admin.component';
import {httpInterceptorProviders} from "./helpers/HttpRequestInterceptor";
import { HomeEmployerComponent } from './home-employer/home-employer.component';
import { AdminBoardComponent } from './board/admin-board/admin-board.component';
import { EmployerBoardComponent } from './board/employer-board/employer-board.component';
import { DefaultBoardComponent } from './board/default-board/default-board.component';
import {AuthGuard} from "./guards/auth.guard";
import {RoleGuard} from "./guards/role.guard";
import {NoRoleGuard} from "./guards/no-role.guard";
import { CommandListComponent } from './command-list/command-list.component';
import { CommandComponent } from './command/command.component';

import {JwtModule} from "@auth0/angular-jwt";

import { CommandDetailsComponent } from './command-details/command-details.component';
import { CommandCreateComponent } from './command-create/command-create.component';

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

const routes: Routes = [
  {path: 'home', component: HomeComponent, canActivate: [NoRoleGuard]},
  {path: 'home/portal', component: PortalComponent, canActivate: [NoRoleGuard]},
  {path: 'home/portal/signup', component: SignupComponent, canActivate: [NoRoleGuard]},
  {path: 'home/portal/signin', component: SigninComponent, canActivate: [NoRoleGuard]},
  {path: 'employer/home', component: HomeEmployerComponent,canActivate: [AuthGuard, RoleGuard], data: { requiredRoles: ['ROLE_USER_EMPLOYER'] }},
  {path: 'employer/commands', component: CommandListComponent,canActivate: [AuthGuard, RoleGuard], data: { requiredRoles: ['ROLE_USER_EMPLOYER'] }},
  {path: 'employer/commands/create', component: CommandCreateComponent,canActivate: [AuthGuard, RoleGuard], data: { requiredRoles: ['ROLE_USER_EMPLOYER'] }},
  {path: 'employer/commands/:cId', component: CommandDetailsComponent,canActivate: [AuthGuard, RoleGuard], data: { requiredRoles: ['ROLE_USER_EMPLOYER'] }},
  {path: 'admin/home', component: HomeAdminComponent,canActivate: [AuthGuard, RoleGuard], data: { requiredRoles: ['ROLE_ADMIN'] }},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
];
@NgModule({
  declarations: [
    AppComponent,
    PortalComponent,
    HomeComponent,
    SignupComponent,
    SigninComponent,
    HomeAdminComponent,
    HomeEmployerComponent,
    AdminBoardComponent,
    EmployerBoardComponent,
    DefaultBoardComponent,
    CommandListComponent,
    CommandComponent,
    CommandDetailsComponent,
    CommandCreateComponent,

  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        // Add other JwtModule options as needed
      },
    }),
    RouterModule.forRoot(routes),

  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
