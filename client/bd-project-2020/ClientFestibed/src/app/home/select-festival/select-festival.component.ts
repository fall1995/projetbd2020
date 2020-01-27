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
@Input() idFestival;

  constructor(private festservice : FestivalService) { }

  ngOnInit() {

  }
  async getPlace(){
    this.places = await this.festservice.getPlaces({
      idFestivalse : this.idFestival,

    })

  }

  async init(){
    await this.getPlace();
    this.init() ;
  }

}
