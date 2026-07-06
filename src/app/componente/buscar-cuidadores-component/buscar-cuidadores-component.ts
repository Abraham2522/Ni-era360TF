import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

import { Cuidador } from '../../model/cuidador';
import { Reserva } from '../../model/reserva';
import { CuidadoresService } from '../../services/cuidadores-service';
import { ReservasService } from '../../services/reservas-service';

@Component({
  selector: 'app-buscar-cuidadores-component',
  standalone: true,
  imports: [
    RouterLink,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule
  ],
  templateUrl: './buscar-cuidadores-component.html',
  styleUrl: './buscar-cuidadores-component.css'
})
export class BuscarCuidadoresComponent implements OnInit {

  cuidadores: Cuidador[] = [];

  constructor(
    private cuidadoresService: CuidadoresService,
    private reservasService: ReservasService
  ) {}

  ngOnInit(): void {
    this.listarCuidadores();
  }

  listarCuidadores() {
    this.cuidadoresService.list().subscribe({
      next: data => {
        console.log('CUIDADORES:', data);
        this.cuidadores = data;
      },
      error: err => {
        console.log('ERROR:', err);
      }
    });
  }

  reservar(cuidador: Cuidador) {
    const direccion = prompt('Ingrese la dirección del servicio:');
    if (!direccion || direccion.trim() === '') {
      alert('Debe ingresar una dirección');
      return;
    }

    const fechaInicio = prompt('Ingrese fecha y hora de inicio. Ejemplo: 2026-07-10 08:00');
    if (!fechaInicio || fechaInicio.trim() === '') {
      alert('Debe ingresar fecha y hora de inicio');
      return;
    }

    const fechaFin = prompt('Ingrese fecha y hora de fin. Ejemplo: 2026-07-10 13:00');
    if (!fechaFin || fechaFin.trim() === '') {
      alert('Debe ingresar fecha y hora de fin');
      return;
    }

    const reserva: Reserva = {
      idReserva: 0,
      idCliente: 1,
      idCuidador: cuidador.idCuidador,
      horaInicio: this.convertirFecha(fechaInicio),
      horaFin: this.convertirFecha(fechaFin),
      direccionServicio: direccion,
      estado: 'PENDIENTE'
    };

    this.reservasService.insert(reserva).subscribe({
      next: () => {
        alert('Reserva enviada. Esperando respuesta del cuidador.');
      },
      error: err => {
        console.error('ERROR RESERVA:', err);
        alert('No se pudo registrar la reserva');
      }
    });
  }

  private convertirFecha(fecha: string): string {
    return fecha.replace(' ', 'T') + ':00-05:00';
  }
}
