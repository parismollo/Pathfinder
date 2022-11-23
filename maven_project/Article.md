# Trinôme
identifiant : G2S1
Paris Felipe Mollo Christondis
Leopold Abignoli
Daniel <!-- Le giga crack mec tu coco --> Gilardoni

# Projet MD5
Projet Chemin, sujet 1

Intro qui explique les bails genre une grille dans nôtre projet c'est rpz par un graph (planaire)...

1. **Longueur et nombre de chemins de longueurs minimales dans une grille** 

Qu'est-ce que la taille d'un chemin dans notre projet ? C'est le nombre de deplacement effectué d'un sommet à un autre, d'une case à une autre (côte à côte) pour aller d'un point à un autre

A) Longueur du chemin minimal

Qu'est ce que x et y dans un graph ?
Est-ce qu'on a x et y dans les graphes de notre projet ?

Un chemin minimal dans une grille de longueur l et de hauteur h, sans murs, est de longueur :
|x1 - x2| + |y1 - y2| , avec (x1,y1) les coordonnées du point de départ et (x2,y2) celles du point d'arrivée.
<!-- |x1 - x2| est la valeur absolue de x1 - x2 -->
On se deplace de |x1 - x2| cases en lignes puis de |y1 - y2| en colonne, depuis le point de depart pour atteindre le point d'arrivée.

B) Nombres de chemins de longueur minimale

0 si pt arrivée = pt depart (x1 = x2 et y1 = y2)    // si longueur 0
1 si ils sont sur même ligne ou même colone (x1 = x2 ou y1 = y2) // et longueur |x1 - x2| ou |y1 - y2|
Si x1 != x2 et y1 != y2 alors :
2 si |x1 - x2| + |y1 - y2| = 2 (en sachant que x1 != x2 et y1 != y2 comme dit au-dessus donc pas 2 d'ecart entre x1 et x2 par ex) // si longueur 2
si lg = 3 : nb = 3
si lg = 4 : nb = 4 ou 6
si lg = 5 : nb = 5 ou 10
si lg = 6 : nb = 6 ou 15 ou 20

On fait |x1 - x2| deplacements en abcisse et |y1 - y2| en ordonnées.
Le nombre de chemins de taille minimale possible est :
(|x1 - x2| + |y1 - y2|)! / ((|x1 - x2|)! * (|y1 - y2|)!)
ou
(|x1 - x2| + |y1 - y2|    |y1 - y2|) coef binomial 
(|x1 - x2| + |y1 - y2|    |x1 - x2|) coef binomial equivalent
Car c'est le nombre de deplacement en abcisse possible parmis le nombre de deplacements possibles en gros.
Si t'as longueur 6 et 3 deplacements en abcisse à faire alors ça revient à faire un 3-uplets de 3 deplacements en abcisses sans repetitions, l'ordre compte. A comprendre mieux.


