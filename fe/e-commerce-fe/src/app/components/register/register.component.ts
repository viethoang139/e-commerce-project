import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {CustomValidators} from "../../commons/custom-validators";
import {RegistrationRequest} from "../../commons/registration-request";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

   registerForm!: FormGroup;
   registrationRequest: RegistrationRequest = new RegistrationRequest();
  constructor(private authService: AuthService,
              private formBuilder: FormBuilder){

  }
  ngOnInit(): void{
    this.registerForm = this.formBuilder.group({
      user: this.formBuilder.group({
        firstName: new FormControl('', [Validators.required, Validators.minLength(2), CustomValidators.notOnlyWhitespace]),
        lastName: new FormControl('', [Validators.required, Validators.minLength(2), CustomValidators.notOnlyWhitespace]),
        email: new FormControl('',
          [Validators.required,
            Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')
          ]
        ),
        password: new FormControl('', [Validators.required, Validators.minLength(8)])
      })
    })
  }


  get firstName(){
    return this.registerForm.get('user.firstName');
  }

  get lastName(){
    return this.registerForm.get('user.lastName');
  }

  get email(){
    return this.registerForm.get('user.email');
  }

  get password(){
    return this.registerForm.get('user.password');
  }

  register(){
    if(this.registerForm.invalid){
      this.registerForm.markAllAsTouched();
      return;
    }
    this.registrationRequest = this.registerForm.controls['user'].value;
    console.log(this.registrationRequest);
    this.authService.register(this.registrationRequest)
      .subscribe({
          next: () => {
            alert(`6 digit code has been sent to ${this.registrationRequest.email}. Please use that code to activate your account`);
          },
          error: err => {
            alert(`There was an error: ${err.message}`);
          }
        }
      )
  }
}
