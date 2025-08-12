import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from './services/api.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  companyProfile: any;
  symbol: string = 'AAPL';

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getCompanyProfile(this.symbol).subscribe({
      next: (data) => {
        this.companyProfile = data?.[0] ?? null;
        console.log('Company Profile:', this.companyProfile);
      },
      error: (err) => console.error('Error fetching company profile', err)
    });
  }
}
