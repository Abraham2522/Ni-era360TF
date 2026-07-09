import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';

import { Mensaje } from '../../model/mensaje';
import { MensajesService } from '../../services/mensajes-service';

@Component({
  selector: 'app-mensajes-cuidador-component',
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
  templateUrl: './mensajes-cuidador-component.html',
  styleUrls: ['./mensajes-cuidador-component.css']
})
export class MensajesCuidadorComponent implements OnInit {

  mensajes: Mensaje[] = [];
  dataSource = new MatTableDataSource<Mensaje>();

  columnas: string[] = [
    'idMensaje',
    'idChat',
    'idUsuario',
    'contenido',
    'fecha',
    'leido',
    'eliminar',
    'actualizar'
  ];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private mensajesService: MensajesService) {}

  ngOnInit(): void {
    this.listarMensajes();
  }

  listarMensajes(): void {
    this.mensajesService.list().subscribe({
      next: (data) => {
        this.mensajes = data;
        this.dataSource.data = data;

        setTimeout(() => {
          this.dataSource.paginator = this.paginator;
        });
      },
      error: (err) => {
        console.error('ERROR MENSAJES:', err);
      }
    });
  }

  registrarMensaje(): void {
    const idChatInput = prompt('Ingrese el ID del chat:');
    const idUsuarioInput = prompt('Ingrese el ID del usuario:');
    const contenido = prompt('Ingrese el contenido del mensaje:');

    if (!idChatInput || !idUsuarioInput || !contenido || contenido.trim() === '') {
      alert('Debe ingresar chat, usuario y contenido');
      return;
    }

    const mensaje: Mensaje = {
      idMensaje: 0,
      idChat: Number(idChatInput),
      idUsuario: Number(idUsuarioInput),
      contenido: contenido,
      fecha: new Date(),
      leido: false
    };

    this.mensajesService.insert(mensaje).subscribe({
      next: () => {
        alert('Mensaje registrado');
        this.listarMensajes();
      },
      error: (err) => {
        console.error(err);
        alert('No se pudo registrar el mensaje');
      }
    });
  }

  actualizarMensaje(mensaje: Mensaje): void {
    const nuevoContenido = prompt(
      'Nuevo contenido del mensaje:',
      mensaje.contenido
    );

    if (!nuevoContenido || nuevoContenido.trim() === '') {
      alert('Debe ingresar un contenido');
      return;
    }

    const mensajeActualizado: Mensaje = {
      ...mensaje,
      contenido: nuevoContenido
    };

    this.mensajesService.update(mensajeActualizado).subscribe({
      next: () => {
        alert('Mensaje actualizado');
        this.listarMensajes();
      },
      error: (err) => {
        console.error(err);
        alert('No se pudo actualizar el mensaje');
      }
    });
  }

  eliminarMensaje(id: number): void {
    const confirmar = confirm('¿Deseas eliminar este mensaje?');

    if (confirmar) {
      this.mensajesService.delete(id).subscribe({
        next: () => {
          alert('Mensaje eliminado');
          this.listarMensajes();
        },
        error: (err) => {
          console.error(err);
          alert('No se pudo eliminar el mensaje');
        }
      });
    }
  }
}
