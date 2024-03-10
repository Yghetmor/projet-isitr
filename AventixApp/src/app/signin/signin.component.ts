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
        this.successMessage = 'Login successful';

        setTimeout(() => {
          this.router.navigate([`home`]);
        }, 2000);

      },
      (error) => {
        this.errorMessage = error.error.message;
        console.error('Login error:', error);
      }
    );

  }
}
