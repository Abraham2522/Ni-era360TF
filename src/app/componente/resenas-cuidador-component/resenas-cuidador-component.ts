import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

import { Resena, ResenasService } from '../../services/resenas-service';
import { ReservasService } from '../../services/reservas-service';
import { Reserva } from '../../model/reserva';

@Component({
  selector: 'app-resenas-cuidador-component',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    DatePipe,
    MatCardModule,
    MatButtonModule
  ],
  templateUrl: './resenas-cuidador-component.html',
  styleUrl: './resenas-cuidador-component.css',
})
export class ResenasCuidadorComponent implements OnInit {

  mostrarResenas = false;

  resenasCuidador: Resena[] = [];
  reservasCuidador: Reserva[] = [];

  promedioGeneral = 0;
  totalResenas = 0;
  familiasSatisfechas = 0;

  constructor(
    private resenasService: ResenasService,
    private reservasService: ReservasService
  ) {}

  ngOnInit(): void {
    this.listarResenasCuidador();
  }

  listarResenasCuidador(): void {
    const idCuidador = Number(sessionStorage.getItem('idCuidador'));

    this.reservasService.list().subscribe({
      next: (reservas: Reserva[]) => {

        this.reservasCuidador = reservas.filter(
          r => Number(r.idCuidador) === idCuidador
        );

        const idsReservas = this.reservasCuidador.map(
          r => Number(r.idReserva)
        );

        console.log('ID CUIDADOR:', idCuidador);
        console.log('RESERVAS CUIDADOR:', this.reservasCuidador);
        console.log('IDS RESERVAS:', idsReservas);

        this.resenasService.list().subscribe({
          next: (resenas: Resena[]) => {

            console.log('TODAS LAS RESEÑAS:', resenas);
            console.log('RESEÑAS DETALLE:', resenas.map(r => ({
              idResena: r.idResena,
              idReserva: r.idReserva,
              calificacion: r.calificacion
            })));

            this.resenasCuidador = resenas.filter(
              r => idsReservas.includes(Number(r.idReserva))
            );

            console.log('RESEÑAS CUIDADOR:', this.resenasCuidador);

            this.totalResenas = this.resenasCuidador.length;

            if (this.totalResenas === 0) {
              this.promedioGeneral = 0;
              this.familiasSatisfechas = 0;
              return;
            }

            const suma = this.resenasCuidador.reduce(
              (total, r) => total + Number(r.calificacion),
              0
            );

            this.promedioGeneral = Number((suma / this.totalResenas).toFixed(1));

            const satisfechas = this.resenasCuidador.filter(
              r => Number(r.calificacion) >= 4
            ).length;

            this.familiasSatisfechas = Math.round(
              (satisfechas / this.totalResenas) * 100
            );
          },
          error: err => {
            console.error('ERROR RESEÑAS CUIDADOR:', err);
          }
        });
      },
      error: err => {
        console.error('ERROR RESERVAS CUIDADOR:', err);
      }
    });
  }

  obtenerEstrellas(calificacion: string): string {
    return '⭐'.repeat(Number(calificacion));
  }

  obtenerNombreCliente(idReserva: number): string {
    const reserva = this.reservasCuidador.find(
      r => Number(r.idReserva) === Number(idReserva)
    );

    if (!reserva) {
      return 'Cliente';
    }

    return 'Cliente #' + reserva.idCliente;
  }

  esBuenaResena(calificacion: string): boolean {
    return Number(calificacion) >= 4;
  }
}
