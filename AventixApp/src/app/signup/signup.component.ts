import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import Validation from "./utils/Validation";
import {AuthService} from "../auth.service";
import {environment} from "../../environment/environment";
import {Router} from "@angular/router";


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{
   signUpForm!: FormGroup;
  formSubmitted: boolean = false
  successMessage!: string
  errorMessage!: string
  private url = `${environment.urlApi}`

  constructor(private fb: FormBuilder,
              private authSerivce: AuthService,
              private router: Router){};

  ngOnInit(): void {
    this.signUpForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      noSiren: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]]
      },{
        validators: [Validation.match('password','confirmPassword')]
      });
  }

  onSubmit() {
    this.formSubmitted = true;

    if (this.signUpForm.invalid) {
      return;
    }

    const { email, noSiren, password } = this.signUpForm.value;

    this.authSerivce.register(email, password, noSiren).subscribe(
      (response) => {
        this.errorMessage = '';
        this.successMessage = 'Registration successful';
        setTimeout(() => {
          this.router.navigate([`home`]);
          //change to login page when created

        }, 2000);
      },
      (error) => {
        this.errorMessage = error.error.message;
        console.error('Registration error:', error);
      }
    );

  }

}
