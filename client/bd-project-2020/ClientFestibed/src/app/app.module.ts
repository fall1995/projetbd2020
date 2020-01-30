import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {AngularFireModule} from '@angular/fire';
import {environment} from '../environments/environment';

// component
import { AppComponent } from './app.component';
import { SigninComponent } from './authentification/signin/signin.component';
import { SignupComponent } from './authentification/signup/signup.component';

import { MenusComponent } from './menus/menus.component';
import { HeaderComponent } from './header/header.component';
// service
import {StorageService} from './service/storage.service';
import {TmdbService} from './service/tmdb.service';
import {AuthService} from './service/auth.service';
import {MenuService} from './service/menu.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
// importation de firebase
import {AngularFireAuthModule} from '@angular/fire/auth';
import {AngularFireDatabaseModule} from '@angular/fire/database';
import {AngularFirestoreModule} from '@angular/fire/firestore';
import {AngularFireStorageModule} from '@angular/fire/storage';
import {AngularFireMessagingModule} from '@angular/fire/messaging';
import * as firebase from 'firebase';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {AuthGuardService} from './service/auth-guard.service';
import { UserProfilComponent } from './user-profil/user-profil.component';
import { HomeComponent } from './home/home.component';

// importation des modules de material design
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule, MatFormFieldModule, MatIconModule, MatOptionModule, MatSelectModule, MatExpansionModule,} from '@angular/material';
import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';
import {MatStepperModule} from '@angular/material/stepper';

// importation des module de primeng
import {TooltipModule} from 'primeng/tooltip';
import {CardModule} from 'primeng/card';
import {ButtonModule} from 'primeng/button';
import {DialogModule} from 'primeng/dialog';
import {ToastModule} from 'primeng/toast';
import {MessageService} from 'primeng/api';
import {DataViewModule} from 'primeng/dataview';
import {DropdownModule} from 'primeng/dropdown';
import {PanelModule} from 'primeng/panel';
import {TableModule} from 'primeng/table';
import {CarouselModule} from 'primeng/carousel';

import 'hammerjs';
import { UserDialogComponent } from './user-profil/user-dialog/user-dialog.component';
import {CheckboxModule, FieldsetModule, OverlayPanelModule} from 'primeng/primeng';
import { CommandeComponent } from './commande/commande.component';
import { PanierComponent } from './panier/panier.component';
import { FactureDetailComponent } from './commande/facture-detail/facture-detail.component';
import {ValidationCommandeDialogComponent} from './panier/validation-commande-dialog/validation-commande-dialog.component';
import {MatRadioModule} from "@angular/material/radio";



//ajouts 2020
import * as $ from 'jquery';
import {MultiSelectModule} from 'primeng/multiselect';
import {PaginatorModule} from 'primeng/paginator';
import {SidebarModule} from 'primeng/sidebar';
import {ScrollPanelModule} from 'primeng/scrollpanel';
import {TabViewModule} from 'primeng/tabview';
import {RatingModule} from 'primeng/rating';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {MatChipsModule} from '@angular/material/chips';
import { Ng5SliderModule } from 'ng5-slider';
import { MatGridListModule, MatToolbarModule } from '@angular/material';
import { EventEmitterService } from './service/event-emitter.service';
import {MatListModule} from '@angular/material/list';
import { TruncateModule } from 'ng2-truncate';
import {AccordionModule} from 'primeng/accordion';
import { ConfirmerCommandeComponent } from './confirmer-commande/confirmer-commande.component';
import {MatInputModule} from '@angular/material';
import {MatBadgeModule} from '@angular/material/badge';
import {LightboxModule} from 'primeng/lightbox';
import {MatMenuModule} from '@angular/material/menu';
import {MatTableModule} from '@angular/material/table';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { MatCarouselModule } from '@ngmodule/material-carousel';
import { AjouterfestivalComponent } from './ajouterfestival/ajouterfestival.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from '@angular/material';
import {MatSidenavModule} from "@angular/material/sidenav";

import { AjoutFestivalComponent } from './ajout-festival/ajout-festival.component';

import { PlaceComponent } from './AjoutPlaceFestivale/place.component';
import { SelectFestivalComponent } from './home/select-festival/select-festival.component';
import {MatDividerModule} from '@angular/material/divider';
import { HebergementComponent } from './hebergement/hebergement.component';
import { ChambreHotelComponent } from './hebergement/chambre-hotel/chambre-hotel.component';
import { ListeChambreComponent } from './hebergement/liste-chambre/liste-chambre.component';


@NgModule({
    declarations: [
        AppComponent,
        SigninComponent,
        SignupComponent,
        MenusComponent,
        HeaderComponent,
        UserProfilComponent,
        HomeComponent,
        UserDialogComponent,
        CommandeComponent,
        PanierComponent,
        ValidationCommandeDialogComponent,
        FactureDetailComponent,
        ConfirmerCommandeComponent,
        AjouterfestivalComponent,
        AjoutFestivalComponent,
        PlaceComponent,
        SelectFestivalComponent,
        HebergementComponent,
        ChambreHotelComponent,
        ListeChambreComponent,





    ],
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        AngularFontAwesomeModule,
        AppRoutingModule,
        HttpClientModule,
        AngularFireStorageModule,
        AngularFireMessagingModule,
        AngularFireAuthModule,
        AngularFireDatabaseModule,
        MatOptionModule,
        MatExpansionModule,
        MatSelectModule,
        AngularFirestoreModule,
        AngularFireModule.initializeApp(environment.firebase),
        BrowserAnimationsModule,
        MatButtonModule,
        MatCheckboxModule,
        MatCardModule,
        MatTabsModule,
        CardModule,
        DialogModule,
        ButtonModule,
        MatIconModule,
        OverlayPanelModule,
        FieldsetModule,
        ToastModule,
        DataViewModule,
        DropdownModule,
        PanelModule,
        TableModule,
        TooltipModule,
        MatStepperModule,
        MatFormFieldModule,
        CarouselModule,
        MultiSelectModule,
        PaginatorModule,
        SidebarModule,
        ScrollPanelModule,
        TabViewModule,
        RatingModule,
        MessagesModule,
        MessageModule,
        MatChipsModule,
        MatGridListModule,
        MatToolbarModule,
        CheckboxModule,
        MatRadioModule,
        Ng5SliderModule,
        MatDividerModule,
        MatListModule,
        TruncateModule,
        AccordionModule,
        MatInputModule,
        MatBadgeModule,
        LightboxModule,
        MatMenuModule,
        MatTableModule,
        MatSnackBarModule,
        MatProgressSpinnerModule,
        MatCarouselModule.forRoot(),
        MatDatepickerModule,
        MatNativeDateModule,
        MatSidenavModule

    ],
    providers: [
                TmdbService, 
                MessageService,
                AuthService,
                MenuService,
                AuthGuardService,
                StorageService,
                EventEmitterService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {

}
