import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/common/_services/user.service';


@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  user: any = {
    email: '',
    password: '',
    name: '',
    surname: ''
  };
  submitted = false;

  constructor(private userService: UserService, private router:Router) { }

  ngOnInit(): void {
  }

  saveUserBasic(): void {
    const data = {
      email: this.user.email,
      password: this.user.password,
      name: this.user.name,
      surname: this.user.surname
    };

    this.userService.addUserBasic$(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }

  goToHome(){
    // this.router.navigate(['/']);
  }

  newTutorial(): void {
    this.submitted = false;
    this.user = {
      email: '',
      password: '',
      name: '',
      surname: ''
    };
  }

}