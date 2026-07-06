import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Chat } from '../model/chat';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ChatsService {

  private url = environment.apiURL;
  private httpClient: HttpClient = inject(HttpClient);

  insert(chat: Chat) {
    return this.httpClient.post(
      this.url + '/chats/insertar',
      chat
    );
  }

  list() {
    return this.httpClient.get<Chat[]>(
      this.url + '/chats/listar'
    );
  }

  delete(id: number) {
    return this.httpClient.delete(
      this.url + '/chats/eliminar/' + id
    );
  }

  update(chat: Chat) {
    return this.httpClient.put(
      this.url + '/chats/editar',
      chat
    );
  }

  listId(id: number) {
    return this.httpClient.get<Chat>(
      this.url + '/chats/buscarpor/' + id
    );
  }

  count() {
    return this.httpClient.get<number>(
      this.url + '/chats/countChats'
    );
  }
}
