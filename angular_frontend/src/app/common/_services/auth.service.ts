import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const ROOT_URL = environment.PROJETFINAL_AL07_JAVA_USER_API;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    var uri = "login/signin";
    return this.http.post(ROOT_URL+`/${uri}`, {
      email,
      password
    }, httpOptions);
  }

  // register(email: string, password: string): Observable<any> {
  //    var uri = "login/signin";
  //   return this.http.post(ROOT_URL+`/${uri}`, {
  //     email,
  //     password
  //   }, httpOptions);
  // }
}