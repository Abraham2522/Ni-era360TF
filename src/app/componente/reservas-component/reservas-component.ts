import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';

import { Reserva } from '../../model/reserva';
import { ReservasService } from '../../services/reservas-service';

@Component({
  selector: 'app-reservas-component',
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
  templateUrl: './reservas-component.html',
  styleUrls: ['./reservas-component.css']
})
export class ReservasComponent implements OnInit {

  reservas: Reserva[] = [];

  dataSource = new MatTableDataSource<Reserva>();

  columnas: string[] = [
    'idReserva',
    'cuidador',
    'inicio',
    'fin',
    'direccion',
    'estado',
    'eliminar',
    'actualizar'
  ];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private reservasService: ReservasService) {}

  ngOnInit(): void {
    this.listarReservas();
  }

  listarReservas(): void {
    this.reservasService.list().subscribe({
      next: (data) => {
        this.reservas = data;
        this.dataSource.data = data;

        setTimeout(() => {
          this.dataSource.paginator = this.paginator;
        });
      },
      error: (err) => {
        console.error('ERROR RESERVAS:', err);
      }
    });
  }

  eliminarReserva(id: number): void {
    const confirmar = confirm('¿Deseas eliminar esta reserva?');

    if (confirmar) {
      this.reservasService.delete(id).subscribe({
        next: () => {
          alert('Reserva eliminada');
          this.listarReservas();
        },
        error: (err) => {
          console.error(err);
          alert('No se pudo eliminar la reserva');
        }
      });
    }
  }
  actualizarReserva(reserva: Reserva): void {
    const nuevaDireccion = prompt(
      'Nueva dirección del servicio:',
      reserva.direccionServicio
    );

    if (!nuevaDireccion || nuevaDireccion.trim() === '') {
      alert('Debe ingresar una dirección');
      return;
    }

    const reservaActualizada: Reserva = {
      ...reserva,
      direccionServicio: nuevaDireccion
    };

    this.reservasService.update(reservaActualizada).subscribe({
      next: () => {
        alert('Reserva actualizada');
        this.listarReservas();
      },
      error: (err) => {
        console.error('ERROR ACTUALIZAR:', err);
        alert('No se pudo actualizar la reserva');
      }
    });
  }

}
