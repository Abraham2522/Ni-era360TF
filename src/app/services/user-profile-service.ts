import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { UserProfile } from '../model/user-profile';

@Injectable({
  providedIn: 'root',
})
export class UserProfileService {

  private url = environment.apiURL;
  private httpClient: HttpClient = inject(HttpClient);

  list() {
    return this.httpClient.get<UserProfile[]>(this.url + '/usuarios/listar');
  }

  insert(usuario: UserProfile) {
    return this.httpClient.post(this.url + '/usuarios/insertar', usuario);
  }

  update(usuario: UserProfile) {
    return this.httpClient.put(this.url + '/usuarios/actualizar', usuario);
  }

  delete(id: number) {
    return this.httpClient.delete(this.url + '/usuarios/eliminar/' + id);
  }

  listId(id: number) {
    return this.httpClient.get<UserProfile>(this.url + '/usuarios/buscarpor/' + id);
  }

  count() {
    return this.httpClient.get<number>(this.url + '/usuarios/countUsuarios');
  }

  findByNombre(nombre: string) {
    return this.httpClient.get<UserProfile[]>(this.url + '/usuarios/nombre/' + nombre);
  }
}
