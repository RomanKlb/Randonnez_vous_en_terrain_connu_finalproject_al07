import { Component, OnInit } from '@angular/core';
import { WeatherService } from 'src/app/common/_services/weather.service';

@Component({
  selector: 'app-weather-list',
  templateUrl: './weather-list.component.html',
  styleUrls: ['./weather-list.component.css']
})
export class WeatherListComponent implements OnInit {

 content?: string
 listData:any = []
  

  constructor(private weatherService: WeatherService) { }

  ngOnInit(): void {
    this.weatherService.getAll()
    .subscribe (res => {
      console.log(res)
      this.listData = res;
    });
  }

  // retrieveUsers(): void {
  //   this.weatherService.getAllWeathers$()
  //     .subscribe(
  //       data => {
  //         this.weathers = data;
  //         console.log(data);
  //       },
  //       error => {
  //         console.log(error);
  //       });
  // }

  // refreshList(): void {
  //   this.retrieveUsers();
  //   this.currentWeather = undefined;
  //   this.currentIndex = -1;
  // }

  // setActiveWeather(weather: any, index: number): void {
  //   this.currentWeather = weather;
  //   this.currentIndex = index;
  // }

  // searchWeatherByLatNlon(): void {
    
  // }

}
