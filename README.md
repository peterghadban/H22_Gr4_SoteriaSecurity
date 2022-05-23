# Soteria Security

Ce projet est un système d'alarme qui détecte s'il y a du mouvement innatendu dans une chambre.



## Introduction 
Ce programme fonctionne avec plusieurs éléments. En effet, le code utilise un Raspberry Pi, Fireabase et Android Studios. Pour résumer le fonctionnement du code, le Raspberry Pi envoie un signal au Firebase qui va ensuite être lu par l'application Android Studios. L'application Android Studios va ensuite envoyer une notification au téléphone pour aviser la personne qu'il y a eu du mouvement dans sa chambre.

## Raspberry Pi

Le Raspberry Pi est l'élément qui s'occupe de la récolte de données. Le Raspberry Pi est connecté à un détecteur à rayon infrarouge. À chaque fois qu'il y a du mouvement, le détecteur PIR le détecte et avertit le Raspberry Pi. Une fois averti, le Raspberry Pi va envoyer la date et l'heure à la base de donnée sur Firebase. Tout sur le Raspberri Pi est codé en Python.

![MicrosoftTeams-image (1)](https://user-images.githubusercontent.com/98619144/168315510-8f5d3f35-7d56-4cd8-8c10-478d0bb0ce41.png)

Dans cette image on voit deux affaires: le Raspberry Pi et le détecteur PIR de rayons infrarouges. Pour connecter le Raspberry Pi et le détecteur, on connecte ce dernier au bouts de métals sur la surface du Raspberry Pi. Ces bouts de métals permettent de lire les données et c'est grâce à ça que le Raspberry Pi peut lire ce que le détecteur émet. Le USB qui est connecté dans le Raspberry Pi est pour contrôler ce dernier avec une souris.

## Firebase
La base de donné Firebase stock les données envoyées par le Raspberry Pi. Des données qui vont être ensuite lues par l'application Android Studios.

![MicrosoftTeams-image](https://user-images.githubusercontent.com/98619144/168314403-20c77562-2be6-428f-9757-96095699e8e4.png)

Cette image montre les données dans la base de données. On peut voir qu'en premier il y a la date du détectement et après il y a l'heure précise aussi. On peut aussi voir que ces données sont placées dans une sous catégorie qui s'appelle "motion". C'est de cette catégorie qu'on va récolter toutes les fois où du mouvement a été détecté par le détecteur de mouvement

## Android Studios
L'application Android Studios est l'élément du projet qui s'occupe de communiquer avec l'utilisateur. Cette application récolte les données du Firebase et va ensuite les traiter. L'application contient aussi d'autre fonction par exemple un graphique qui trie tous les mouvements selon l'heure de la journée où il sont été détectés.

![MicrosoftTeams-image](https://user-images.githubusercontent.com/98619144/168446812-7803dc3d-a150-4e37-9cbe-a5ed3fc650f9.png)

Ça c'est la partie historique de l'application. À chaque fois qu'il y a une nouvelle donnée qui est ajoutée dans la base de donnée, l'application va aller chercher cette donnée et la placer dans la partie historique de l'application.

![MicrosoftTeams-image (1)](https://user-images.githubusercontent.com/98619144/168447355-3fc7a9b4-65c6-48e6-842b-edb3501a020d.png)

Ceci est la partie login de l'application. Si l'utilisateur a déjà un compte, il peut directement login dans son compte. Une fois qu'il accèede son compte, toutes ses données vont apparaître. Si l'utilisateur n'a pas de compte, il peut se créer un nouveau compte. L'utilisation de l'application est très facile.

![MicrosoftTeams-image (2)](https://user-images.githubusercontent.com/98619144/168453195-692d7aeb-3b13-48cd-8d18-5572c3244607.png)

Ça c'est la page de graphique de l'application. Cette page prend en compte chaque entrée de données et les répartit selon les sections d'heures. Il fait ensuite un graphique à l'aide de ces données pour voir dans quelle section d'heure il y a le plus de mouvement. Il y a quatre sections: 12-5 AM, 6-11 AM, 12-5 PM, 6-11 PM. 

<img width="374" alt="MicrosoftTeams-image (5)" src="https://user-images.githubusercontent.com/98619144/168454240-aa2057d0-589c-413a-93bb-f08aca91c4d8.png">

Cette page appartient au main menu de l'application, c'est la page qui s'ouvre en premier dès que l'utilisateur ouvre l'application. Le menu principal présente cinq choix à l'utilisateur. Le premier choix est "Activity" qui amène à la page de l'historique des données. Le deuxième choix est "Charts" qui amène à la page de graphique. Le troisième choix est "Account" qui amène à la page de l'information de l'utilisateur. Le quatrième est la page "About" qui donne de l'information sur l'application et le fonctionnement du code. Le dernier choix est le boutton "LogOut" qui permet à l'utilisateur de se déconnecter de son compte.

<img width="376" alt="MicrosoftTeams-image (6)" src="https://user-images.githubusercontent.com/98619144/168454256-f449c42b-eb69-43f6-be36-253a44fb872c.png">

Voici la page "About". Il y a tous les réponses aux questions que l'utilisateur pourrait se poser.

<img width="389" alt="MicrosoftTeams-image (7)" src="https://user-images.githubusercontent.com/98619144/168454289-d694b02f-4d12-4c46-9125-d872e5dcf708.png">

Voici la page "Account". Cette page contient trois informations. La première information est le id de l'utilisateur qui est utile au créateur de l'application si jamais l'utilisateur à besoin d'aide avec son compte. La deuxième information est le email de l'utilisateur et la troisième information est l'option qui permet à l'utilisateur de changer son mot de passe.

![MicrosoftTeams-image (8)](https://user-images.githubusercontent.com/98619144/168454342-9e35d5ff-06a8-4a87-8863-6a2f3d0140be.png)

Voici le logo de notre application.
