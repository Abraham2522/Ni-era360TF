import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

import { UserProfile } from '../../model/user-profile';
import { Cuidador } from '../../model/cuidador';

import { UserProfileService } from '../../services/user-profile-service';
import { CuidadoresService } from '../../services/cuidadores-service';

@Component({
  selector: 'app-perfil-cuidador-component',
  imports: [
    RouterLink,
    FormsModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule
  ],
  templateUrl: './perfil-cuidador-component.html',
  styleUrl: './perfil-cuidador-component.css',
})
export class PerfilCuidadorComponent implements OnInit {

  usuario: UserProfile = new UserProfile();
  cuidador: Cuidador = new Cuidador();

  idUsuario: number = Number(sessionStorage.getItem('idUsuario'));
  idCuidador: number = 1;

  constructor(
    private userProfileService: UserProfileService,
    private cuidadoresService: CuidadoresService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarPerfil();
  }

  cargarPerfil() {
    this.userProfileService.listId(this.idUsuario).subscribe((data: UserProfile) => {
      this.usuario = data;
    });

    this.cuidadoresService.listId(this.idCuidador).subscribe((data: Cuidador) => {
      this.cuidador = data;
    });
  }

  guardarCambios() {
    this.userProfileService.update(this.usuario).subscribe(() => {
      this.cuidadoresService.update(this.cuidador).subscribe(() => {
        alert('Perfil profesional actualizado correctamente');
        this.cargarPerfil();
      });
    });
  }

  eliminarPerfil() {
    const confirmar = confirm('¿Seguro que deseas desactivar/eliminar tu perfil?');

    if (confirmar) {
      this.cuidadoresService.delete(this.cuidador.idCuidador).subscribe(() => {
        alert('Perfil eliminado correctamente');
        this.router.navigate(['/login']);
      });
    }
  }
}
