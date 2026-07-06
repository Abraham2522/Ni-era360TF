import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';

import { Pago } from '../../model/pago';
import { PagosService } from '../../services/pagos-service';

@Component({
  selector: 'app-pagos-component',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    DatePipe,
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule
  ],
  templateUrl: './pagos-component.html',
  styleUrls: ['./pagos-component.css']
})
export class PagosComponent implements OnInit {

  pagos: Pago[] = [];
  dataSource = new MatTableDataSource<Pago>();

  columnas: string[] = [
    'idPago',
    'idReserva',
    'montoPago',
    'metodoPago',
    'estadoPago',
    'pagadaEn',
    'eliminar',
    'actualizar'
  ];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private pagosService: PagosService) {}

  ngOnInit(): void {
    this.listarPagos();
  }

  listarPagos(): void {
    this.pagosService.list().subscribe({
      next: (data) => {
        this.pagos = data;
        this.dataSource.data = data;

        setTimeout(() => {
          this.dataSource.paginator = this.paginator;
        });
      },
      error: (err) => {
        console.error('ERROR PAGOS:', err);
      }
    });
  }

  registrarPago(): void {
    const idReserva = Number(prompt('Ingrese ID de reserva:'));
    if (!idReserva) {
      alert('Debe ingresar un ID de reserva válido');
      return;
    }

    const montoPago = Number(prompt('Ingrese monto de pago:'));
    if (!montoPago) {
      alert('Debe ingresar un monto válido');
      return;
    }

    const metodoPago = prompt('Ingrese método de pago: Yape, Plin, Tarjeta, Efectivo');
    if (!metodoPago || metodoPago.trim() === '') {
      alert('Debe ingresar un método de pago');
      return;
    }

    const estadoTexto = prompt('¿Está pagado? Escriba true o false:', 'true');
    if (estadoTexto !== 'true' && estadoTexto !== 'false') {
      alert('Debe escribir true o false');
      return;
    }

    const pago: Pago = {
      idPago: 0,
      idReserva: idReserva,
      montoPago: montoPago,
      estadoPago: estadoTexto === 'true',
      pagadaEn: new Date().toISOString(),
      metodoPago: metodoPago
    };

    this.pagosService.insert(pago).subscribe({
      next: () => {
        alert('Pago registrado');
        this.listarPagos();
      },
      error: (err) => {
        console.error(err);
        alert('No se pudo registrar el pago');
      }
    });
  }

  actualizarPago(pago: Pago): void {
    const nuevoMonto = Number(prompt('Nuevo monto:', String(pago.montoPago)));
    if (!nuevoMonto) {
      alert('Debe ingresar un monto válido');
      return;
    }

    const nuevoMetodo = prompt('Nuevo método de pago:', pago.metodoPago);
    if (!nuevoMetodo || nuevoMetodo.trim() === '') {
      alert('Debe ingresar un método de pago');
      return;
    }

    const estadoTexto = prompt('¿Está pagado? true o false:', String(pago.estadoPago));
    if (estadoTexto !== 'true' && estadoTexto !== 'false') {
      alert('Debe escribir true o false');
      return;
    }

    const pagoActualizado: Pago = {
      ...pago,
      montoPago: nuevoMonto,
      metodoPago: nuevoMetodo,
      estadoPago: estadoTexto === 'true'
    };

    this.pagosService.update(pagoActualizado).subscribe({
      next: () => {
        alert('Pago actualizado');
        this.listarPagos();
      },
      error: (err) => {
        console.error(err);
        alert('No se pudo actualizar el pago');
      }
    });
  }

  eliminarPago(id: number): void {
    const confirmar = confirm('¿Deseas eliminar este pago?');

    if (confirmar) {
      this.pagosService.delete(id).subscribe({
        next: () => {
          alert('Pago eliminado');
          this.listarPagos();
        },
        error: (err) => {
          console.error(err);
          alert('No se pudo eliminar el pago');
        }
      });
    }
  }
}
