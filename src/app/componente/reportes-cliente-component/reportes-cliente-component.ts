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
  selector: 'app-reportes-cliente-component',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './reportes-cliente-component.html',
  styleUrl: './reportes-cliente-component.css',
})
export class ReportesClienteComponent implements OnInit {

  reservasCliente: Reserva[] = [];
  pagosCliente: Pago[] = [];
  resenasCliente: Resena[] = [];

  totalReservas = 0;
  totalPagos = 0;
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
        this.reservasCliente = reservas;

        this.totalReservas = this.reservasCliente.length;

        const idsReservas = this.reservasCliente.map(
          r => Number(r.idReserva)
        );

        this.pagosService.list().subscribe({
          next: pagos => {
            this.pagosCliente = pagos.filter(
              p => idsReservas.includes(Number(p.idReserva))
            );

            this.totalPagos = this.pagosCliente.length;

            this.resenasService.list().subscribe({
              next: resenas => {
                this.resenasCliente = resenas.filter(
                  r => idsReservas.includes(Number(r.idReserva))
                );

                this.totalResenas = this.resenasCliente.length;

                setTimeout(() => {
                  this.graficoReservasPorEstado();
                  this.graficoPagosPorEstado();
                  this.graficoResenasPorCalificacion();
                }, 100);
              },
              error: err => console.error('ERROR RESEÑAS:', err)
            });
          },
          error: err => console.error('ERROR PAGOS:', err)
        });
      },
      error: err => console.error('ERROR RESERVAS:', err)
    });
  }

  graficoReservasPorEstado(): void {
    const pendientes = this.reservasCliente.filter(r => r.estado === 'PENDIENTE').length;
    const confirmadas = this.reservasCliente.filter(r => r.estado === 'CONFIRMADA').length;
    const finalizadas = this.reservasCliente.filter(r => r.estado === 'FINALIZADA').length;
    const canceladas = this.reservasCliente.filter(r => r.estado === 'CANCELADA').length;

    new Chart('graficoReservasCliente', {
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

  graficoPagosPorEstado(): void {
    const pagados = this.pagosCliente.filter(p => p.estadoPago).length;
    const pendientes = this.pagosCliente.filter(p => !p.estadoPago).length;

    new Chart('graficoPagosCliente', {
      type: 'pie',
      data: {
        labels: ['Pagados', 'Pendientes'],
        datasets: [{
          data: [pagados, pendientes],
          backgroundColor: ['#0097b2', '#ffc107']
        }]
      },
      options: {
        responsive: true
      }
    });
  }

  graficoResenasPorCalificacion(): void {
    const una = this.resenasCliente.filter(r => Number(r.calificacion) === 1).length;
    const dos = this.resenasCliente.filter(r => Number(r.calificacion) === 2).length;
    const tres = this.resenasCliente.filter(r => Number(r.calificacion) === 3).length;
    const cuatro = this.resenasCliente.filter(r => Number(r.calificacion) === 4).length;
    const cinco = this.resenasCliente.filter(r => Number(r.calificacion) === 5).length;

    new Chart('graficoResenasCliente', {
      type: 'bar',
      data: {
        labels: ['1 estrella', '2 estrellas', '3 estrellas', '4 estrellas', '5 estrellas'],
        datasets: [{
          label: 'Reseñas',
          data: [una, dos, tres, cuatro, cinco],
          backgroundColor: '#0097b2'
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
}
