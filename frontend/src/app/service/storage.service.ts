import { inject, Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { API } from "../config/config";
import { FileAddress } from "../domain/enums/FileAddress";

@Injectable({
  providedIn: "root"
})
export class StorageService {

  private http = inject(HttpClient);
  private STORAGE_URL = `${API}/storage`;

  upload(
    file: File,
    fileAddress: FileAddress,
    ownerId: number
  ): Observable<any> {
    const formData = new FormData();

    formData.append("address", fileAddress);
    formData.append("file", file, file.name);
    formData.append("id", `${ownerId}`);

    return this.http
      .post(this.STORAGE_URL, formData);
  }

  download(
    fileName: string
  ): Observable<any> {
    return this
      .http.get(`${this.STORAGE_URL}/download`, {
          params: {
            fileName: fileName
          }
        }
      );
  }

  deleteFile(
    id: number
  ) {
    return this.http
      .delete(`${this.STORAGE_URL}/${id}`);
  }
}
