import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-registro-cuidador',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule
  ],
  templateUrl: './registro-cuidador-component.html',
  styleUrl: './registro-cuidador-component.css'
})
export class RegistroCuidadorComponent {

  registroForm: FormGroup;

  private readonly API_URL = 'http://localhost:8080/api/register-cuidador';

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
      password: ['', Validators.required],
      experiencia: ['', Validators.required],
      tarifa: ['', Validators.required],
      descripcion: ['', Validators.required],
      antecedentes: ['', Validators.required],
      disponibilidad: ['', Validators.required]
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
          password: form.password,
          descripcion: form.descripcion,
          tarifa: form.tarifa,
          experiencia: form.experiencia,
          antecedentes: form.antecedentes,
          disponibilidad: form.disponibilidad
        })
      });

      if (!response.ok) {
        throw new Error('Error al registrar cuidador');
      }

      alert('Cuidador registrado correctamente');
      this.router.navigate(['/login']);

    } catch (error) {
      console.error('ERROR REAL:', error);
      alert('Ocurrió un error al registrar cuidador');
    }
  }
}
