import { inject, Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { API } from "../config/config";

@Injectable({
  providedIn: "root"
})
export class UserService {

  private http = inject(HttpClient);
  private USER_URL = `${API}/user`;

  register(
    req: any
  ): Observable<any> {
    return this.http
      .post(`${this.USER_URL}/registration`, req);
  }

  deleteAccount(
    id: number
  ): Observable<any> {
    return this.http
      .delete(`${this.USER_URL}/${id}`);
  }

  friendRequest(
    req: any
  ): Observable<any> {
    return this.http
      .post(`${this.USER_URL}/friend/action`, req);
  }

  update(
    req: any,
    id: number
  ): Observable<any> {
    return this.http
      .put(`${this.USER_URL}/${id}`, req);
  }
}
