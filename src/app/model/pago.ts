export interface Pago {
  idPago: number;
  idReserva: number;
  montoPago: number;
  estadoPago: boolean;
  pagadaEn: string;
  metodoPago: string;
}
