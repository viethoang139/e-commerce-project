import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

import {BehaviorSubject, Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {RegistrationRequest} from "../commons/registration-request";
import {AuthenticationRequest} from "../commons/authentication-request";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private httpClient: HttpClient) {

  }

  base_url = environment.shopApiUrl + "/auth";
  isUserLogin: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  register(registrationRequest: RegistrationRequest): Observable<any>{
      const registerUrl = `${this.base_url}/register`;
      return this.httpClient.post(registerUrl, registrationRequest);
  }

  authenticate(authenticationRequest: AuthenticationRequest): Observable<any>{
    const loginUrl = `${this.base_url}/authenticate`;
    return this.httpClient.post(loginUrl,authenticationRequest);

  }
  confirm(token: string): Observable<any> {
      const activateAccountUrl = `${this.base_url}/activate-account?token=${token}`;
      return this.httpClient.get(activateAccountUrl);
  }

  set userEmail(email: string){
      sessionStorage.setItem('userEmail', email);
  }

  get userEmail(){
    return sessionStorage.getItem('userEmail');
  }

  set isUserLoggedIn(isLogin: string){
      sessionStorage.setItem('isLogin', isLogin)
  }

}
