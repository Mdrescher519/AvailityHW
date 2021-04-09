import { users } from './../users';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../app.service';

@Component({
  selector: 'app-user-reg',
  templateUrl: './user-reg.component.html',
  styleUrls: ['./user-reg.component.scss']
})
export class UserRegComponent implements OnInit {

  constructor(private appService: AppService) {}

  users: users[] = [];
  tableHeader: string[] = ['User ID', 'First Name', 'Last Name', 'NPI Number', 'Address', 'Phone', 'Email'];
  isCollapsed = true;

  userRegForm = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    npiNumber: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    phone: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required)
  });

  //on page init, fetch all users from server
  ngOnInit() {
    this.getAllUsers();
  }

  onSubmit() {
    //on form submit, add user to server
    this.appService.addUser(this.userRegForm.value).subscribe(data => {
      this.userRegForm.reset();
    });
    // timeout required with no OnChange directive  - after form submit, fetch all users from server
    setTimeout(()=>{ this.getAllUsers(); }, 1500);
  }

  //fetch all users from server
  getAllUsers() {
    this.appService.getUsers().subscribe((users: users[]) => {
      this.users = users;
    });
  }
}
