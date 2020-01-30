import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {hebergementService} from '../../service/hebergement.service';
import {IpServiceService} from "../../ip-service.service";
import { User } from 'src/app/tmdb-data/user';
import { AngularFireAuth } from '@angular/fire/auth';

@Component({
  selector: 'app-liste-chambre',
  templateUrl: './liste-chambre.component.html',
  styleUrls: ['./liste-chambre.component.scss']
})
export class ListeChambreComponent implements OnInit {
  tabDisponiblite : any[];
  jour : Date;
  nbPlaceAdulte : any;
  nbPlaceEnfant : any;
  numLogement : any;
  ipAddress:string;
  idUtilisateur : any;
  user: User;
  

  constructor(private router : Router, private route : ActivatedRoute, private heberg : hebergementService,
    private afAuth: AngularFireAuth, private ip:IpServiceService,) { }

  ngOnInit() {
    this.numLogement = this.route.snapshot.paramMap.get('numloge');

    console.log("bug");
    this.idutilisa();
   
    this.init();
   
  }

  async getTabDispo() {
    this.tabDisponiblite = await this.heberg.tabDispo({
      numLogement: this.numLogement,

    });

}

async init() {
  await this.getTabDispo();

}
  
  async addLogement() {
    console.log(this.idUtilisateur);
    await this.heberg.addReservationLogement({
        // variable que le serveur s'attend a recevoir
        idUtilisateur : this.idUtilisateur,
        numLogement: this.numLogement,
        jour: this.jour,
        nbPlaceAdulte : this.nbPlaceAdulte,
        nbPlaceEnfant: this.nbPlaceEnfant,
        
   
    }).then(res =>{
        console.log("succes")
    }).catch(error =>{
        console.log(error);

      });

    }

    

    onSubmit(){
        
      console.log('heyy brrs');

      this.addLogement();
  }

  idutilisa(){
    this.afAuth.user.subscribe(utilisateur =>{
        this.idUtilisateur=utilisateur.uid;
              
            });
            console.log(this.idUtilisateur)
        }

}
