import { CommonModule, NgIf } from "@angular/common";
import { Component } from "@angular/core";
import { SearchBarComponent } from "../search bar/search.component";

@Component({
    selector: 'app-user',
    standalone: true,
    templateUrl: './userPage.component.html',
    styleUrl: './userPage.component.css',
    imports: [CommonModule, SearchBarComponent]
})
export class UserComponent {
    searchResults: any[] = [];
    currentView: string = 'friends';

    setView(view: string){
        this.currentView = view;
    }

    updateSearchResults(results: any[]) {
        this.searchResults = results;
    }
}