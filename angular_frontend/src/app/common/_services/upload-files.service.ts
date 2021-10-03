import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UploadFilesService {

  ROOT_URL = environment.PROJETFINAL_AL07_JAVA_INTERESTPOINT_API;

  httpHeaders = new HttpHeaders()
  .set('content-type', 'application/json')
  .set('Access-Control-Allow-Origin', '*')

  constructor(private http: HttpClient) { }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    var uri = "interestpoint/upload"
    console.log(uri)
    const req = new HttpRequest('POST', `${this.ROOT_URL}/${uri}`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    var uri = "interestpoint/files"
    return this.http.get(`${this.ROOT_URL}/${uri}`);
  }
}
