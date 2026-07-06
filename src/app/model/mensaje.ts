export class Mensaje {
  idMensaje: number = 0;
  idChat: number = 0;
  idUsuario: number = 0;
  contenido: string = '';
  fecha: Date = new Date();
  leido: boolean = false;
}
