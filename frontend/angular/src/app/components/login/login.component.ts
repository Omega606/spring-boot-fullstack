import { Component } from '@angular/core';
import {AuthenticationRequest} from "../../models/authentication-request";
import {AuthenticationService} from "../../services/authentication/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  authenticationRequest: AuthenticationRequest = {};
  errorMsg = '';

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {}

  login() {
    this.errorMsg = '';
    this.authenticationService.login(this.authenticationRequest)
      .subscribe({
        next: (authenticationResponse) => {
          localStorage.setItem('user_access_token', JSON.stringify(authenticationResponse));
          this.router.navigate(['customers']);
        },
        error: (err) => {
          if (err.error.statusCode === 401) {
            this.errorMsg = 'Username and / or Password is incorrect';
          }
        }
        });
  }

  register() {
    this.router.navigate(['register']);
  }
}
