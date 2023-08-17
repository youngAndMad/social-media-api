import { inject, Injectable } from "@angular/core";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http = inject(HttpClient)

  test():Observable<any>{
    return this.http.get('http://localhost:8090/api/v1/channel/1')
  }
}
