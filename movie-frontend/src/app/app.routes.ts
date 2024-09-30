import { Routes } from '@angular/router';
// import { LoginComponent } from './login/login.component';
// import { SignupComponent } from './signup/signup.component';


// import { NotFoundComponent } from './not-found/not-found.component';
// import { LandingPageComponent } from './landing-page/landing-page.component';
// import { PaymentComponent } from './payment/payment.component';
import { AdminComponent } from './admin/admin.component';

export const routes: Routes = [
  { path: '', redirectTo: '/admin', pathMatch: 'full' },
//   { path: 'signup', component: SignupComponent },
//   { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminComponent },
//   { path: 'T3movies', component: LandingPageComponent },
//   { path: '**', component: NotFoundComponent },

  
  
]