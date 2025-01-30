import { Component, EventEmitter, Output } from "@angular/core";
import { UserService } from "../../services/UserService";
import { FormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { debounceTime, distinctUntilChanged, Subject } from "rxjs";
import { SearchResultComponent } from "../search-result/search-result.component";

@Component({
    selector: 'app-search-bar',
    standalone: true,
    imports: [FormsModule, CommonModule, SearchResultComponent],
    templateUrl: './search.component.html',
    styleUrl: './search.component.css'
  })
export class SearchBarComponent {
    username: string = '';
    searchResults: any[] = [];

    @Output() searchResult = new EventEmitter<any[]>();

    constructor(private userService: UserService) {}

    onSearch(){
        if (this.username.trim()) {
            this.userService.searchUsers(this.username).subscribe({
                next: (result: any) => {
                    this.searchResults = result.data;
                    this.searchResult.emit(this.searchResults);
                },
                error: (err) => console.error('Error searching users', err)
            });
        } else {
            this.searchResults = [];
            this.searchResult.emit(this.searchResults);
        }
    }

    performSearch(username: string){
        this.userService.searchUsers(username).subscribe({
            next: (result: any) => {
                this.searchResult = result.data;
                this.searchResult.emit(result.data);
            },
            error: (err) => console.error('Error searching users', err)
        });
    }
}