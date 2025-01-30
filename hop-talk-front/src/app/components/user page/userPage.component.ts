import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { SearchBarComponent } from "../search bar/search.component";
import { GroupChannelComponent } from "../group-channels/group-channels.component";

@Component({
    selector: 'app-user',
    standalone: true,
    templateUrl: './userPage.component.html',
    styleUrl: './userPage.component.css',
    imports: [CommonModule, SearchBarComponent, GroupChannelComponent]
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