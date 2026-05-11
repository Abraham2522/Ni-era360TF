# Cambios realizados

## Error corregido
Se agregó la implementación faltante `UserProfileServiceImpl` para que Spring pueda inyectar el bean requerido por `com.upc.ninera360.controllers.UserProfileController`.

## Módulo agregado: Reservas
Se agregó todo lo necesario para la tabla `reservas`:

- `entities/Reservas.java`
- `dtos/ReservasDTO.java`
- `repositories/ReservasRepositorio.java`
- `services/ReservasService.java`
- `serviceimpl/ReservasServiceImpl.java`
- `controllers/ReservasController.java`

## Campos implementados
La entidad `Reservas` contiene:

- `id_reserva` como PK autogenerada
- `id_cliente` como relación `ManyToOne` con `clientes`
- `id_cuidador` como relación `ManyToOne` con `cuidadores`
- `hora_inicio` como `TIMESTAMPTZ`
- `hora_fin` como `TIMESTAMPTZ`
- `estado` como `TEXT`

## Endpoints agregados

- `GET /reservas/listar`
- `GET /reservas/obtener/{id}`
- `GET /reservas/cliente/{idCliente}`
- `GET /reservas/cuidador/{idCuidador}`
- `GET /reservas/estado/{estado}`
- `POST /reservas/insertar`
- `PUT /reservas/editar`
- `DELETE /reservas/eliminar/{id}`

## JSON de ejemplo para insertar

```json
{
  "idCliente": 1,
  "idCuidador": 2,
  "horaInicio": "2026-05-11T09:00:00-05:00",
  "horaFin": "2026-05-11T13:00:00-05:00",
  "estado": "pendiente"
}
```

## Nota
Para insertar una reserva, primero deben existir registros en `clientes` y `cuidadores` con los IDs enviados.
