import { Injectable, EventEmitter } from '@angular/core'; 

@Injectable({
  providedIn: 'root'
})
export class EventEmitterService {

  invokePanier = new EventEmitter();      
    
  constructor() { }    
    
  refreshPanier() {    
    this.invokePanier.emit();    
  }  
}
