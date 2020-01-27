import { Component, OnInit } from '@angular/core';
import {Festival} from "../Festival-DATA/Festival";

@Component({
  selector: 'app-ajouterfestival',
  templateUrl: './ajouterfestival.component.html',
  styleUrls: ['./ajouterfestival.component.scss']
})
export class AjouterfestivalComponent implements OnInit {
  festival : Festival;
  nomFestival : string ;
  domaine   :  string;
  complementDomaine : string;
  region : string;
  departement : number;
  periodicite : string;
  moiHabDebut  : string;
  siteWeb  :  string;
  commune  :  string;
  dateDebut  : any;
  dateFin  :  any;
  dateCreation :   any;
  codepost : number;
  codeINSEE : string;
  coord1 : string;
  coord2 : string;
  nomDepartement : string;
  nbPlaceLouees=10;

  constructor() { }

  ngOnInit() {
  }

}
