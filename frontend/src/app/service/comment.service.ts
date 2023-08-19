import { inject, Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { API } from "../config/config";

@Injectable({
  providedIn: "root"
})
export class CommentService {

  private http = inject(HttpClient);
  private COMMENT_URL = `${API}/comment`;

  addComment(
    req: any
  ): Observable<any> {
    return this.http
      .post(this.COMMENT_URL, req);
  }

  delete(
    id: number
  ): Observable<any> {
    return this.http
      .delete(`${this.COMMENT_URL}/${id}`);
  }


}
