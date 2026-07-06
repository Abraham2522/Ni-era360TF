import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Reserva } from '../model/reserva';
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ReservasService {

  private url = environment.apiURL;
  private httpClient: HttpClient = inject(HttpClient);

  constructor() {}

  insert(reserva: Reserva) {
    console.log(reserva);
    return this.httpClient.post(
      this.url + '/reservas/agregar',
      reserva
    );
  }

  list() {
    return this.httpClient.get<Reserva[]>(
      this.url + '/reservas/listar'
    );
  }

  delete(id: number) {
    return this.httpClient.delete(
      this.url + '/reservas/eliminar/' + id
    );
  }

  update(reserva: Reserva) {
    return this.httpClient.put(
      this.url + '/reservas/editar',
      reserva
    );
  }

  listId(id: number) {
    return this.httpClient.get<Reserva>(
      this.url + '/reservas/buscar/' + id
    );
  }

  count() {
    return this.httpClient.get<number>(
      this.url + '/reservas/countReservas'
    );
  }

  findByEstado(estado: string) {
    return this.httpClient.get<Reserva[]>(
      this.url + '/reservas/estado/' + estado
    );
  }
}
