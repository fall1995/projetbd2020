import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {IpServiceService} from "./../ip-service.service";
import {Hotel} from '../Hotel/Hotel';
import {Residence} from '../Residence/Residence';
import {Camping} from '../Camping/Camping';
import {VillageVaccance} from '../VillageVaccance/VillageVaccance';
import {hebergementService} from '../service/hebergement.service';


@Component({
  selector: 'app-hebergement',
  templateUrl: './hebergement.component.html',
  styleUrls: ['./hebergement.component.scss']
})
export class HebergementComponent implements OnInit {

  idFestival: any;
  idf: string;
   ipAddress: string;
   idUtilisateur: string;
    ip: any;
    selectedType : string;
    Hotel ="Hotel";
    Residence = "Residence";
    camping : "Camping";
    vilalageVaca : "VillageVaccance";
    tabHotels : Hotel[];
    tabCamping : Camping[];
    tabVillageVaccance : VillageVaccance[];
    tabResidence : Residence[];

  
  constructor(private route : ActivatedRoute, private hebergServ : hebergementService) { }

  ngOnInit() {

    this.idf = this.route.snapshot.paramMap.get('id');
    this.idFestival = parseInt(this.idf, 10);
    this.getIP();
  }


  getIP() {
    this.ip.getIPAddress().subscribe((res: any) => {
      this.ipAddress = res.ip;
      this.idUtilisateur = this.ipAddress;
    });
  }

  async  getHebergement(){
    if(this.selectedType===this.Hotel){
      this.tabHotels = await this.hebergServ.getHebergement({
        idFestival : this.idFestival,
        type : this.selectedType,
      });
    }
    else if (this.selectedType===this.Residence){
      this.tabResidence = await this.hebergServ.getHebergement({
        idFestival : this.idFestival,
        type : this.selectedType,
      });
      this.tabCamping= [];
      this.tabCamping=[];
      this.tabVillageVaccance=[];

    }
    else if (this.selectedType===this.camping){
      this.tabCamping = await this.hebergServ.getHebergement({
        idFestival : this.idFestival,
        type : this.selectedType,
      });
        this.tabResidence=[];
       this.tabHotels=[];
        this.tabVillageVaccance=[];
    }
    else if (this.selectedType===this.vilalageVaca){
      this.tabVillageVaccance = await this.hebergServ.getHebergement({
        idFestival : this.idFestival,
        type : this.selectedType,
      });
      this.tabResidence=[];
      this.tabHotels=[];
      this.tabCamping=[];
    }
  }

   OnSelected(type : string){
    this.selectedType=type;
   }


}
