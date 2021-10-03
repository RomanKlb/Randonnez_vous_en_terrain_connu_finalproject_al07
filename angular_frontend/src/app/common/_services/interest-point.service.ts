import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { InterestPoint } from '../data/api-interestpoint-java-modele/interestPoint';


@Injectable({
  providedIn: 'root'
})
export class InterestPointService {

  ROOT_URL = environment.PROJETFINAL_AL07_JAVA_INTERESTPOINT_API;

  httpHeaders = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*')

  constructor(private http: HttpClient) { }


  // addInterestPoint$(formData: any) {
  //   var uri = "interestpoint/add";
  //   console.log("Objet inséré : " + JSON.stringify(formData));
  //   return this.http.post<InterestPoint>(`${this.ROOT_URL}/${uri}`, formData);
  // }

  addInterestPoint$( point: any) {
    console.log(point)
    var uri = "interestpoint/addPoint";
    return this.http.post<InterestPoint>(`${this.ROOT_URL}/${uri}`,point);
  }

  addImage(formData: any) {
    var uri = "interestpoint/addImage";
    console.log("Objet inséré : " + JSON.stringify(formData));
    return this.http.post<InterestPoint>(`${this.ROOT_URL}/${uri}`, formData);
  }


  deleteInterestPointById$(id: any): Observable<InterestPoint> {
    var uri = "interestpoint/delete/id=" + id;
    return this.http.delete<InterestPoint>(`${this.ROOT_URL}/${uri}`);
  }

  // updateInterestPoint$(interestPoint: InterestPoint): Observable<InterestPoint> {
  //   var uri = "interestpoint/edit/id=" + interestPoint.id;
  //   return this.http.put<InterestPoint>(`${this.ROOT_URL}/${uri}`, interestPoint);
  // }

  searchInterestPointByAll$(): Observable<InterestPoint> {
    var uri = "interestpoint/search/all";
    return this.http.get<InterestPoint>(`${this.ROOT_URL}/${uri}`);
  }

  searchInterestPointById$(id: any): Observable<InterestPoint> {
    var uri = "interestpoint/search/id=" + id;
    return this.http.get<InterestPoint>(`${this.ROOT_URL}/${uri}`);
  }

  searchInterestPointByLocationName$(name: string): Observable<InterestPoint> {
    var uri = "interestpoint/search/lieu=" + name;
    return this.http.get<InterestPoint>(`${this.ROOT_URL}/${uri}`);
  }

  searchInterestPointByThematic$(nomThematic: string): Observable<InterestPoint> {
    var uri = "interestpoint/search/thematic=" + nomThematic;
    return this.http.get<InterestPoint>(`${this.ROOT_URL}/${uri}`);
  }

  searchInterestPointByActivity$(nomActivity: string): Observable<InterestPoint> {
    var uri = "interestpoint/search/activity=" + nomActivity;
    return this.http.get<InterestPoint>(`${this.ROOT_URL}/${uri}`);
  } 

}
