import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/common/data/api-user-java-modele/user';
import { TokenStorageService } from 'src/app/common/_services/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User = new User();
  isLoggedIn: any = false;
  roles: string[] = [];

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {

    this.tokenStorageService.estConnecte.subscribe(isConnect => {
      // this.isLoggedIn = !!this.tokenStorage.getToken();
      this.isLoggedIn = isConnect;
      this.user = this.tokenStorageService.getUser();
    });



    if (this.tokenStorageService.getToken()) {
      this.user = this.tokenStorageService.getUser();
      this.isLoggedIn = !!this.tokenStorageService.getUser();
      this.roles = this.tokenStorageService.getUser().roles;
    }
    // this.user = this.tokenStorageService.getUser();
    // if (this.user != null)
    //   this.isLoggedIn = true;
  }

}
