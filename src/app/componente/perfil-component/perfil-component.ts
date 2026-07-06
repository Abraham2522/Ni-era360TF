import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

import { UserProfile } from '../../model/user-profile';
import { UserProfileService } from '../../services/user-profile-service';

@Component({
  selector: 'app-perfil-component',
  imports: [
    RouterLink,
    FormsModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule
  ],
  templateUrl: './perfil-component.html',
  styleUrl: './perfil-component.css',
})
export class PerfilComponent implements OnInit {

  usuario: UserProfile = new UserProfile();

  idUsuario: number = Number(sessionStorage.getItem('idUsuario'));

  constructor(
    private userProfileService: UserProfileService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarPerfil();
  }

  cargarPerfil() {
    this.userProfileService.listId(this.idUsuario).subscribe((data: UserProfile) => {
      this.usuario = data;
    });
  }

  guardarCambios() {
    this.userProfileService.update(this.usuario).subscribe(() => {
      alert('Perfil actualizado correctamente');
      this.cargarPerfil();
    });
  }

  eliminarCuenta() {
    const confirmar = confirm('¿Seguro que deseas desactivar/eliminar tu cuenta?');

    if (confirmar) {
      this.userProfileService.delete(this.usuario.idUsuario).subscribe(() => {
        alert('Cuenta eliminada correctamente');
        this.router.navigate(['/login']);
      });
    }
  }
}
