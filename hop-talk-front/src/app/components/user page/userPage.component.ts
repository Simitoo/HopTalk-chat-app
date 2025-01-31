import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { SearchBarComponent } from "../search bar/search.component";
import { GroupChannelComponent } from "../group-channels/group-channels.component";
import { MessageComponent } from "../chat-message/message.component";

@Component({
    selector: 'app-user',
    standalone: true,
    templateUrl: './userPage.component.html',
    styleUrl: './userPage.component.css',
    imports: [CommonModule, SearchBarComponent, GroupChannelComponent, MessageComponent]
})
export class UserComponent {
    searchResults: any[] = [];
    currentView: string = 'friends';
    selectedChannel: any = null;

    setView(view: string){
        this.currentView = view;
    }

    openChannel(channel: any) {
        this.selectedChannel = channel;
    }

    clearSelectedChannel() {
        this.selectedChannel = null;
    }

    updateSearchResults(results: any[]) {
        this.searchResults = results;
    }

    selectChannel(channel: any) {
        this.selectedChannel = channel;
    }
}