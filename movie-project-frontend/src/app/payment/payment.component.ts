import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PaymentService } from '../payment.service'; // Import your payment service
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent implements OnInit {
  paymentForm: FormGroup;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private paymentService: PaymentService,
    private snackBar: MatSnackBar
  ) {
    this.paymentForm = this.fb.group({
      amount: ['', [Validators.required, Validators.min(1)]],
      password: ['', Validators.required],
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (this.paymentForm.valid) {
      const { amount, password } = this.paymentForm.value;

     
      this.paymentService.processPayment(amount, password).subscribe(
        (response) => {
          
          console.log('Payment successful:', response);
          this.snackBar.open('Payment successful!', 'Close', {
            duration: 5000,
          });
        
          this.paymentForm.reset();
        },
        (error) => {
         
          console.error('Payment error:', error);
          this.snackBar.open('Payment failed. Please try again.', 'Close', {
            duration: 5000,
          });
        }
      );
    } else {
      this.errorMessage = 'Please fill out the form correctly.';
    }
  }
}
