import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
import * as L from 'leaflet';
import { InterestPointService } from 'src/app/common/_services/interest-point.service';
import { WeatherService } from 'src/app/common/_services/weather.service';
import { faCircleNotch, faTemperatureHigh, faTemperatureLow, faThermometerEmpty, faThermometerFull, faThermometerHalf, faThermometerQuarter, faThermometerThreeQuarters } from '@fortawesome/free-solid-svg-icons';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-carte-home',
  templateUrl: './carte-home.component.html',
  styleUrls: ['./carte-home.component.css']
})
export class CarteHomeComponent implements AfterViewInit {

  private map: any;
  circle: any;

  // array:any = [];
  blabla: any;

  interestPoints: any = [];
  weathers: any = [];

  private initMap(): void {
    this.map = L.map('map', {
      center: [45.1885, 5.724],
      zoom: 10
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 8,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    tiles.addTo(this.map);

  }

  constructor(
    private interestPointService: InterestPointService,
    private weatherService: WeatherService,
    private library: FaIconLibrary) {
    library.addIcons(faCircleNotch, 
      faTemperatureLow, 
      faTemperatureHigh, 
      faThermometerEmpty, 
      faThermometerFull, 
      faThermometerHalf, 
      faThermometerQuarter,
      faThermometerThreeQuarters);
  }


  ngOnInit(): void {
    this.initMap();
    // this.recupWeathersAll();
    this.makeIPointMarkers(this.map);
  }

  ngAfterViewInit(): void {
  }


  makeIPointMarkers(map: any): void {
    this.interestPointService.searchInterestPointByAll$()
      .subscribe(
        data => {
          this.interestPoints = data;
          console.log(this.interestPoints);

          for (let ip of this.interestPoints) {
            let lat = parseFloat(ip.locationGeographical.latitude);
            let lon = parseFloat(ip.locationGeographical.longitude);

            this.recupWeathersByLocation(lat, lon, ip, map);
          }
        },
        error => {
          console.log(error);
        });
  }

  makeIPointPopup(ip: any, w: any): string {
    this.blabla =
      `<div>
        <div class="text-center">${ip.nomDuLieu}</div>
        <div class="text-center">La couverture nuageuse actuelle : ${w.cloudsPourcentage} %</div>
        <div class="text-center">L'humidité de l'air actuelle : ${w.humidity} %</div>
        <div class="text-center">La visibilité actuelle : ${w.visibility} mètres</div>
      </div>`;
    return this.blabla;
  }

  recupWeathersByLocation(latitude: any, longitude: any, ip: any, map: any) {
    this.weatherService.getByLocation(latitude, longitude)
      .subscribe(data => {
        console.log(data);
        this.weathers = data;

        for (let w of this.weathers) {

          console.log(w)
          console.log(Number(w.feels_like));
          if (w.feels_like) {
            if (Number(w.feels_like) <= 30 && Number(w.feels_like) >= 20.1) {
              this.circle = L.circleMarker([latitude, longitude], { radius: 5, color: "crimson" });
            }
            if (Number(w.feels_like) <= 20 && Number(w.feels_like) >= 15.1) {
              this.circle = L.circleMarker([latitude, longitude], { radius: 5, color: "orange" });
            }
            if (Number(w.feels_like) <= 15 && Number(w.feels_like) >= 10.1) {
              this.circle = L.circleMarker([latitude, longitude], { radius: 5, color: "goldenrod" });
            }
            if (Number(w.feels_like) <= 10 && Number(w.feels_like) >= 5.1) {
              this.circle = L.circleMarker([latitude, longitude], { radius: 5, color: "deepskyblue" });
            }
            if (Number(w.feels_like) <= 5 && Number(w.feels_like) >= 0.1) {
              this.circle = L.circleMarker([latitude, longitude], { radius: 5, color: "dodgerblue" });
            }
            if (Number(w.feels_like) <= 0) {
              this.circle = L.circleMarker([latitude, longitude], { radius: 5, color: "blue" });
            }
          }
          else {
            this.circle = L.circleMarker([latitude, longitude], { radius: 5, color: "red" });
          }
          this.circle.bindPopup(this.makeIPointPopup(ip, w));

          this.circle.addTo(map);
        }
      });
  }


  recupWeathersAll(): any {
    this.weatherService.getAll()
      .subscribe(data => {
        console.log(data);
        this.weathers = data;
      })
  }

}

// function facirclenotch(facirclenotch: any) {
//   throw new Error('Function not implemented.');
// }
