import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule
  ],
  templateUrl: './login-component.html',
  styleUrl: './login-component.css'
})
export class LoginComponent {

  loginForm: FormGroup;
  errorMessage = '';
  isSubmitting = false;
  hidePassword = true;

  private readonly API_URL = 'http://localhost:8080/api/authenticate';

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  async onSubmit(): Promise<void> {

    this.errorMessage = '';

    if (this.loginForm.invalid) return;

    this.isSubmitting = true;

    const { username, password } = this.loginForm.value;

    try {

      const response = await fetch(this.API_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          username,
          password
        })
      });

      if (!response.ok) {
        throw new Error('Login incorrecto');
      }

      const data = await response.json();

      const roles: string[] = data.roles ?? [];

      sessionStorage.setItem('token', data.jwt);
      sessionStorage.setItem('roles', JSON.stringify(roles));
      sessionStorage.setItem('idUsuario', String(data.idUsuario));

      if (data.idCliente !== null && data.idCliente !== undefined) {
        sessionStorage.setItem('idCliente', String(data.idCliente));
      }

      if (data.idCuidador !== null && data.idCuidador !== undefined) {
        sessionStorage.setItem('idCuidador', String(data.idCuidador));
      }

      console.log('ID USUARIO:', data.idUsuario);
      console.log('ID CLIENTE:', data.idCliente);
      console.log('ID CUIDADOR:', data.idCuidador);
      console.log('ROLES:', roles);

      if (roles.includes('ROLE_CLIENTE')) {
        this.router.navigate(['/home-cliente']);
      }
      else if (roles.includes('ROLE_CUIDADOR')) {
        this.router.navigate(['/home-cuidador']);
      }
      else {
        this.router.navigate(['/home']);
      }

    }
    catch (err) {
      console.error(err);
      this.errorMessage = 'Usuario o contraseña incorrectos';
    }
    finally {
      this.isSubmitting = false;
    }
  }

  private showError(message: string): void {
    this.errorMessage = message;
  }
}
