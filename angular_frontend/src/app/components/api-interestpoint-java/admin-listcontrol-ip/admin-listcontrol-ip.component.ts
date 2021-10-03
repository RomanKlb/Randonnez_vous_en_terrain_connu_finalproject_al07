import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InterestPoint } from 'src/app/common/data/api-interestpoint-java-modele/interestPoint';
import { InterestPointService } from 'src/app/common/_services/interest-point.service';

@Component({
  selector: 'app-admin-listcontrol-ip',
  templateUrl: './admin-listcontrol-ip.component.html',
  styleUrls: ['./admin-listcontrol-ip.component.css']
})
export class AdminListcontrolIpComponent implements OnInit {

  interestpoints: any = [];
  currentIP?: any;
  currentIndex= -1;
  nomDuLieu = '';

  constructor(private interestPointService: InterestPointService,
    private router:Router) { }

  ngOnInit(): void {
    this.retrieveIPs();
  }

  retrieveIPs(): void {
    this.interestPointService.searchInterestPointByAll$()
    .subscribe(
      data => {
        this.interestpoints = data;
      },
      error => {
        console.log(error);
      });
  }

  refreshList(): void {
    this.retrieveIPs();
    //soucis de refresh
    this.currentIP = undefined;
    this.currentIndex = -1;
  }

  setActiveIP(interestPoint: InterestPoint, index: number): void {
    this.currentIP = interestPoint;
    this.currentIndex = index;
    }

  removeOneActiveIP(): void{
    this.interestPointService.deleteInterestPointById$(this.currentIP.id)
    .subscribe(
      response => {
        console.log(response);
        this.refreshList();
      },
      error => {
        console.log(error);
      });
  }

  addAnIP(): void {
    this.router.navigate(['/dashboard-admin/add-interestpoint']);
  }

  searchIP(): void {
    this.interestPointService.searchInterestPointByLocationName$(this.nomDuLieu)
      .subscribe(
        data => {
          this.interestpoints = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

}
