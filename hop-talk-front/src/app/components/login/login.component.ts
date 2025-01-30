import { Component } from "@angular/core";
import { UserService } from "../../services/UserService";
import { Router } from "@angular/router";
import { FormsModule } from "@angular/forms";
import { UserResponse } from "../../models/user-response";
import { CommonModule } from "@angular/common";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    standalone: true,
    imports: [FormsModule, CommonModule],
})
export class LoginComponent {
    username: string = '';
    password: string = '';
    errorMessage: string = '';

    constructor(private userService: UserService, private router: Router){}

    onLogin(){
       this.userService.login(this.username, this.password).subscribe({
            next: (response: UserResponse) => {
                console.log('Login successful:', response.data);
                localStorage.setItem('currentUser', JSON.stringify(response.data));
                this.router.navigate(['/user']);
            },
            error: (err) => {
                this.errorMessage = 'Invalid username or password';
                console.log(err);
            },
       });
    }
}