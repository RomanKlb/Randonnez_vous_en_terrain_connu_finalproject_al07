import { Component, Input, NgModule, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/common/data/api-user-java-modele/user';
import { AuthService } from 'src/app/common/_services/auth.service';
import { TokenStorageService } from 'src/app/common/_services/token-storage.service';
import { UserService } from 'src/app/common/_services/user.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AddUserComponent } from '../api-user-java/add-user/add-user.component';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  login: any = {
    email: null,
    password: null
  };
  user: User = new User;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];



  constructor(
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private router: Router,
    private userService: UserService,
    private modalService: NgbModal) { }

  ngOnInit(): void {
    
    this.tokenStorage.estConnecte.subscribe(isConnect => {
      // this.isLoggedIn = !!this.tokenStorage.getToken();
      this.isLoggedIn = isConnect;
      this.user = this.tokenStorage.getUser();
      if(!isConnect){
        this.refreshform();
      }
    });

    this.login = new FormGroup({
      Email: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/)
      ]),
      Password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(20)
      ])
    });
  }

  onSubmit(): void {
    this.authService.login(this.login.email, this.login.password)
      .subscribe(
        data => {
          console.log(data.id);
          this.tokenStorage.saveToken(data.token);
          this.tokenStorage.saveUser(data);
          this.user = data;

          this.isLoginFailed = false;
          this.isLoggedIn = true;
          this.roles = this.tokenStorage.getUser().role;
          // if (this.roles = ["ADMINISTRATOR"]) {
          //   this.router.navigate(['/dashboard-admin']);
          // }
          // if (this.roles = ["REGISTRERED"]) {
          //   this.router.navigate(['/']);
          // }
        },
        (err: { error: { message: string; }; }) => {
          this.errorMessage = "êtes-vous inscrit ?";
          this.isLoginFailed = true;
        }
      );
  }

  goToAddUser(){
    this.router.navigate(['login/add-user']);
  }

  onLogout(): void {
    this.tokenStorage.signOut();
    this.isLoggedIn = false;
    this.refreshform();
  }

  refreshform(){
    this.login.email = "";
    this.login.password = "";
  }

  open() {
    const modalRef = this.modalService.open(AddUserComponent);
  }
}
