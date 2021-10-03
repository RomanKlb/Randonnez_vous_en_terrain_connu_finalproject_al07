import { Component, OnInit } from '@angular/core';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { faMapSigns } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-logo-gest-ip',
  templateUrl: './logo-gest-ip.component.html',
  styleUrls: ['./logo-gest-ip.component.css']
})
export class LogoGestIpComponent implements OnInit {

  constructor(private library: FaIconLibrary) {
    library.addIcons(faMapSigns)
  }
  ngOnInit(): void {
  }

}
