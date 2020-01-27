import { Component, OnInit } from '@angular/core';
import {TmdbService} from '../service/tmdb.service';
import {environment} from '../../environments/environment';
import {Festival} from '../Festival-DATA/Festival';
import {FestivalService} from '../service/Festival.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {


  domaineExistant:any[];
  domaineF = "";
  VilleSelect= "";
  Datedebut= "";
  DateFin = "";
  selectedFest: Festival; // festival selectionner
  festivals : Festival[];
  displayDialog: boolean;
  displayeD : boolean;
  nombrePlace = 1;
  router : Router;

  constructor(private festservice : FestivalService) { }
  

  ngOnInit() {
    this.domaineExistant = ['Aly', 'Demba', 'Houleye', 'Allasane', 'Demba','Aly', 'Demba', 'Houleye', 'Allasane', 'Demba',
      'Aly', 'Demba', 'Houleye', 'Allasane', 'Demba','Aly', 'Demba', 'Houleye', 'Allasane', 'Demba','Aly', 'Demba', 'Houleye', 'Allasane', 'Demba'];
    this.init() ;
  }

  async getFestivals() {

    this.festivals = await this.festservice.getFestivals({
      domaine : this.domaineF,
      Ville : this.VilleSelect,
      dateDebut : this.Datedebut,
      dateFin : this.DateFin,
    })

  }
  selectdetails(fes: Festival) {
    this.selectedFest = fes;
    this.displayDialog = true;
    event.preventDefault();
  }

  selectPlace(fes: Festival) {
    this.selectedFest = fes;
    this.displayeD = true;
    event.preventDefault();
  }

  fermerPopup() {
    this.displayeD=false;
  }

  changementDePage() {
    this.router.navigate(['AjoutPlaceFestivale']);
  }

  async init(){
    await this.getFestivals();
  }



}
