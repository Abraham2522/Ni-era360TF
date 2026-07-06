import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { Resena, ResenasService } from '../../services/resenas-service';

@Component({
  selector: 'app-resenas-component',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './resenas-component.html',
  styleUrl: './resenas-component.css',
})
export class ResenasComponent implements AfterViewInit {

  columnas: string[] = [
    'idResena',
    'idReserva',
    'calificacion',
    'comentario',
    'creadoEn',
    'activo',
    'eliminar',
    'actualizar'
  ];

  dataSource = new MatTableDataSource<Resena>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private resenasService: ResenasService) {}

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.listarResenas();
  }

  listarResenas() {
    this.resenasService.list().subscribe({
      next: data => {
        this.dataSource.data = data;
      },
      error: err => {
        console.error('ERROR LISTAR RESEÑAS:', err);
      }
    });
  }

  registrarResena() {
    const idReserva = Number(prompt('Ingrese ID de reserva:'));
    const calificacion = prompt('Ingrese calificación:');
    const comentario = prompt('Ingrese comentario:');

    if (!idReserva || !calificacion || !comentario) {
      alert('Debe completar todos los campos');
      return;
    }

    const resena: Resena = {
      idResena: 0,
      idReserva,
      calificacion,
      comentario,
      activo: true
    };

    this.resenasService.insert(resena).subscribe({
      next: () => {
        alert('Reseña registrada');
        this.listarResenas();
      },
      error: err => {
        console.error('ERROR REGISTRAR RESEÑA:', err);
        alert('No se pudo registrar la reseña');
      }
    });
  }

  eliminarResena(id: number) {
    this.resenasService.delete(id).subscribe({
      next: () => {
        alert('Reseña eliminada');
        this.listarResenas();
      },
      error: err => {
        console.error('ERROR ELIMINAR RESEÑA:', err);
      }
    });
  }

  actualizarResena(resena: Resena) {
    const calificacion = prompt('Nueva calificación:', resena.calificacion);
    const comentario = prompt('Nuevo comentario:', resena.comentario);

    if (!calificacion || !comentario) {
      alert('Debe completar todos los campos');
      return;
    }

    const actualizada: Resena = {
      ...resena,
      calificacion,
      comentario
    };

    this.resenasService.update(actualizada).subscribe({
      next: () => {
        alert('Reseña actualizada');
        this.listarResenas();
      },
      error: err => {
        console.error('ERROR ACTUALIZAR RESEÑA:', err);
      }
    });
  }
}
