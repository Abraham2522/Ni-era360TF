import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Pago } from '../model/pago';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class PagosService {

  private url = environment.apiURL;
  private httpClient: HttpClient = inject(HttpClient);

  insert(pago: Pago) {
    return this.httpClient.post(this.url + '/pagos/agregar', pago);
  }

  list() {
    return this.httpClient.get<Pago[]>(this.url + '/pagos/listar');
  }

  delete(id: number) {
    return this.httpClient.delete(this.url + '/pagos/eliminar/' + id);
  }

  update(pago: Pago) {
    return this.httpClient.put(this.url + '/pagos/editar', pago);
  }

  listId(id: number) {
    return this.httpClient.get<Pago>(this.url + '/pagos/buscarpor/' + id);
  }

  count() {
    return this.httpClient.get<number>(this.url + '/pagos/countPagos');
  }

  findByEstadoPago(estado: boolean) {
    return this.httpClient.get<Pago[]>(this.url + '/pagos/estado/' + estado);
  }
}
