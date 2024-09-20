import { Component, OnInit } from '@angular/core';
import { Auth, createUserWithEmailAndPassword } from '@angular/fire/auth';
import { Router, RouterLink, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../auth.service';  // Import the AuthService

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink, RouterModule],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignUpComponent implements OnInit {
  signupForm: FormGroup;
  errorMessage: string = ''; 

  constructor(private auth: Auth, private router: Router, private fb: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  signUp() {
    const { email, password } = this.signupForm.value;
    this.errorMessage = ''; 

    createUserWithEmailAndPassword(this.auth, email, password)
      .then(() => this.router.navigate(['/login']))
      .catch((error) => {
        this.errorMessage = error.message; 
      });
  }

  signUpWithGoogle() {
    this.authService.signInWithGoogle();
  }
}
