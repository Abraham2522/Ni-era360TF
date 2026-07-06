export interface Reserva {
  idReserva: number;
  idCliente: number;
  idCuidador: number;
  horaInicio: string;
  horaFin: string;
  direccionServicio: string;
  estado: string;

  cuidador?: {
    idCuidador: number;
    nombre: string;
    apellido: string;
  };
}
