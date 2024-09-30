import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormArray } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterModule, HttpClientModule],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  movieForm!: FormGroup;
  private httpClient = inject(HttpClient);

  constructor(private formBuilder: FormBuilder, ) {}

  ngOnInit(): void {
    this.movieForm = this.formBuilder.group({
      movieTitle: ['', Validators.required],
      releaseDate: ['', Validators.required],
      movieDescription: ['', Validators.required],
      moviePoster: ['', Validators.required],
      genre: ['', Validators.required],
      directorName: ['', Validators.required],
      directorEmail: ['', [Validators.required, Validators.email]],
      directorPicture: ['', Validators.required],
      castMembers: this.formBuilder.array([
        this.createCastMemberFormGroup()
      ])
    });
  }

  createCastMemberFormGroup(): FormGroup {
    return this.formBuilder.group({
      actorName: ['', Validators.required],
      autobiographyLink: ['', Validators.required],
      picture: ['', Validators.required]
    });
  }

  get castMembers(): FormArray {
    return this.movieForm.get('castMembers') as FormArray;
  }

  addCastMember(): void {
    this.castMembers.push(this.createCastMemberFormGroup());
  }

  removeCastMember(index: number): void {
    this.castMembers.removeAt(index);
  }

  onSubmit(): void {
    if (this.movieForm.valid) {
      console.log(this.movieForm.value);
      
    } else {
      console.log('Form is invalid');
    }
  }
}
