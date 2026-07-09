import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-home-cliente-component',
  standalone: true,
  imports: [
    RouterLink,
    MatIconModule,
    MatButtonModule
  ],
  templateUrl: './home-cliente-component.html',
  styleUrl: './home-cliente-component.css',
})
export class HomeClienteComponent {

  constructor(private router: Router) {}

  logout(): void {

    localStorage.clear();
    sessionStorage.clear();

    this.router.navigate(['/login']);
  }

}
