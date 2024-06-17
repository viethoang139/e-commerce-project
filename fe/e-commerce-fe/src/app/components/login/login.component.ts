import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationRequest} from "../../commons/authentication-request";
import {TokenService} from "../../services/token.service";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  loginForm: FormGroup;
  authenticateRequest: AuthenticationRequest;
  token: string;
  isLogin = "";
  userEmail = "";
  constructor(private router: Router,
              private authService: AuthService,
              private formBuilder: FormBuilder,
              private tokenService: TokenService){

  }
  ngOnInit(): void {
      this.loginForm = this.formBuilder.group({
        login: this.formBuilder.group({
            email: new FormControl('',[Validators.required,
              Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')
            ]),
            password: new FormControl('',[Validators.required, Validators.minLength(8)])
        })
      })
  }

  get email(){
    return this.loginForm.get('login.email');
  }

  get password(){
    return this.loginForm.get('login.password');
  }

  authenticate() {
    if(this.loginForm.invalid){
      this.loginForm.markAllAsTouched();
      return;
    }
      this.authenticateRequest = this.loginForm.get('login').value;
      this.userEmail = this.authenticateRequest.email;
      this.authService.authenticate(this.authenticateRequest)
        .subscribe(
          data => {
               this.tokenService.token = data.token as string;
               this.authService.isUserLogin.next(true);
               this.isLogin = "user has login";
               this.authService.isUserLoggedIn = this.isLogin;
               this.authService.userEmail = this.userEmail;
               this.router.navigate(['product']);
          },
      );
  }
}
