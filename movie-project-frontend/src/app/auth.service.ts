import { Injectable } from '@angular/core';
import { Auth, signInWithPopup, GoogleAuthProvider, onAuthStateChanged, User } from '@angular/fire/auth';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private countdownInterval: any; 
  private user: User | null = null;

  constructor(
    private auth: Auth,
    private router: Router,
    private snackBar: MatSnackBar,
  ) {
    onAuthStateChanged(this.auth, (user) => {
      this.user = user;
    });
  }

  signInWithGoogle(): Promise<any> {
    const provider = new GoogleAuthProvider();
    return signInWithPopup(this.auth, provider)
      .then(result => {
        this.setSession();
        localStorage.setItem('userToken', JSON.stringify(result.user));
        this.router.navigate(['/dashboard']);
      })
      .catch(error => {
        this.snackBar.open(error.message, 'Close', { duration: 5000 });
      });
  }

  signUp(user: string, email: string, password: string): Observable<any> {
    // Simulate success response instead of making an API call
    console.log(`User: ${user}, Email: ${email}, Password: ${password}`);
    return of({ success: true, message: 'Signup successful!' });
  }

  login(user: string, password: string): Observable<any> {
    // Simulate success response instead of making an API call
    console.log(`User: ${user}, Password: ${password}`);
    return of({ success: true, message: 'Login successful!' });
  }

  setSession(): void {
    const expirationTime = new Date().getTime() + 60 * 60 * 1000; 
    localStorage.setItem('tokenExpiry', expirationTime.toString());
    this.startCountdown();
  }

  startCountdown(): void {
    const tokenExpiry = localStorage.getItem('tokenExpiry');
    if (!tokenExpiry) {
      return; 
    }

    const expirationTime = Number(tokenExpiry);
    const currentTime = new Date().getTime();
    let timeRemaining = expirationTime - currentTime;

    if (this.countdownInterval) {
      clearInterval(this.countdownInterval); 
    }

    this.countdownInterval = setInterval(() => {
      timeRemaining -= 1000; 
      console.log(`Time remaining: ${Math.floor(timeRemaining / 1000)} seconds`);
      localStorage.setItem('timeRemaining', timeRemaining.toString());

      if (timeRemaining <= 0) {
        clearInterval(this.countdownInterval); 
        this.snackBar.open('Your session has expired. Please log in again.', 'Close', {
          duration: 5000,
        });
        this.logout(); 
      }
    }, 1000); 
  }

  logout(): void {
    localStorage.removeItem('userToken');
    localStorage.removeItem('tokenExpiry');
    this.router.navigate(['/login']);
  }
}
