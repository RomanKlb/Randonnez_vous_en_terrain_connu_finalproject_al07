import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../data/api-user-java-modele/user';



@Injectable({
  providedIn: 'root'
})
export class UserService {

  ROOT_URL = environment.PROJETFINAL_AL07_JAVA_USER_API;
  

  httpHeaders = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*')


  constructor(private http: HttpClient) { }

 

  searchUserById$(id: string): Observable<User> {
    var uri = "user/searchUserId/" + id;
    return this.http.get(`${this.ROOT_URL}/${uri}`);
  }

  searchUserByEmail$(email: string): Observable<User> {
    var uri = "user/searchUserMail/" + email;
    return this.http.get<User>(`${this.ROOT_URL}/${uri}`);
  }

  searchAllUsers$(): Observable<User> {
    var uri = "user/searchUser";
    return this.http.get<User>(`${this.ROOT_URL}/${uri}`);
  }

  searchAllAdmins$(): Observable<User> {
    var uri = "user/searchAdmin";
    return this.http.get<User>(`${this.ROOT_URL}/${uri}`);
  }

  addUserBasic$(user: User): Observable<User> {
    var uri = "user/addUser";
    return this.http.post<User>(`${this.ROOT_URL}/${uri}`, user);
    //,httpOptions)
  }

  addUserAdmin$(user: User): Observable<User> {
    var uri = "user/addAdminUser";
    return this.http.post<User>(`${this.ROOT_URL}/${uri}`, user);
    //,httpOptions)
  }

  deleteUserById$(id: any): Observable<User> {
    var uri = "user/deleteUser/" + id;
    return this.http.delete<User>(`${this.ROOT_URL}/${uri}`);
  }

  deleteAllUsers$(): Observable<User> {
    var uri = "user/deleteUser/all";
    return this.http.delete<User>(`${this.ROOT_URL}/${uri}`);
  }

  updateUser$(user: User): Observable<User> {
    var uri = "user/editUser/" + user.id;
    return this.http.put<User>(`${this.ROOT_URL}/${uri}`, user);
    //,httpOptions)
  }

}