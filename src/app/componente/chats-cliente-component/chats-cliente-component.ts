import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';

import { Chat } from '../../model/chat';
import {ChatsService} from '../../services/chat-service';

@Component({
  selector: 'app-chats-cliente-component',
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
  templateUrl: './chats-cliente-component.html',
  styleUrls: ['./chats-cliente-component.css']
})
export class ChatsClienteComponent implements OnInit {

  chats: Chat[] = [];
  dataSource = new MatTableDataSource<Chat>();

  columnas: string[] = [
    'idChat',
    'idCliente',
    'idCuidador',
    'creadoEn',
    'activo',
    'eliminar',
    'actualizar'
  ];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private chatsService: ChatsService
  ) {}

  ngOnInit(): void {
    this.listarChats();
  }

  listarChats(): void {
    this.chatsService.list().subscribe({
      next: (data) => {

        console.log('CHATS:', data);

        this.chats = data;
        this.dataSource.data = data;

        setTimeout(() => {
          this.dataSource.paginator = this.paginator;
        });

      },
      error: (err) => {
        console.error('ERROR CHATS:', err);
      }
    });
  }

  registrarChat(): void {

    const idCliente = Number(
      prompt('Ingrese ID Cliente:')
    );

    if (!idCliente) {
      alert('ID Cliente inválido');
      return;
    }

    const idCuidador = Number(
      prompt('Ingrese ID Cuidador:')
    );

    if (!idCuidador) {
      alert('ID Cuidador inválido');
      return;
    }

    const nuevoChat: Chat = {
      idChat: 0,
      idCliente: idCliente,
      idCuidador: idCuidador,
      creadoEn: new Date().toISOString(),
      activo: true
    };

    this.chatsService.insert(nuevoChat).subscribe({
      next: () => {
        alert('Chat registrado');
        this.listarChats();
      },
      error: (err) => {
        console.error(err);
        alert('No se pudo registrar');
      }
    });
  }

  actualizarChat(chat: Chat): void {

    const activoTexto = prompt(
      '¿Activo? true o false',
      String(chat.activo)
    );

    if (
      activoTexto !== 'true' &&
      activoTexto !== 'false'
    ) {
      alert('Debe escribir true o false');
      return;
    }

    const actualizado: Chat = {
      ...chat,
      activo: activoTexto === 'true'
    };

    this.chatsService.update(actualizado).subscribe({
      next: () => {
        alert('Chat actualizado');
        this.listarChats();
      },
      error: (err) => {
        console.error(err);
        alert('No se pudo actualizar');
      }
    });
  }

  eliminarChat(id: number): void {

    const confirmar = confirm(
      '¿Deseas eliminar este chat?'
    );

    if (confirmar) {

      this.chatsService.delete(id).subscribe({
        next: () => {
          alert('Chat eliminado');
          this.listarChats();
        },
        error: (err) => {
          console.error(err);
          alert('No se pudo eliminar');
        }
      });

    }
  }
}
