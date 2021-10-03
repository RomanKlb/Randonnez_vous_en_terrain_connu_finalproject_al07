import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/common/_services/user.service';

@Component({
  selector: 'app-add-user-admin',
  templateUrl: './add-user-admin.component.html',
  styleUrls: ['./add-user-admin.component.css']
})
export class AddUserAdminComponent implements OnInit {

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

  saveUserAdmin(): void {
    const data = {
      email: this.user.email,
      password: this.user.password,
      name: this.user.name,
      surname: this.user.surname
    };

    this.userService.addUserAdmin$(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }
  goToDashboardAdmin(){
    this.router.navigate(['/dashboard-admin/users']);
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
