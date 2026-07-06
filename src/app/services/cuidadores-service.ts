import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cuidador } from '../model/cuidador';
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CuidadoresService {

  private url = environment.apiURL;
  private httpClient: HttpClient = inject(HttpClient);

  constructor() {}

  insert(cuidador: Cuidador) {
    return this.httpClient.post(
      this.url + '/cuidadores/insertar',
      cuidador
    );
  }

  list() {
    return this.httpClient.get<Cuidador[]>(
      this.url + '/cuidadores/listar'
    );
  }

  delete(id: number) {
    return this.httpClient.delete(
      this.url + '/cuidadores/eliminar/' + id
    );
  }

  update(cuidador: Cuidador) {
    return this.httpClient.put(
      this.url + '/cuidadores/editar',
      cuidador
    );
  }

  listId(id: number) {
    return this.httpClient.get<Cuidador>(
      this.url + '/cuidadores/buscarpor/' + id
    );
  }

  count() {
    return this.httpClient.get<number>(
      this.url + '/cuidadores/countCuidadores'
    );
  }

  findByDescripcion(descripcion: string) {
    return this.httpClient.get<Cuidador[]>(
      this.url + '/cuidadores/descripcion/' + descripcion
    );
  }
}
