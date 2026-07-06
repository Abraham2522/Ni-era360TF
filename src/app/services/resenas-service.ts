import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Resena {
  idResena: number;
  idReserva: number;
  calificacion: string;
  comentario: string;
  creadoEn?: string;
  activo?: boolean;
}

@Injectable({
  providedIn: 'root',
})
export class ResenasService {
  private url = 'http://localhost:8080/resenas';

  constructor(private http: HttpClient) {}

  list(): Observable<Resena[]> {
    return this.http.get<Resena[]>(`${this.url}/listar`);
  }

  insert(resena: Resena): Observable<Resena> {
    return this.http.post<Resena>(`${this.url}/insertar`, resena);
  }

  update(resena: Resena): Observable<Resena> {
    return this.http.put<Resena>(`${this.url}/editar`, resena);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/eliminar/${id}`);
  }
}
