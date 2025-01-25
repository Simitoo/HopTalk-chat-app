import { Component, EventEmitter, Output } from "@angular/core";
import { CommonModule } from "@angular/common";
import { enviroment } from "../../environments/environment";

@Component({
    selector: 'app-file',
    standalone: true,
    imports: [CommonModule],
    templateUrl: 'upload.component.html',
    styleUrl: 'upload.component.css',
})
export class FileUploadComponent {
    previewUrl: string = enviroment.defaultIconUrl;
    @Output() imageUploaded = new EventEmitter<File>();

    onFileSelected(event: any): void {
        const selectedFile = event.target.files[0];

        if(selectedFile) {
            const reader = new FileReader();
            reader.onload = () => {
                this.previewUrl = reader.result as string;
            };
            reader.readAsDataURL(selectedFile);
            
            this.imageUploaded.emit(selectedFile);
        }
    }
}