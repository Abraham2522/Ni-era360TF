import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Mensaje } from '../model/mensaje';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class MensajesService {

  private url = environment.apiURL;
  private httpClient: HttpClient = inject(HttpClient);

  insert(mensaje: Mensaje) {
    return this.httpClient.post(
      this.url + '/mensajes/insertar',
      mensaje
    );
  }

  list() {
    return this.httpClient.get<Mensaje[]>(
      this.url + '/mensajes/listar'
    );
  }

  delete(id: number) {
    return this.httpClient.delete(
      this.url + '/mensajes/eliminar/' + id
    );
  }

  update(mensaje: Mensaje) {
    return this.httpClient.put(
      this.url + '/mensajes/editar',
      mensaje
    );
  }

  listId(id: number) {
    return this.httpClient.get<Mensaje>(
      this.url + '/mensajes/buscarpor/' + id
    );
  }

  count() {
    return this.httpClient.get<number>(
      this.url + '/mensajes/countMensajes'
    );
  }

  findByContenido(contenido: string) {
    return this.httpClient.get<Mensaje[]>(
      this.url + '/mensajes/contenido/' + contenido
    );
  }
}
