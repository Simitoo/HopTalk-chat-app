import { Component } from "@angular/core";
import { UserService } from "../../services/UserService";
import { Router } from "@angular/router";
import { FormsModule } from "@angular/forms";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    standalone:true,
    imports: [FormsModule],
})
export class LoginPage {
    username: string = '';
    password: string = '';
    errorMessage: string = '';

    constructor(private router: Router, private userService: UserService){}

    onLogin(){
       
    }
}