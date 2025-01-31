import { CommonModule } from "@angular/common";
import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { GroupChannelService } from "../../services/GroupChannelService";
import { GroupChannel } from "../../models/group-channel.model";

@Component({
    selector: 'app-group-channels',
    standalone: true,
    templateUrl: './group-channels.component.html',
    styleUrls: ['./group-channels.component.css'],
    imports: [CommonModule]
})
export class GroupChannelComponent implements OnInit {
    groupChannels: GroupChannel[] = [];
    userId: number | null = null;

    constructor(private groupChannelService: GroupChannelService) {}

    @Output() channelSelected = new EventEmitter<any>();

    selectChannel(channel: any) {
        this.channelSelected.emit(channel);
    }

    ngOnInit(): void {
        this.fetchUserId();
        if(this.userId !== null){
            this.fetchGroupChannels();
        } else {
            console.error("User ID not found.");
        }
    }

    fetchUserId() {
        const currUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
        if(currUser && currUser.id){
            this.userId = currUser.id;
        }
    }

    fetchGroupChannels() {
        if (this.userId !== null) {
            this.groupChannelService.getUserGroupChannels(this.userId).subscribe({
                next: (channels) => {
                    console.log('Fetched group channels:', channels);
                    this.groupChannels = channels; 
                },
                error: (error) => {
                    console.error('Failed to load group channels', error);
                }
            });
        }
    }

    handleAction(channel: GroupChannel){
        const selectedChannel = {
            id: channel.id,
            type: 'CHANNEL',
            iconUrl: channel.iconUrl,
            title: channel.title,
            participants: channel.participantsList
        };

        localStorage.setItem('selectedChannel', JSON.stringify(selectedChannel));
        console.log('Selected channel stored:', selectedChannel);

        window.location.reload();
    }

}