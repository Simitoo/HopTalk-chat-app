import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { Router } from "@angular/router";

@Component({
    selector: 'app-home-page',
    templateUrl: './home.component.html',
    styleUrl: './home.component.css',
    standalone: true,
    imports: [CommonModule]
})
export class HomePage{
    constructor(private router: Router) {}

    goToLogin(){
        this.router.navigate(['/login']);
    }

    goToRegister(){
        this.router.navigate(['/register']);
    }
}