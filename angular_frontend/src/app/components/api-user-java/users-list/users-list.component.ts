import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/common/data/api-user-java-modele/user';
import { UserService } from 'src/app/common/_services/user.service';
import { AddUserAdminComponent } from '../add-user-admin/add-user-admin.component';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  users: any = [];
  currentUser?: any;
  currentIndex = -1;
  email = '';

  constructor(private userService: UserService, 
    private router:Router,
    private modalService: NgbModal) { }

  ngOnInit(): void {
    this.retrieveUsers();
  }

  retrieveUsers(): void {
    this.userService.searchAllAdmins$()
      .subscribe(
        data => {
          this.users = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrieveUsers();
    //soucis de refresh
    this.currentUser = undefined;
    this.currentIndex = -1;
  }

  setActiveUser(user: User, index: number): void {
    this.currentUser = user;
    this.currentIndex = index;
    }

  removeOneActiveUser(): void{
    this.userService.deleteUserById$(this.currentUser.id)
    .subscribe(
      response => {
        console.log(response);
        this.refreshList();
      },
      error => {
        console.log(error);
      });
  }

  // addAnAdmin(): void{
  //   this.router.navigate(['/dashboard-admin/add-user-admin']);
  // }


  searchUser(): void {
    this.userService.searchUserByEmail$(this.email)
      .subscribe(
        data => {
          this.users = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  open() {
    const modalRef = this.modalService.open(AddUserAdminComponent);
  }

}