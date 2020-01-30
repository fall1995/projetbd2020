import {Component, Input, OnInit} from '@angular/core';
import {Place} from "../../Place-data/Place";
import {FestivalService} from "../../service/Festival.service";
import {ActivatedRoute, Router} from '@angular/router';
import {IpServiceService} from "../../ip-service.service";
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-select-festival',
  templateUrl: './select-festival.component.html',
  styleUrls: ['./select-festival.component.scss']
})
export class SelectFestivalComponent implements OnInit {
    ipAddress:string;
    places: Place[];
    idFestival: any;
    jour: any;
    nbPlaceSansCateg: any;
    nbPlaceCateg1: any;
    nbPlaceCateg2: any;
    idf : any;
    idUtilisateur : any;
    tabOption : any[];
    dated: string;
    datef: string;
    reponse : any;

    constructor(private festservice: FestivalService,private route : ActivatedRoute,
                private ip:IpServiceService, private router : Router, private message : MessageService) {
    }

    ngOnInit() {

        this.idf = this.route.snapshot.paramMap.get('id');
        this.dated = this.route.snapshot.paramMap.get('dated');
        this.datef = this.route.snapshot.paramMap.get('datef');
        this.idFestival = parseInt(this.idf, 10);
        this.init();
        this.getIP();
        //this.remplirTab();
       // console.log(this.tabOption.length);
       
    }

    async getPlace() {
        this.places = await this.festservice.getPlaces({
            idFestival: this.idFestival,

        });

    }

    hebergement(){
        this.router.navigate(['Hebergement',this.idFestival,this.dated,this.datef]);
       
    }

    async init() {
        await this.getPlace();

    }

    async addPlace() {
      this.reponse=  await this.festservice.addPlace({
            // variable que le serveur s'attend a recevoir
            idUtilisateur: this.idUtilisateur,
            idFestival: this.idFestival,
            jour : this.jour,
            nbPlaceSansCateg: this.nbPlaceSansCateg,
            nbPlaceCateg1: this.nbPlaceCateg1,
            nbPlaceCateg2: this.nbPlaceCateg2


        }).then(res =>{
            this.message.add({severity:'success',
            summary:`Etat de Votre reservation  `,
            detail:'${u.user.displayName}'});
            
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
        for (i = 1; i <this.places.length+1; i++){
             this.tabOption[i] = i;
           
        }
    }

    // methode a appeler lors du clic sur reservation;
    onSubmit(){
        
        console.log('heyy Aly');

        this.addPlace();
    }

    getIP() {
        this.ip.getIPAddress().subscribe((res: any) => {
            this.ipAddress = res.ip;
            this.idUtilisateur = this.ipAddress;
        });
    }


    }
