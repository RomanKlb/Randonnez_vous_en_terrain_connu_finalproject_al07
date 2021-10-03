import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faUserCog } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-logo-gest-admin',
  templateUrl: './logo-gest-admin.component.html',
  styleUrls: ['./logo-gest-admin.component.css']
})
export class LogoGestAdminComponent implements OnInit {

  constructor(private library: FaIconLibrary, private router:Router) {
    library.addIcons(faUserCog)
   }

  ngOnInit(): void {
  }

}
