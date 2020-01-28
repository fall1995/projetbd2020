import {Component, Input, OnInit} from '@angular/core';
import {Place} from "../../Place-data/Place";
import {FestivalService} from "../../service/Festival.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-select-festival',
  templateUrl: './select-festival.component.html',
  styleUrls: ['./select-festival.component.scss']
})
export class SelectFestivalComponent implements OnInit {
    places: Place[];
     idFestival: number;
    numjour: number;
    nbPlaceSanGateg: number;
    nbPlaceCateg1: number;
    nbPlaceCateg2: number;
    idf : string;

    constructor(private festservice: FestivalService,private route : ActivatedRoute) {
    }

    ngOnInit() {
      

        this.idf = this.route.snapshot.paramMap.get('id');
        this.idFestival = parseInt(this.idf, 10);
        this.init();

    }

    async getPlace() {
        this.places = await this.festservice.getPlaces({
            idFestival: this.idFestival,

        });

    }

    async init() {
        await this.getPlace();

    }

    async addPlace() {
        this.festservice.addPlace({
            // variable que le serveur s'attend a recevoir

            idFestival: this.idFestival,
            numjour: this.numjour,
            nbPlaceSanGateg: this.nbPlaceSanGateg,
            nbPlaceCateg1: this.nbPlaceCateg1,
            nbPlaceCateg2: this.nbPlaceCateg2,

        });

    }

    // methode a appeler lors du clic sur reservation;
    onSubmit(jour : number,nbPlaceSanGateg : number, nbPlaceCateg1 : number, nbPlaceCateg2 : number){
        this.numjour=jour;
        this.nbPlaceSanGateg=nbPlaceSanGateg;
        this.nbPlaceCateg1= nbPlaceCateg1;
        this.nbPlaceCateg2 = nbPlaceCateg2;
        this.addPlace();
    }
}
