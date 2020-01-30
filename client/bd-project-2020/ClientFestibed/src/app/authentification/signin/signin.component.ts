import { Component, OnInit } from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth';
import {auth} from 'firebase';
import {AuthService} from '../../service/auth.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {TmdbService} from '../../service/tmdb.service';
import {environment} from '../../../environments/environment';
import {MovieResponse} from '../../tmdb-data/Movie';
import {MessageService} from 'primeng/api';
import { Injectable } from '@angular/core';



@Component({
  selector: 'signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

    signInForm : FormGroup;
    errorMessage : string;
    isAuth : boolean;
    listeMovie: MovieResponse;

  constructor(private afAuth: AngularFireAuth, private authService : AuthService, private message : MessageService,
              private formBuilder: FormBuilder, private route: Router, private tmdbService : TmdbService) {
  }

    ngOnInit() {
        this.initForm();
        this.afAuth.auth.onAuthStateChanged(
            (user) =>{
                if (user){
                    this.isAuth = true;
                } else {
                    this.isAuth = false;
                }
            }
        );
        this.init();
    }

    init() {
        this.tmdbService.init( environment.tmdbKey );
    }

    /**
     * login with Google acompt
     */
    loginGoogle() {
        this.afAuth.auth.signInWithPopup(new auth.GoogleAuthProvider()).then(
            u => {
                this.route.navigate(['/home']);
                this.message.add({severity:'success',
                    summary:`Bienvenue ${u.user.displayName}`,
                    detail:'Vous pouvez commander vos films et plats 😁!'});
                this.sendServeur();
            },
            (error) => {
                this.errorMessage = error;
                this.message.add({severity:'error',
                    summary:'Erreur de connexion',
                    detail:'Une erreur est survenue l\'ors de la connexion !'});
            }
        );
    }

    /**
     * login with Facebook acompt
     */

        // Sign in with Facebook

    loginFacebook(){
             this.afAuth.auth.signInWithPopup(new auth.FacebookAuthProvider());
        }

        // Auth logic to run auth providers
        AuthLogin(provider) {
            return this.afAuth.auth.signInWithPopup(provider)
                .then((result) => {
                    console.log('You have been successfully logged in!')
                }).catch((error) => {
                    console.log(error)
                })
        }



    /**
     * envoie les information du client au serveur il s'atend a recevoir (id, nom, prenom)
     */
    sendServeur(){
        this.afAuth.user.subscribe(utilisateur =>{
            let i = utilisateur.displayName.indexOf(" "); // couper en 2 displayname pour avoir le prenom et le nom
            if (utilisateur.uid){
                console.log('heyyyy')
                console.log(utilisateur.uid)
                this.authService.authentificate({
                    // variable que le serveur s'attend a recevoir
                    
                    idClient: utilisateur.uid,
                    nom: utilisateur.displayName.substr(0,i),
                    prenom: utilisateur.displayName.substr(i),
                    email: utilisateur.email,
                    photo: utilisateur.photoURL,
                }).then(data =>{console.log('heyyyy')
                });
            }
        });
    }
    // formGroup pour la connexion avec email et le passwod
    initForm() {
        this.signInForm = this.formBuilder.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.pattern(/[0-9a-zA-Z]{6,}/)]]
        });
    }
    /**
     * methode de connexion avec l'email et le mot de pass
     */
    onSubmit() {
        const email = this.signInForm.get('email').value;
        const password = this.signInForm.get('password').value;
        this.afAuth.auth.signInWithEmailAndPassword(email,password).then(
            u => {
                this.route.navigate(['/']);
                this.message.add({severity:'success',
                    summary:`Bienvenue `,
                    detail:'Vous pouvez commander vos films et plats 😁!'});
                this.sendServeur();
            },
            (error) => {
                this.errorMessage = error;
                this.message.add({severity:'error',
                    summary:'Erreur de connexion',
                    detail:'Une erreur est survenue l\'ors de la connexion !'});
            }
        );
    }
}
