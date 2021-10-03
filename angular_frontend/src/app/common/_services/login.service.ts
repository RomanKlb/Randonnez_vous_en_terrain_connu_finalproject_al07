import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LoginResponse } from '../data/api-user-java-modele/loginResponse';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public currentRole : string = "";

  ROOT_URL: string = environment.PROJETFINAL_AL07_JAVA_USER_API;

  private _headers = new HttpHeaders({'Content-Type': 'application/json'}); 
  
  httpHeaders = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*')
    
  loginResponse: LoginResponse = new LoginResponse('', '', '', false, '');

  constructor(private http: HttpClient) { }


  // loginCheck$(email:any, password:any): Observable<LoginResponse> {
  //   this.loginResponse.email = email;
  //   this.loginResponse.password = password;
  //   var uri = "login/signin";
  //   return this.http.post<LoginResponse>(`${this.ROOT_URL}/${uri}`, {
  //     email, password});
  // }

//   public postLogin$(login: Login): Observable<LoginResponse>{
//     let url = this.ROOT_URL +"/login/signin";

//     return this.http.post<LoginResponse>(url,login, {headers: this._headers} )
//            .pipe(
//                tap((loginResponse: LoginResponse)=>{
//                        this.sauvegarderJeton(loginResponse);}
//                   )
//            );
           
//  }

//  private sauvegarderJeton(loginResponse:LoginResponse){
//       if(loginResponse.status){
//         console.log("es tu ici?")
//         this.currentRole = loginResponse.roles;
//         sessionStorage.setItem('authToken',loginResponse.token);
//         console.log("tokennnnnnnn" + loginResponse.token);
//       }
//       else{
//         console.log("es tu ici error???")
//        sessionStorage.setItem('authToken',"");
//        this.currentRole = "";
//       }
//  }

}