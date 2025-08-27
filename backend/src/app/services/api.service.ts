// src/app/services/api.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private base = environment.apiBaseUrl; // http://localhost:8080/api

  constructor(private http: HttpClient) { }

  // call your backend controller: GET /api/fmp/profile/{symbol}
  getCompanyProfile(symbol: string): Observable<any> {
    return this.http.get<any>(`${this.base}/fmp/profile/${symbol}`);
  }
}
