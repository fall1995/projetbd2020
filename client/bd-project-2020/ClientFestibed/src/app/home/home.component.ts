import { Component, OnInit } from '@angular/core';
import {TmdbService} from '../service/tmdb.service';
import {environment} from '../../environments/environment';
import {Festival} from '../Festival-DATA/Festival';
import {FestivalService} from '../service/Festival.service';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {


  domaineExistant:any[];
  domaineF ="";
  VilleSelect="";
  Datedebut="";
  DateFin="";
  festivals : Festival[];

  constructor(private festservice : FestivalService) { }
  

  ngOnInit() {
    this.domaineExistant = ['Aly', 'Demba', 'Houleye', 'Allasane', 'Demba'];
    this.init() 
  }

  async getFestivals() {

    this.festivals = await this.festservice.getFestivals({
      domaine : this.domaineF,
      Ville : this.VilleSelect,
      dateDebut : this.Datedebut,
      dateFin : this.DateFin,
    })

  }

  async init(){
    await this.getFestivals();
  }


}
