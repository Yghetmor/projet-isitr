import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {environment} from "../../environment/environment";
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import Validation from "../signup/utils/Validation";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  signInForm!: FormGroup;
  formSubmitted: boolean = false
  successMessage!: string
  errorMessage!: string
  private url = `${environment.urlApi}`

  constructor(private fb: FormBuilder,
              private authSerivce: AuthService,
              private router: Router) {
  };

  ngOnInit(): void {
    this.signInForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]]
    });
  }


  onSubmit() {
    this.formSubmitted = true;

    if (this.signInForm.invalid) {
      return;
    }

    const {email, password} = this.signInForm.value;

    this.authSerivce.login(email, password).subscribe(
      (response) => {

        //todo : must save data and token for current use

        this.errorMessage = '';


        const role = localStorage.getItem('account_role');
        console.log(role)
        let redirectUrl = '';

        switch(role) {
          case 'ROLE_ADMIN':
            redirectUrl = '/admin/home';
            break;
          case 'ROLE_USER_EMPLOYER':
            redirectUrl = '/employer/home';
            break;
          default:
            // No matching role found, prepare an error message
            this.errorMessage = 'Unauthorized: No valid role found for this account.';
            console.error('Login error: Unauthorized access attempt due to invalid role.');
            return; // Exit the method to prevent further execution
        }

        this.successMessage = 'Login successful';

        setTimeout(() => {
          this.router.navigate([redirectUrl]);
        }, 2000);

      },
      (error) => {
        this.errorMessage = error.error.message;
        console.error('Login error:', error);
      }
    );

  }
}
