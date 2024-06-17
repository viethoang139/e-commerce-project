import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrl: './login-status.component.css'
})
export class LoginStatusComponent implements OnInit{

  isAuthenticated: boolean;

  constructor(
      private route: Router,
      private authService: AuthService
  ){
      this.authService.isUserLogin.subscribe(value => {
        this.isAuthenticated = value;
      })
  }
  ngOnInit(): void {
    this.isAuthenticated = sessionStorage.getItem('isLogin') === "user has login";
  }

  logout(){
      localStorage.removeItem('token');
      sessionStorage.removeItem('isLogin');
      this.isAuthenticated = false;
      this.route.navigate(['login']);
  }



}
