import { Component } from '@angular/core';
import { User } from './common/data/api-user-java-modele/user';
import { TokenStorageService } from './common/_services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Randonnez-vous en terrain connu';
  user: User = new User();
  
  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
  this.user = this.tokenStorage.getUser();
  }
}
