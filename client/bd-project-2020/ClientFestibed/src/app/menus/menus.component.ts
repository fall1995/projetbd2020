import {Component, OnInit} from '@angular/core';
import {MenuService} from '../service/menu.service';
import {Plats} from '../menu-commade-data/Menu';
import {MessageService, SelectItem} from 'primeng/api';
import {StorageService} from '../service/storage.service';

//new
import { Options, LabelType } from 'ng5-slider';
import { EventEmitterService } from '../service/event-emitter.service';

@Component({
    selector: 'app-menus',
    templateUrl: './menus.component.html',
    styleUrls: ['./menus.component.scss']
})
export class MenusComponent implements OnInit {

    /**
     *variable dans le quel on stock les plats après les avoirs récupéré
     */

    plats: Plats[];
    selectedPlat: Plats; // plat selectionné
    sortOptions: SelectItem[];
    displayDialog: boolean;
    sortKey: string;
    sortField: string;
    sortOrder: number;
    boolPrix = false;


//Nouveautés
    typePlats: any;
    typeSelectionne: any;
    //Slider
    minValue: number = 1;
    maxValue: number = 16;
    options: Options = {
        floor: 1,
        ceil: 16,
        translate: (value: number, label: LabelType): string => {
            switch (label) {
                case LabelType.Low:
                    return `<b>Prix Min:</b> ${value}€`;
                case LabelType.High:
                    return `<b>Prix Max:</b> ${value}€`;
                default:
                    return value + '€';
            }
        }
    };


    constructor(private eventEmitterService: EventEmitterService, private menuService: MenuService, private storageService: StorageService,
                private message: MessageService){
    }

    ngOnInit() {
        this.typePlats = ['Tous les plats', 'Entrée', 'Plat', 'Dessert', 'Boisson', 'Hors d\'oeuvre'];
        this.init();
        this.sortOptions = [
            {label: 'Nom', value: 'id'},
            {label: 'Type plat', value: 'type'},
            {label: 'Prix', value: 'prix'},
        ];
    }

    selectCar(plat: Plats) {
        this.selectedPlat = plat;
        this.displayDialog = true;
        event.preventDefault();
    }


    // aly version 2 recuperation plats
    async PrixetType() {
        if (this.typeSelectionne) {
            console.log(this.typeSelectionne)
        }
        this.plats = await this.menuService.getPlatParPrixEtType({
            min: this.minValue,
            max: this.maxValue,
            typeSelectionne: this.typeSelectionne
        })

    }

    onSortChange(event) { 
        let value = event.value;
        if (value.indexOf('!') === 0) {
            this.sortOrder = -1;
            this.sortField = value.substring(1, value.length);
        } else {
            this.sortOrder = 1;
            this.sortField = value;
        }
    }

    async init() {

        await this.PrixetType();

    }

    /**
     * methode d'ajout du plat dans le panier après selection
     * @param id
     */
    addMenus(id: string) {
        let plat = this.plats.find(plats => plats.id === id);
        this.storageService.addMenu(plat);
        this.message.add({
            severity: 'success',
            summary: `Ajout de ${plat.id} `,
            detail: 'Votre choix a bien été ajouter dans votre panier'
        });
        this.eventEmitterService.refreshPanier();    
    }


}
