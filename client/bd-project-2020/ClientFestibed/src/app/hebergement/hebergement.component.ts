import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
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
  idHebergement: any;
  idf : any;
   ipAddress: string;
   idUtilisateur: string;
    ip: any;
    selectedType = "HOTEL";
    Hotel ="HOTEL";
    Residence = "RESIDENCE";
    camping : "CAMPING";
    vilalageVaca : "VillageVaccance";
    tabHotels : Hotel[];
    tabCamping : Camping[];
    tabVillageVaccance : VillageVaccance[];
    tabResidence : Residence[];
    typeHebergement : any[];
  dated: string;
  datef: string;


  constructor(private route : ActivatedRoute, private hebergServ: hebergementService, private router : Router ) { }

  ngOnInit() {
    this.typeHebergement = ['HOTEL', 'RESIDENCE', 'CAMPING','VillageVaccance'];
    this.idf = this.route.snapshot.paramMap.get('id');
        this.idFestival = parseInt(this.idf, 10);
        this.dated = this.route.snapshot.paramMap.get('dated');
        this.datef = this.route.snapshot.paramMap.get('datef');
        

        this.getHebergement();
  }

  OnSelected(type : string){
    this.selectedType=type;
   }
  
   getIP() {
    this.ip.getIPAddress().subscribe((res: any) => {
      this.ipAddress = res.ip;
      this.idUtilisateur = this.ipAddress;
    });
  }

  ChambreHotel(idHebergement : number) {
    console.log(idHebergement);
    this.router.navigate(['ChambreHotel',this.idFestival,idHebergement,this.selectedType,this.datef,this.datef]);
  }

  async  getHebergement(){
    if(this.selectedType===this.Hotel){
       await this.hebergServ.getHebergement({
        idFestival : this.idFestival,
        typeHebergement : this.selectedType,
        idHebergement : this.idHebergement,
      }).then( res => {
        this.tabHotels = res;
        console.log(this.tabHotels);
      }).catch( err =>{
        console.log(err);
      });
    }
    else if (this.selectedType===this.Residence){
      this.tabResidence = await this.hebergServ.getHebergement({
        idFestival : this.idFestival,
        typeHebergement : this.selectedType,
      });
      this.tabCamping= [];
      this.tabCamping=[];
      this.tabVillageVaccance=[];

    }
    else if (this.selectedType===this.camping){
      this.tabCamping = await this.hebergServ.getHebergement({
        idFestival : this.idFestival,
        typeHebergement : this.selectedType,
      });
        this.tabResidence=[];
       this.tabHotels=[];
        this.tabVillageVaccance=[];
    }
    else if (this.selectedType===this.vilalageVaca){
      this.tabVillageVaccance = await this.hebergServ.getHebergement({
        idFestival : this.idFestival,
        typeHebergement : this.selectedType,
      });
      this.tabResidence=[];
      this.tabHotels=[];
      this.tabCamping=[];
    }
  }





}
