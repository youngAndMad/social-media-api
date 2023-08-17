import { inject, Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { API } from "../config/config";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  http = inject(HttpClient)

  register(
    req: any
  ):Observable<any>{
    return this.http
      .post(`${API}/user/registration`,req)
  }
}
