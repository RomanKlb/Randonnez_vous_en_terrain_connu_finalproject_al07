import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/common/data/api-user-java-modele/user';
import { TokenStorageService } from 'src/app/common/_services/token-storage.service';
import { UserService } from 'src/app/common/_services/user.service';
import { faHiking, faUser } from '@fortawesome/free-solid-svg-icons'
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UserDetailsComponent } from '../api-user-java/user-details/user-details.component';
import { AboutmeComponent } from '../aboutme/aboutme.component';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  
  user: User = new User();
  hasAdminRole = false;
  isLoggedIn: any = false;

  ngOnInit(): void {

    this.tokenStorage.estConnecte.subscribe(isConnect => {
      // this.isLoggedIn = !!this.tokenStorage.getToken();
      this.isLoggedIn = !!this.tokenStorage.getUser();
      this.user = this.tokenStorage.getUser();
      if (isConnect) {
        if (this.user.isAdmin == true) {
          this.hasAdminRole = true;
        }
        if (this.user.isAdmin == false) {
          this.hasAdminRole = false;
        }
      }
    });
  }

  constructor(private tokenStorage: TokenStorageService,
    private userService: UserService,
    private router: Router,
    private library: FaIconLibrary,
    private modalService: NgbModal) {
    library.addIcons(faHiking, faUser);
  }

  @Input()
  public titre: string = "default-title";


  reloadPage(): void {
    window.location.reload();
  }

  onLogout(): void {
    this.tokenStorage.signOut();
    this.isLoggedIn = false;
    // pb ici 
    this.router.navigate(['//']);
    // this.reloadPage();
  }

  open() {
    const modalRef = this.modalService.open(UserDetailsComponent);
  }

  openAbout() {
    const modalRef = this.modalService.open(AboutmeComponent)
  }

}