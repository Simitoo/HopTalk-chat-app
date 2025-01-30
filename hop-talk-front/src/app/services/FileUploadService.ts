import { HttpClient } from "@angular/common/http";
import { environment } from "../environments/environment";
import { Observable, throwError } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class FileUploadService {
    cloudinaryUrl: string = environment.cloudinaryUploadUrl;
    cloudinaryPreset: string = environment.cloudinaryPreset;

    constructor(private http: HttpClient) {}

    uploadImage(file: File): Observable<any> {

        if (!['image/jpeg', 'image/png'].includes(file.type)) {
            return throwError(() => new Error('Invalid file type. Please upload a JPG or PNG image.'));
        }
        if (file.size > 5 * 1024 * 1024) {
            return throwError(() => new Error('File size is too large. Maximum allowed size is 5MB.'));
        }

        const formData = new FormData();
        formData.append('file', file);
        formData.append('upload_preset', this.cloudinaryPreset);

        console.log('FormData entries:', Array.from(formData.entries()));

        return this.http.post(this.cloudinaryUrl, formData);
    }
}