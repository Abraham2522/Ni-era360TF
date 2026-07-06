import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

import { ReservasService } from '../../services/reservas-service';
import { Reserva } from '../../model/reserva';

@Component({
  selector: 'app-reservas-cuidador-component',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    DatePipe,
    MatCardModule,
    MatButtonModule
  ],
  templateUrl: './reservas-cuidador-component.html',
  styleUrl: './reservas-cuidador-component.css',
})
export class ReservasCuidadorComponent implements OnInit {

  mostrarReservas = false;

  reservasCuidador: Reserva[] = [];

  totalPendientes = 0;
  totalConfirmadas = 0;
  totalFinalizadas = 0;

  constructor(private reservasService: ReservasService) {}

  ngOnInit(): void {
    this.listarReservasCuidador();
  }

  listarReservasCuidador(): void {
    const idCuidador = Number(sessionStorage.getItem('idCuidador'));

    this.reservasService.list().subscribe({
      next: (reservas: Reserva[]) => {
        this.reservasCuidador = reservas.filter(
          r => Number(r.idCuidador) === idCuidador
        );

        this.totalPendientes = this.reservasCuidador
          .filter(r => r.estado?.toUpperCase() === 'PENDIENTE')
          .length;

        this.totalConfirmadas = this.reservasCuidador
          .filter(r => r.estado?.toUpperCase() === 'CONFIRMADA')
          .length;

        this.totalFinalizadas = this.reservasCuidador
          .filter(r => r.estado?.toUpperCase() === 'FINALIZADA')
          .length;
      },
      error: err => {
        console.error('ERROR RESERVAS CUIDADOR:', err);
      }
    });
  }

  aceptarReserva(reserva: Reserva): void {
    const actualizada: Reserva = {
      ...reserva,
      estado: 'CONFIRMADA'
    };

    this.reservasService.update(actualizada).subscribe({
      next: () => {
        alert('Reserva aceptada');
        this.listarReservasCuidador();
      },
      error: err => {
        console.error('ERROR ACEPTAR RESERVA:', err);
      }
    });
  }

  rechazarReserva(reserva: Reserva): void {
    const actualizada: Reserva = {
      ...reserva,
      estado: 'RECHAZADA'
    };

    this.reservasService.update(actualizada).subscribe({
      next: () => {
        alert('Reserva rechazada');
        this.listarReservasCuidador();
      },
      error: err => {
        console.error('ERROR RECHAZAR RESERVA:', err);
      }
    });
  }

  cancelarReserva(reserva: Reserva): void {
    const actualizada: Reserva = {
      ...reserva,
      estado: 'CANCELADA'
    };

    this.reservasService.update(actualizada).subscribe({
      next: () => {
        alert('Reserva cancelada');
        this.listarReservasCuidador();
      },
      error: err => {
        console.error('ERROR CANCELAR RESERVA:', err);
      }
    });
  }

  verDetalle(reserva: Reserva): void {
    alert(
      `Reserva #${reserva.idReserva}\n` +
      `Cliente: ${reserva.idCliente}\n` +
      `Inicio: ${reserva.horaInicio}\n` +
      `Fin: ${reserva.horaFin}\n` +
      `Dirección: ${reserva.direccionServicio}\n` +
      `Estado: ${reserva.estado}`
    );
  }
}
