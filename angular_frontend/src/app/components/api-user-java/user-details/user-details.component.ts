import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/common/data/api-user-java-modele/user';
import { TokenStorageService } from 'src/app/common/_services/token-storage.service';
import { UserService } from 'src/app/common/_services/user.service';


@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})

export class UserDetailsComponent implements OnInit {
  user: User = new User;
  editUser: User = {
    id: '',
    email: '',
    password: '',
    name: '',
    surname: ''
  };
  message = '';
  submitted = false;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.user = this.tokenStorageService.getUser();
    console.log(this.user)
    this.editUser.id = this.user.id;
    this.editUser.email = this.user.email;
    this.editUser.password = '';
    this.editUser.name = this.user.name;
    this.editUser.surname = this.user.surname;
    console.log(this.editUser)
    this.message = '';
  }

  updateUser(): void {
    this.userService.updateUser$(this.editUser)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
          this.message = "La modification a bien été prise en compte";
        },
        error => {
          console.log(error);
        });
  }

}
