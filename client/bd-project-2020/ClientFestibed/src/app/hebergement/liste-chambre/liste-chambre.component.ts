import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {hebergementService} from '../../service/hebergement.service';
import {IpServiceService} from "../../ip-service.service";

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
  

  constructor(private router : Router, private route : ActivatedRoute, private heberg : hebergementService, private ip:IpServiceService,) { }

  ngOnInit() {
    this.numLogement = this.route.snapshot.paramMap.get('numloge');

    console.log("bug");
    this.getIP();
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

    getIP() {
      this.ip.getIPAddress().subscribe((res: any) => {
          this.ipAddress = res.ip;
          this.idUtilisateur = this.ipAddress;
          //console.log(this.idUtilisateur);
      });
  }

    onSubmit(){
        
      console.log('heyy brrs');

      this.addLogement();
  }

}
