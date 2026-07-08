import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule
} from '@angular/forms';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-registro-cliente',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    RouterLink
  ],
  templateUrl: './registro-cliente-component.html',
  styleUrl: './registro-cliente-component.css'
})
export class RegistroClienteComponent {

  registroForm: FormGroup;

  private readonly API_URL = 'https://ni-era360tfbackend.onrender.com/api/register-cliente';

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {

    this.registroForm = this.fb.group({
      nombre: ['', Validators.required],
      apellidos: ['', Validators.required],
      dni: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefono: ['', Validators.required],
      direccion: ['', Validators.required],
      password: ['', Validators.required]
    });

  }

  async onSubmit(): Promise<void> {
    if (this.registroForm.invalid) {
      this.registroForm.markAllAsTouched();
      return;
    }

    const form = this.registroForm.value;

    try {
      const response = await fetch(this.API_URL, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
          nombre: form.nombre,
          apellidos: form.apellidos,
          dni: form.dni,
          direccion: form.direccion,
          telefono: form.telefono,
          correo: form.email,
          password: form.password
        })
      });

      if (!response.ok) {
        throw new Error('Error al registrar cliente');
      }

      alert('Cliente registrado correctamente');
      this.router.navigate(['/login']);

    } catch (error) {
      console.error('ERROR REAL:', error);
      alert('Ocurrió un error al registrar cliente');
    }
  }
}
