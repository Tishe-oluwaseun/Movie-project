import { Component } from '@angular/core';
import { Auth, signInWithEmailAndPassword } from '@angular/fire/auth';
import { Router, RouterLink } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../auth.service'; // Import AuthService

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string = ''; 

  constructor(
    private auth: Auth, 
    private router: Router, 
    private fb: FormBuilder,
    private authService: AuthService 
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }
  
  loginWithGoogle() {
    this.authService.signInWithGoogle();
  }
  

  login() {
    const { email, password } = this.loginForm.value;
    this.errorMessage = ''; 

    signInWithEmailAndPassword(this.auth, email, password)
      .then((userCredential) => {
        const user = userCredential.user;

        
        this.authService.setSession(); 

        localStorage.setItem('userToken', JSON.stringify(user));
        
        this.router.navigate(['/photo-library']);
      })
      .catch((error) => {
        this.errorMessage = error.message; 
      });
  }
}
