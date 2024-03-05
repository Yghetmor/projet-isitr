import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import Validation from "./utils/Validation";


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{
   signUpForm!: FormGroup;
  formSubmitted: boolean = false

  constructor(private fb: FormBuilder){};

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

    console.log(JSON.stringify(this.signUpForm.value, null, 2));
  }

}
