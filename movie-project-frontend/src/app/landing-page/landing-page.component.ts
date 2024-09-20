import { Component } from '@angular/core';
import { WatchListComponent } from '../watch-list/watch-list.component';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [ WatchListComponent, CommonModule, RouterLink],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent {
  menuVisible = false;
  moviesVisible = false;
  celebsVisible = false;

  toggleMenu() {
    this.menuVisible = !this.menuVisible;
  }

  toggleMovies() {
    this.moviesVisible = !this.moviesVisible;
    if (this.moviesVisible) {
      this.celebsVisible = false;
    }
  }

  toggleCelebs() {
    this.celebsVisible = !this.celebsVisible;
    if (this.celebsVisible) {
      this.moviesVisible = false;
    }
  }
  

}
