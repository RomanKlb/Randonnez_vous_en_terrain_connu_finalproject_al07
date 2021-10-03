import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/common/data/api-user-java-modele/user';
import { TokenStorageService } from 'src/app/common/_services/token-storage.service';
import { UserService } from 'src/app/common/_services/user.service';

@Component({
  selector: 'app-dashboard-user',
  templateUrl: './dashboard-user.component.html',
  styleUrls: ['./dashboard-user.component.css']
})
export class DashboardUserComponent implements OnInit {

  content?: string;
  user: User = new User();
  isLoggedIn = false;

  constructor(private router: Router, private userService: UserService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.user = this.tokenStorageService.getUser();
    this.isLoggedIn = true;
    if (this.user == null) {
      this.isLoggedIn = false;
      this.router.navigate(['/error-not-connecting']);
    }
    if (this.user.isAdmin == true) {
      this.isLoggedIn = true;
      this.router.navigate(['/dashboard-admin']);
    }
  }
  
  onLogout(): void {
    this.tokenStorageService.signOut();
    this.isLoggedIn = false;
    this.reloadPage();
  }

  onEdit(user: any) : void {
    console.log(this.user.id);
    this.router.navigate(['dashboard-user/'+this.user.id]);
  }

  reloadPage(): void {
    window.location.reload();
    this.router.navigate(['']);
  }

  

}
