import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { environment } from "../../environments/environment";
import { UserService } from "../../services/UserService";
import { Router } from "@angular/router";
import { UserType } from "../../models/user.model";
import { CommonModule } from "@angular/common";
import { FileUploadComponent } from "../file/upload.component";
import { FileUploadService } from "../../services/FileUploadService";

@Component({
    selector: 'app-register',
    templateUrl: 'register.component.html',
    styleUrls: ['register.component.css'],
    standalone: true,
    imports: [FormsModule, CommonModule, FileUploadComponent]
})
export class RegisterComponent {
    selectedFile: File | null = null;
    isUploading: boolean = false;
    userData: UserType = {
        username: '',
        password: '',
        iconUrl: environment.defaultIconUrl,
    };

    constructor(private userService: UserService, private fileUploadService: FileUploadService, private router: Router) {}

    onFileSelected(file: File): void {
        this.selectedFile = file;
    }

    onRegister(): void {
        if (this.selectedFile) {
            this.isUploading = true;
            this.fileUploadService.uploadImage(this.selectedFile).subscribe({
                next: (response) => {
                    this.userData.iconUrl = response.secure_url;
                    this.registerUser();
                },
                error: (err) => {
                    console.error('Error uploading file', err);
                    this.isUploading = false;
                },
            });
        } else {
            this.registerUser();
        }
    }

    private registerUser(): void {
        this.userService.register(this.userData).subscribe({
            next: (response) => {
                console.log('User registered successfully', response);
                localStorage.setItem('currentUser', JSON.stringify(this.userData));
                this.router.navigate(['/user']);
            },
            error: (err) => {
                console.error('Error registering user', err);
            },
            complete: () => {
                console.log('Registration process completed.');
            }
        });
    }
}
