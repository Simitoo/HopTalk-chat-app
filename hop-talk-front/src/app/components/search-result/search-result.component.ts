import { CommonModule } from "@angular/common";
import { Component, Input } from "@angular/core";

@Component({
    selector: 'app-search-result',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './search-result.component.html',
    styleUrl: './search-result.component.css'
})
export class SearchResultComponent {
    @Input() searchResults: any[] = [];
}