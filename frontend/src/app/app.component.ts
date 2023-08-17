import { Component, inject, OnInit } from "@angular/core";
import { AuthService } from "./service/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'frontend';

  private http = inject(AuthService)

  ngOnInit(): void {
    this.http.test().subscribe(console.log)
  }
}
