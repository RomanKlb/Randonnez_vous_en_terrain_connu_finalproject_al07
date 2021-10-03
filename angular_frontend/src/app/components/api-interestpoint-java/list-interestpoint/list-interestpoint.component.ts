import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { InterestPointService } from 'src/app/common/_services/interest-point.service';
import { WeatherService } from 'src/app/common/_services/weather.service';

@Component({
  selector: 'ngbd-modal-content',
  template: `
    <div class="modal-header">
      <h4 class="modal-title">Description</h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <p>{{text}}</p>
      <p>Temps d'activité : {{temps}} minutes</p>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark" (click)="activeModal.close('Close click')">Close</button>
    </div>
  `
})
export class NgbdModalContent {
  @Input() text: any;
  @Input() temps: any;

  constructor(public activeModal: NgbActiveModal) { }
}

@Component({
  selector: 'app-list-interestpoint',
  templateUrl: './list-interestpoint.component.html',
  styleUrls: ['./list-interestpoint.component.css'],
})

export class ListInterestpointComponent implements OnInit {

  interestPoints: any = [];
  weathers: any = [];
  thematics: any = [];


  constructor(private interestPointService: InterestPointService, private weatherService: WeatherService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.retrieveInterestPoint();
    // this.retrieveWeather();
  }

  retrieveInterestPoint(): void {
    this.interestPointService.searchInterestPointByAll$()
      .subscribe(
        data => {
          this.interestPoints = data;
          console.log(this.interestPoints);
        },
        error => {
          console.log(error);
        });
  }

  // retrieveWeather(): any {
  //   this.weatherService.getAll()
  //     .subscribe(data => {
  //       this.weathers = data;
  //       console.log(this.weathers);
  //     })
  // }


  searchByThematic(nomThematic: string): any {
    this.interestPointService.searchInterestPointByThematic$(nomThematic)
      .subscribe(data => {
        this.interestPoints = data;
        console.log("in searchByT in component ts", data);
        console.log(this.interestPoints);
      },
        error => {
          console.log(error);
        })
  }

  searchByActivity(nomActivity: string): any {
    this.interestPointService.searchInterestPointByActivity$(nomActivity)
      .subscribe(data => {
        this.interestPoints = data;
        console.log("in searchByA in component ts", data);
        console.log(this.interestPoints);
      },
        error => {
          console.log(error);
        })
  }


  open(temps: any, blabla: any) {
    const modalRef = this.modalService.open(NgbdModalContent);
    modalRef.componentInstance.text = blabla;
    modalRef.componentInstance.temps = temps;
  }

}

