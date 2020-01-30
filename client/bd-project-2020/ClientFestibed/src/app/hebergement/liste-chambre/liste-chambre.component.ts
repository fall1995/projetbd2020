import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {hebergementService} from '../../service/hebergement.service';

@Component({
  selector: 'app-liste-chambre',
  templateUrl: './liste-chambre.component.html',
  styleUrls: ['./liste-chambre.component.scss']
})
export class ListeChambreComponent implements OnInit {
  tabDisponiblite : any[];
  dateSelect : any;
  nbPlaceAdult : any;
  nbPlaceEnfant : any;
  numlog : any;

  constructor(private router : Router, private route : ActivatedRoute, private heberg : hebergementService) { }

  ngOnInit() {
    this.numlog = this.route.snapshot.paramMap.get('numloge');
  }

  
  async addPlace() {
    await this.heberg.addReservationLogement({
        // variable que le serveur s'attend a recevoir
        nulogement: this.numlog,
        date: this.dateSelect,
        nbPlaceAdult : this.nbPlaceAdult,
        nbPlaceEnfant: this.nbPlaceEnfant,
   
    }).then(res =>{
        console.log("succes")
    }).catch(error =>{
        console.log(error);

      });

    }

}
