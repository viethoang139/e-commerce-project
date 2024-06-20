import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.css'
})
export class ActivateAccountComponent {
    message = "";
    isOkay = true;
    submitted = false;

    constructor(
      private router: Router,
      private authService: AuthService
    ) {
    }

  onCodeCompleted(token: string){
        console.log(token);
        this.confirmationAccount(token);
  }

  redirectToLogin() {
    this.router.navigate(['login']);
  }

  private confirmationAccount(token: string) {
      this.authService.confirm(token)
        .subscribe({
          next: () => {
            this.message = "Your account has been successfully activated/\nNow you can process to login";
            this.submitted = true;
            this.isOkay = true;
          },
          error: () => {
            this.message = "Token has been expired or invalid";
            this.submitted = true;
            this.isOkay = false;
          }
        });
  }

  redirectToActivateAccount() {
    this.router.navigate(['activate-account'])
  }
}
