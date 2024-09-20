import { Injectable } from '@angular/core';
import { Auth, authState, signOut, signInWithPopup, GoogleAuthProvider } from '@angular/fire/auth';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private countdownInterval: any;  

  constructor(
    private auth: Auth,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  authState(): Observable<any> {
    return authState(this.auth);
  }

  isSessionValid(): boolean {
    this.setSession(); 
    const tokenExpiry = localStorage.getItem('tokenExpiry');
    const currentTime = new Date().getTime();

    if (tokenExpiry && currentTime >= Number(tokenExpiry)) {
      this.snackBar.open('Session has expired. Please log in again.', 'Close', {
        duration: 5000,
      });
      return false; 
    }
    return true; 
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
    localStorage.removeItem('timeRemaining');
    signOut(this.auth).then(() => {
      this.router.navigate(['/login']); 
    });
  }

  
  signInWithGoogle(): Promise<any> {
    const provider = new GoogleAuthProvider();
    return signInWithPopup(this.auth, provider)
      .then(result => {
        this.setSession();
        localStorage.setItem('userToken', JSON.stringify(result.user));
        this.router.navigate(['/login']);
      })
      .catch(error => {
        this.snackBar.open(error.message, 'Close', { duration: 5000 });
      });
  }
}
