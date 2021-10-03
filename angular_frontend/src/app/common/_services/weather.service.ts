import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  // private _apiWeatherNodejsBaseUrl = 'http://localhost:5000';
  ROOT_URL = environment.PROJETFINAL_AL07_API;

  httpHeaders = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*')

  constructor(private httpClient: HttpClient) { }

  getAll() {
    var uri = "weather/all";
    return this.httpClient.get(`${this.ROOT_URL}/${uri}`);
  }
  getByLocation(latitude: any, longitude: any) : Observable<any> {
    var uri= "weather/coord/"+latitude
    var uri2 = longitude;
    console.log(`${this.ROOT_URL}/${uri}/${uri2}`);
    return this.httpClient.get(`${this.ROOT_URL}/${uri}/${uri2}`);
  }

  getByName(name: any) : Observable<any> {
    var uri= "weather/city/" + name;
    return this.httpClient.get(`${this.ROOT_URL}/${uri}`);
  }

}