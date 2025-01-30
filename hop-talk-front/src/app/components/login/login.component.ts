import { Component } from "@angular/core";
import { UserService } from "../../services/UserService";
import { Router } from "@angular/router";
import { FormsModule } from "@angular/forms";
import { UserType } from "../../models/user.model";
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
            next: (user: UserType) => {
                console.log('Login successful:', user);
                localStorage.setItem('currentUser', JSON.stringify(user));
                this.router.navigate(['/user']);
            },
            error: (err) => {
                this.errorMessage = 'Invalid username or password';
                console.log(err);
            },
       });
    }
}