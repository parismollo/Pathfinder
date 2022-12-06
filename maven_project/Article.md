# Trinôme
identifiant : G2S1
Paris Felipe Mollo Christondis
Leopold Abignoli
Daniel Gilardoni

# Projet MD5
Projet Chemin, sujet 1

Intro qui explique les bails genre une grille dans nôtre projet c'est rpz par un graph (planaire)...

1. **Longueur et nombre de chemins de longueurs minimales dans une grille** 

La taille d'un chemin dans notre projet est le nombre de deplacement effectué d'un sommet à un de ses voisins, ou le nombre de cases par lequel on doit passer pour aller d'une case à une autre de la grille.
Un sommet qui a pour coordonnée (x,y) correspond à la case de hauteur y et de longueur x.

A) Longueur du chemin minimal

Un chemin minimal dans une grille de longueur l et de hauteur h, sans murs, est de longueur :
|x1 - x2| + |y1 - y2| , avec (x1,y1) les coordonnées du point de départ et (x2,y2) celles du point d'arrivée.
(La case en bas à gauche est en (0,0) et celle en haut à droite est en (l,h))
<!-- |x1 - x2| est la valeur absolue de x1 - x2 -->
On se deplace de |x1 - x2| cases en lignes puis de |y1 - y2| en colonne, depuis le point de depart pour atteindre le point d'arrivée.

B) Nombres de chemins de longueur minimale

Si le point d'arrivée et de depart sont sur la même ligne (y1 = y2) ou la même colonne (x1 = x2) alors il n'y a qu'un chemin de longueur minimale.
Si ce n'est pas le cas (x1 != x2 et y1 != y2) alors :
<!-- 2 si |x1 - x2| + |y1 - y2| = 2 (en sachant que x1 != x2 et y1 != y2 comme dit au-dessus donc pas 2 d'ecart entre x1 et x2 par ex) // si longueur 2 -->
<!-- si lg = 3 : nb = 3 -->
<!-- si lg = 4 : nb = 4 ou 6 -->
<!-- si lg = 5 : nb = 5 ou 10 -->
<!-- si lg = 6 : nb = 6 ou 15 ou 20 -->
On fait |x1 - x2| deplacements en abcisse et |y1 - y2| en ordonnées.
Choisir un chemin de taille minimale c'est choisir |x1 - x2| déplacements en abcisse et |y1 - y2| deplacements en ordonnée dans l'ordre que l'on veut.
Cela revient à compter le nombre de mots de taille |x1 - x2| + |y1 - y2| composés de |x1 - x2| lettre x et de |y1 - y2| lettre y différents. Ce qui se calcule à l'aide du coeficient binomial.
Le nombre de chemins de taille minimale possible est donc :
(|x1 - x2| + |y1 - y2|)! / ((|x1 - x2|)! * (|y1 - y2|)!)
ou
(|x1 - x2| + |y1 - y2|    |y1 - y2|) coef binomial 
(|x1 - x2| + |y1 - y2|    |x1 - x2|) coef binomial equivalent
Car c'est le nombre de deplacement en abcisse possible parmis le nombre de deplacements possibles en gros.
Si t'as longueur 6 et 3 deplacements en abcisse à faire alors ça revient à faire un 3-uplets de 3 deplacements en abcisses sans repetitions, l'ordre compte. A comprendre mieux.


2. **Borne maximale du nombre de chemins dans une grille**

Dans une grille sans mur de longueur l et hauteur h, la taille maximale d'un chemin est l+h. 
C'est le chemin minimal entre le point de depart de coordonnée (0,0), et le point d'arrivée de coordonnée (h,l) par exemple.
Le nombre de chemins de taille minimale entre ces 2 points est la borne maximale du nombre de chemin dans une grille.
En effet le nombre de chemins entre ces 2 points, d'après les conclusions de la partie précédente, est :
(l+h)! / (h! + l!)
ce qui est supérieur à un chemin qui aurait moins de deplacements en abcisses ou en ordonnées (demontrer ?) et il ne peut pas y avoir plus de deplacements en abcisse que l, et que h en ordonnée.

Grand O ?

3. **L'algorithme de parcours en largeur**
### Type
* (BFS) est un algorithme de recherche qui permet parcourir un arbre ou un graphe.
### Input
* Entrées : graphe G = (V, E) et sommet s ∈ V, où V represente les sommets du graphe G et E represente les aretes (u, v) de G.
### Complexité
* O(V + E) pour les listes d'adjacence
* O(V^2) when matrice d''adjacence 

### Pseudo code
```
début
    créer ﬁle(Q);
    marquer(départ);
    enﬁler(Q, départ);
    tant que Q != ∅ faire
        u ← déﬁler(Q);
        pour tous les uv ∈ E faire
            si v non marqué alors
                marquer(v);
                enﬁler(Q, v);
```

### Description

L'algorithme BFS (Breadth-first search) est un algorithme de recherche. Il réalise sa recherche à travers un parcours transversal d'un graphe, ce qui signifie qu'on visite tous les sommets d'un même niveau avant de passer à un autre. 

Afin de visiter tous les sommets d'une graphe. BFS catégorise chaque sommet en deux types - visité et non visité. À partir d'un noeud choisi, BFS visite tous les noeuds adjacents au noeud sélectionné et ainsi de suite. 

### Déroulement de l'algorithme
___

#### Étape 1:
- Déclarer une **file d'attente** (*FIFO*) et insérer le sommet de départ.
- Initialiser un **tableau de marquage** (*tableau de booléan*) et marquer le sommet de départ comme visité.

#### Étape 2
Suivre le processus ci-dessous jusqu'à ce que la file d'attente soit vide :
- Supprimer le premier sommet de la file d'attente.
- Marquer ce sommet comme visité.
- Insérer tous les voisins non visités du sommet dans la file d'attente.

4. **Programme calculant un des plus court chemin dans une grille** 

*Entrées* : Graphe orienté G = (S, A) et un sommet s ∈ S
*Sorties* : Distance de s aux autres sommets
```
Q ← ∅
distance[s] ← 0
precedent[s] ← s
Pour tous les u ∈ A \ {s} faire
    distances[u] ← +∞
Tant que Q != S faire
    Trouver u ∈ S \ Q tel que distances[u] est minimum
    Q ← Q ∪ {u}
    Pour tous les v ∈ S \ Q tels que (u, v) ∈ A faire
        precedent[v] ← 
        distances[v] ← min(distances[v], distances[u] + 1)
retourner precedents
```
Le chemin le plus court entre le sommet s et un sommet arrivée est 

1. **Complexité de l'algorithme calculant le plus court chemin**
