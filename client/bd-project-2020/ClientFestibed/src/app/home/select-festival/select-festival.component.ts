import {Component, Input, OnInit} from '@angular/core';
import {Place} from "../../Place-data/Place";
import {FestivalService} from "../../service/Festival.service";

@Component({
  selector: 'app-select-festival',
  templateUrl: './select-festival.component.html',
  styleUrls: ['./select-festival.component.scss']
})
export class SelectFestivalComponent implements OnInit {
places : Place[];
 idFestival=400;

  constructor(private festservice : FestivalService) { }

  ngOnInit() {
    this.init() ;
  }
  async getPlace(){
    this.places = await this.festservice.getPlaces({
      idFestival : this.idFestival,

    })

  }

  async init(){
    await this.getPlace();
    
  }

}
