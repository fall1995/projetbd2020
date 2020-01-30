import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {hebergementService} from '../../service/hebergement.service';

@Component({
  selector: 'app-chambre-hotel',
  templateUrl: './chambre-hotel.component.html',
  styleUrls: ['./chambre-hotel.component.scss']
})
export class ChambreHotelComponent implements OnInit {
  numlogement : any;
  idf: any;
  idFestival : any;
  dated: any;
  datef: any;
  type: any;
  idhebergement: string;
  tabChambre : any[];
 

  constructor(private router : Router, private route : ActivatedRoute, private heberg : hebergementService) { 
    
  }

  ngOnInit() {
    this.idf = this.route.snapshot.paramMap.get('id');
        this.idFestival = parseInt(this.idf, 10);
        this.dated = this.route.snapshot.paramMap.get('dated');
        this.datef = this.route.snapshot.paramMap.get('datef');
        this.idhebergement = this.route.snapshot.paramMap.get('idheber');
        this.type = this.route.snapshot.paramMap.get('type');
        this.getChambre();
  }

  async getChambre(){
    this.tabChambre = await this.heberg.getchambres({
      idFestival : this.idFestival,
      idHebergement : this.idhebergement,
      dateDF : this.dated,
      dateFF : this.datef,
      typeHebergement : this.type
      
  });
  console.log(this.idFestival);
  console.log(this.dated);
  console.log(this.datef);
  console.log(this.type);
  console.log(this.idhebergement);

}

ChambreListeHotel(numlog : any) {
  console.log(numlog);
  this.router.navigate(['chambre-liste',numlog]);
}




  


}
