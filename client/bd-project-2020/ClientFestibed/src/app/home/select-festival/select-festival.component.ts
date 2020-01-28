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
    jour: number;
    nbPlaceSansCateg: number;
    nbPlaceCateg1: number;
    nbPlaceCateg2: number;
    idf : string;
    idUtilisateur= 8;
    tabOption : number[];

    constructor(private festservice: FestivalService,private route : ActivatedRoute) {
    }

    ngOnInit() {

        this.idf = this.route.snapshot.paramMap.get('id');
        this.idFestival = parseInt(this.idf, 10);
        this.init();
       
        this.remplirTab();
        console.log(this.tabOption.length);
       
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
        await this.festservice.addPlace({
            // variable que le serveur s'attend a recevoir
            idUtilisateur: this.idUtilisateur,
            idFestival: this.idFestival,
            jour : this.jour,
            nbPlaceSansCateg: this.nbPlaceSansCateg,
            nbPlaceCateg1: this.nbPlaceCateg1,
            nbPlaceCateg2: this.nbPlaceCateg2


        }).then(res =>{
            console.log("succes")
        }).catch(error =>{
            console.log(error);
            console.log(this.idUtilisateur);
            console.log(this.idFestival);
            console.log(this.nbPlaceSansCateg);
            console.log(this.nbPlaceCateg1);
            console.log(this.nbPlaceCateg2);
            console.log(this.jour);
            
        });

    }

    remplirTab(){
        let i = 1;
        for (let i = 1; i <this.places.length+1; i++){
             this.tabOption[i] = i;
           
        }
    }

    // methode a appeler lors du clic sur reservation;
    onSubmit(){
        
        console.log('heyy Aly');
        console.log(this.tabOption.length);
        this.addPlace();
    }
}