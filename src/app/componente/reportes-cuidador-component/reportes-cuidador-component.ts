import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import {
  Chart,
  BarController,
  BarElement,
  PieController,
  ArcElement,
  CategoryScale,
  LinearScale,
  Tooltip,
  Legend
} from 'chart.js';

import { ReservasService } from '../../services/reservas-service';
import { PagosService } from '../../services/pagos-service';
import { ResenasService, Resena } from '../../services/resenas-service';

import { Reserva } from '../../model/reserva';
import { Pago } from '../../model/pago';

Chart.register(
  BarController,
  BarElement,
  PieController,
  ArcElement,
  CategoryScale,
  LinearScale,
  Tooltip,
  Legend
);

@Component({
  selector: 'app-reportes-cuidador-component',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './reportes-cuidador-component.html',
  styleUrl: './reportes-cuidador-component.css',
})
export class ReportesCuidadorComponent implements OnInit {

  idCuidador = Number(sessionStorage.getItem('idCuidador'));

  reservasCuidador: Reserva[] = [];
  pagosCuidador: Pago[] = [];
  resenasCuidador: Resena[] = [];

  totalReservas = 0;
  totalIngresos = 0;
  totalResenas = 0;

  constructor(
    private reservasService: ReservasService,
    private pagosService: PagosService,
    private resenasService: ResenasService
  ) {}

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos(): void {
    this.reservasService.list().subscribe({
      next: reservas => {
        this.reservasCuidador = reservas.filter(
          r => Number(r.idCuidador) === this.idCuidador
        );

        this.totalReservas = this.reservasCuidador.length;

        const idsReservas = this.reservasCuidador.map(
          r => Number(r.idReserva)
        );

        this.pagosService.list().subscribe({
          next: pagos => {
            this.pagosCuidador = pagos.filter(
              p => idsReservas.includes(Number(p.idReserva))
            );

            this.totalIngresos = this.pagosCuidador
              .filter(p => p.estadoPago)
              .reduce((total, p) => total + Number(p.montoPago), 0);

            this.resenasService.list().subscribe({
              next: resenas => {
                this.resenasCuidador = resenas.filter(
                  r => idsReservas.includes(Number(r.idReserva))
                );

                this.totalResenas = this.resenasCuidador.length;

                setTimeout(() => {
                  this.graficoReservasPorEstado();
                  this.graficoIngresosPorPago();
                  this.graficoResenasPorCalificacion();
                }, 100);
              }
            });
          }
        });
      }
    });
  }

  graficoReservasPorEstado(): void {
    const pendientes = this.reservasCuidador.filter(r => r.estado === 'PENDIENTE').length;
    const confirmadas = this.reservasCuidador.filter(r => r.estado === 'CONFIRMADA').length;
    const finalizadas = this.reservasCuidador.filter(r => r.estado === 'FINALIZADA').length;
    const canceladas = this.reservasCuidador.filter(r => r.estado === 'CANCELADA').length;

    new Chart('graficoReservasCuidador', {
      type: 'bar',
      data: {
        labels: ['Pendientes', 'Confirmadas', 'Finalizadas', 'Canceladas'],
        datasets: [{
          label: 'Reservas',
          data: [pendientes, confirmadas, finalizadas, canceladas],
          backgroundColor: ['#ffc107', '#0097b2', '#28a745', '#dc3545']
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: false
          }
        }
      }
    });
  }

  graficoIngresosPorPago(): void {
    new Chart('graficoIngresosCuidador', {
      type: 'bar',
      data: {
        labels: this.pagosCuidador.map(p => 'Pago #' + p.idPago),
        datasets: [{
          label: 'Monto recibido S/',
          data: this.pagosCuidador.map(p => Number(p.montoPago)),
          backgroundColor: '#0097b2'
        }]
      },
      options: {
        responsive: true
      }
    });
  }

  graficoResenasPorCalificacion(): void {
    const una = this.resenasCuidador.filter(r => Number(r.calificacion) === 1).length;
    const dos = this.resenasCuidador.filter(r => Number(r.calificacion) === 2).length;
    const tres = this.resenasCuidador.filter(r => Number(r.calificacion) === 3).length;
    const cuatro = this.resenasCuidador.filter(r => Number(r.calificacion) === 4).length;
    const cinco = this.resenasCuidador.filter(r => Number(r.calificacion) === 5).length;

    new Chart('graficoResenasCuidador', {
      type: 'pie',
      data: {
        labels: ['1 estrella', '2 estrellas', '3 estrellas', '4 estrellas', '5 estrellas'],
        datasets: [{
          data: [una, dos, tres, cuatro, cinco],
          backgroundColor: ['#dc3545', '#ff9800', '#ffc107', '#8bc34a', '#0097b2']
        }]
      },
      options: {
        responsive: true
      }
    });
  }
}
