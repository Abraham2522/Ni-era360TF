import { Routes } from '@angular/router';

import { LoginComponent } from './componente/login-component/login-component';
import { SeleccionarRolComponent } from './componente/seleccionar-rol-component/seleccionar-rol-component';
import { RegistroClienteComponent } from './componente/registro-cliente-component/registro-cliente-component';
import { RegistroCuidadorComponent } from './componente/registro-cuidador-component/registro-cuidador-component';
import {HomeComponent} from './componente/home-component/home-component';
import {HomeCuidadorComponent} from './componente/home-cuidador-component/home-cuidador-component';
import {HomeClienteComponent} from './componente/home-cliente-component/home-cliente-component';
import {Index} from './componente/index';
import { BuscarCuidadoresComponent } from './componente/buscar-cuidadores-component/buscar-cuidadores-component';
import { MensajesComponent } from './componente/mensajes-component/mensajes-component';
import { PagosComponent } from './componente/pagos-component/pagos-component';
import { ResenasComponent } from './componente/resenas-component/resenas-component';
import {ReservasComponent} from './componente/reservas-component/reservas-component';
import {PerfilComponent} from './componente/perfil-component/perfil-component';
import { ReservasCuidadorComponent } from './componente/reservas-cuidador-component/reservas-cuidador-component';
import { MensajesCuidadorComponent } from './componente/mensajes-cuidador-component/mensajes-cuidador-component';
import { PagosCuidadorComponent } from './componente/pagos-cuidador-component/pagos-cuidador-component';
import { ResenasCuidadorComponent } from './componente/resenas-cuidador-component/resenas-cuidador-component';
import { DisponibilidadComponent } from './componente/disponibilidad-component/disponibilidad-component';
import { PerfilCuidadorComponent } from './componente/perfil-cuidador-component/perfil-cuidador-component';
import { ReportesClienteComponent } from './componente/reportes-cliente-component/reportes-cliente-component';
import { ReportesCuidadorComponent } from './componente/reportes-cuidador-component/reportes-cuidador-component';
import {authGuard} from './guards/auth-guard';
import {ChatsCuidadorComponent} from './componente/chats-cuidador-component/chats-cuidador-component';

export const routes: Routes = [
  { path: '', component: Index },

  { path: 'login', component: LoginComponent },
  { path: 'seleccionar-rol', component: SeleccionarRolComponent },

  { path: 'registro-cliente', component: RegistroClienteComponent },
  { path: 'registro-cuidador', component: RegistroCuidadorComponent },

  { path: 'home', component: HomeComponent, canActivate: [authGuard] },
  { path: 'home-cliente', component: HomeClienteComponent, canActivate: [authGuard] },
  { path: 'home-cuidador', component: HomeCuidadorComponent, canActivate: [authGuard] },

  { path: 'buscar-cuidadores-component', component: BuscarCuidadoresComponent, canActivate: [authGuard] },
  { path: 'mis-reservas-component', component: ReservasComponent, canActivate: [authGuard] },
  { path: 'mensajes-component', component: MensajesComponent, canActivate: [authGuard] },
  { path: 'pagos-component', component: PagosComponent, canActivate: [authGuard] },
  { path: 'resenas-component', component: ResenasComponent, canActivate: [authGuard] },
  { path: 'mi-perfil-component', component: PerfilComponent, canActivate: [authGuard] },

  { path: 'reservas-cuidador-component', component: ReservasCuidadorComponent, canActivate: [authGuard] },
  { path: 'mensajes-cuidador-component', component: MensajesCuidadorComponent, canActivate: [authGuard] },
  { path: 'pagos-cuidador-component', component: PagosCuidadorComponent, canActivate: [authGuard] },
  { path: 'resenas-cuidador-component', component: ResenasCuidadorComponent, canActivate: [authGuard] },
  { path: 'disponibilidad-component', component: DisponibilidadComponent, canActivate: [authGuard] },
  { path: 'perfil-cuidador-component', component: PerfilCuidadorComponent, canActivate: [authGuard] },

  {
    path: 'reportes-cliente-component',
    component: ReportesClienteComponent,
    canActivate: [authGuard]
  },
  {
    path: 'reportes-cuidador-component',
    component: ReportesCuidadorComponent,
    canActivate: [authGuard]
  },

  {
    path: 'chats-cliente-component',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./componente/chats-cliente-component/chats-cliente-component')
        .then(m => m.ChatsClienteComponent)
  },
  {
    path: 'chats-cuidador-component',
    component: ChatsCuidadorComponent,
    canActivate: [authGuard]
  },

  { path: '**', redirectTo: '' }
];
