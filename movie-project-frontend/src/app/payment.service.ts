import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PaymentService {
  private paystackUrl = 'https://api.paystack.co/transaction/initialize'; 
  private secretKey = 'sk_test_f502c5c21ae08ffde8c41dee65e319c080780c3e'; 
  constructor(private http: HttpClient) {}

 
  processPayment(email: string, amount: number): Observable<any> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.secretKey}`, 
      'Content-Type': 'application/json',
    });

    const body = {
      email,
      amount: amount * 100, 
      currency: 'NGN',
    };

    return this.http.post<any>(this.paystackUrl, body, { headers });
  }


  verifyPayment(reference: string): Observable<any> {
    const verifyUrl = `https://api.paystack.co/transaction/verify/${reference}`;
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.secretKey}`, 
    });

    return this.http.get<any>(verifyUrl, { headers });
  }
}
