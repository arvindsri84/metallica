import { Component, OnInit,Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { UserManagementService, User } from '../services/user-management.service';
import { RefdataService } from '../services/refdata-service';


@Component({
  selector: 'app-user-actions',
  templateUrl: './user-actions.component.html',
  styleUrls: ['./user-actions.component.scss']
})
export class UserActionsComponent implements OnInit {

  name: string;

  constructor(
    public dialog: MatDialog,
    private userManagementService: UserManagementService,
    private refdataService: RefdataService
  ) { }

  ngOnInit() {
  }

  openDialog(): void {
    
    let dialogRef = this.dialog.open(LoginDialog, {
      width: '40%',
      data: { username: null, password: null, name: null, error: null }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.name = result.name;
      this.refdataService.getRefdata(this.userManagementService.getUser());
    });

  }

  logout(): void{
    this.userManagementService.setUser(null);
    this.name = null;
  }
}


@Component({
  selector: 'login-dialog',
  templateUrl: 'login-dialog.html',
})
export class LoginDialog {

  constructor(public dialogRef: MatDialogRef<LoginDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any, private userManagementService: UserManagementService) { }

  onCancel(): void {
    this.dialogRef.close();
  }

  onLogin(data): void{
    console.log(data);

    this.userManagementService.authenticate(data.username,data.password).subscribe(
      result => {
        console.log(result);
        let user = new User();
        user.access_token = result.access_token;
        this.userManagementService.setUser(user);

        this.userManagementService.getUserDetails().subscribe(
          res => {
            console.log(res);
            user.name = res.name;
            data.name = user.name;
            this.dialogRef.close(data);
          },
          err => {
            console.log(err);
            if(err.error){
              this.data.error = err.error.error;
            }else{
              this.data.error = err.message;
            }
          }
        );
      },
      err => {
        console.log(err);
        if(err.error){
          this.data.error = err.error.error;
        }else{
          this.data.error = err.message;
        }
      }
    );
  }

}