import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

import { ReservasService } from '../../services/reservas-service';
import { PagosService } from '../../services/pagos-service';
import { ResenasService } from '../../services/resenas-service';
import { MensajesService } from '../../services/mensajes-service';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-home-cuidador-component',
  standalone: true,
  imports: [
    RouterLink,
    MatIconModule,
    MatButtonModule
  ],
  templateUrl: './home-cuidador-component.html',
  styleUrl: './home-cuidador-component.css',
})
export class HomeCuidadorComponent implements OnInit {

  totalReservas = 0;
  promedioCalificacion = 0;
  totalMensajes = 0;
  totalIngresos = 0;

  constructor(
    private reservasService: ReservasService,
    private pagosService: PagosService,
    private resenasService: ResenasService,
    private mensajesService: MensajesService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarReservas();
    this.cargarPagos();
    this.cargarResenas();
    this.cargarMensajes();
  }

  logout(): void {
    localStorage.clear();
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }

  cargarReservas(): void {
    this.reservasService.list().subscribe({
      next: data => this.totalReservas = data.length,
      error: err => console.error('ERROR RESERVAS:', err)
    });
  }

  cargarPagos(): void {
    this.pagosService.list().subscribe({
      next: data => {
        this.totalIngresos = data.reduce(
          (total, pago) => total + Number(pago.montoPago),
          0
        );
      },
      error: err => console.error('ERROR PAGOS:', err)
    });
  }

  cargarResenas(): void {
    this.resenasService.list().subscribe({
      next: data => {
        if (data.length === 0) {
          this.promedioCalificacion = 0;
          return;
        }

        const suma = data.reduce(
          (total, resena) => total + Number(resena.calificacion),
          0
        );

        this.promedioCalificacion = Number((suma / data.length).toFixed(1));
      },
      error: err => console.error('ERROR RESEÑAS:', err)
    });
  }

  cargarMensajes(): void {
    this.mensajesService.list().subscribe({
      next: data => this.totalMensajes = data.length,
      error: err => console.error('ERROR MENSAJES:', err)
    });
  }
}
