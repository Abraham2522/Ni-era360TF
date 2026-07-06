import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

import { PagosService } from '../../services/pagos-service';
import { ReservasService } from '../../services/reservas-service';
import { Pago } from '../../model/pago';
import { Reserva } from '../../model/reserva';

@Component({
  selector: 'app-pagos-cuidador-component',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    DatePipe,
    MatCardModule,
    MatButtonModule
  ],
  templateUrl: './pagos-cuidador-component.html',
  styleUrl: './pagos-cuidador-component.css',
})
export class PagosCuidadorComponent implements OnInit {

  mostrarPagos = false;

  pagosCuidador: Pago[] = [];
  reservasCuidador: Reserva[] = [];

  totalPagado = 0;
  totalPendiente = 0;

  constructor(
    private pagosService: PagosService,
    private reservasService: ReservasService
  ) {}

  ngOnInit(): void {
    this.listarPagosCuidador();
  }

  listarPagosCuidador(): void {
    const idCuidador = Number(sessionStorage.getItem('idCuidador'));
    console.log('ID CUIDADOR LOGUEADO:', idCuidador);

    this.reservasService.list().subscribe({
      next: (reservas: Reserva[]) => {

        this.reservasCuidador = reservas.filter(
          r => Number(r.idCuidador) === idCuidador
        );

        const idsReservas = this.reservasCuidador.map(
          r => Number(r.idReserva)
        );

        this.pagosService.list().subscribe({
          next: (pagos: Pago[]) => {

            this.pagosCuidador = pagos.filter(
              p => idsReservas.includes(Number(p.idReserva))
            );

            this.totalPagado = this.pagosCuidador
              .filter(p => p.estadoPago)
              .reduce((t, p) => t + Number(p.montoPago), 0);

            this.totalPendiente = this.pagosCuidador
              .filter(p => !p.estadoPago)
              .reduce((t, p) => t + Number(p.montoPago), 0);
          },
          error: err => {
            console.error('ERROR PAGOS CUIDADOR:', err);
          }
        });

      },
      error: err => {
        console.error('ERROR RESERVAS CUIDADOR:', err);
      }
    });
  }

  obtenerNombreCliente(idReserva: number): string {
    const reserva = this.reservasCuidador.find(
      r => Number(r.idReserva) === Number(idReserva)
    );

    if (!reserva) {
      return `Reserva #${idReserva}`;
    }

    return `Cliente #${reserva.idCliente}`;
  }
}
