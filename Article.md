# Projet Chemin

- [Projet Chemin](#projet-chemin)
  - [Equipe](#equipe)
  - [Introduction](#introduction)
  - [Questions](#questions)
    - [1. Longueur et nombre de chemins de longueurs minimales dans une grille](#1-longueur-et-nombre-de-chemins-de-longueurs-minimales-dans-une-grille)
      - [A) Longueur du chemin minimal](#a-longueur-du-chemin-minimal)
      - [B) Nombres de chemins de longueur minimale](#b-nombres-de-chemins-de-longueur-minimale)
    - [2. Borne maximale du nombre de chemins dans une grille](#2-borne-maximale-du-nombre-de-chemins-dans-une-grille)
    - [3. L'algorithme de parcours en largeur](#3-lalgorithme-de-parcours-en-largeur)
      - [Type](#type)
      - [Input](#input)
      - [Complexité](#complexité)
      - [Pseudo code](#pseudo-code)
      - [Description](#description)
      - [Déroulement de l'algorithme](#déroulement-de-lalgorithme)
    - [4. Programme calculant un des plus court chemin dans une grille](#4-programme-calculant-un-des-plus-court-chemin-dans-une-grille)
    - [5. Complexité de l'algorithme calculant le plus court chemin](#5-complexité-de-lalgorithme-calculant-le-plus-court-chemin)


## Equipe
**identifiant** : G2S1 
* Daniel Gilardoni      
* Felipe Paris Mollo Christondis      
* Leopold Abignoli        


## Introduction

* todo
<!-- [TODO] -->

## Questions
### 1. Longueur et nombre de chemins de longueurs minimales dans une grille

La taille d'un chemin dans notre projet est le nombre de deplacement effectué d'un sommet à un de ses voisins, ou le nombre de cases par lequel on doit passer pour aller d'une case à une autre de la grille.
Un sommet qui a pour coordonnée $(x,y)$ correspond à la case de hauteur $y$ et de longueur $x$.


#### A) Longueur du chemin minimal

Un chemin minimal dans une grille de longueur l et de hauteur h, sans murs, est de longueur :
$|x_1 - x_2| + |y_1 - y_2|$ , avec $(x_1,y_1)$ les coordonnées du point de départ et $(x_2,y_2)$ celles du point d'arrivée.
(La case en bas à gauche est en (0,0) et celle en haut à droite est en (l,h))
<!-- $|x_1 - x_2|$ est la valeur absolue de $x_1 - x_2$ -->
On se deplace de $|x_1 - x_2|$ cases en lignes puis de $|y_1 - y_2|$ en colonne, depuis le point de depart pour atteindre le point d'arrivée.

#### B) Nombres de chemins de longueur minimale

Si le point d'arrivée et de depart sont sur la même ligne $(y_1 = y_2)$ ou la même colonne $(x_1 = x_2)$ alors il n'y a qu'un chemin de longueur minimale.
Si ce n'est pas le cas $(x_1 \ne x_2 et y_1 \ne y_2)$ alors :
<!-- 2 si |x1 - x2| + |y1 - y2| = 2 (en sachant que x1 != x2 et y1 != y2 comme dit au-dessus donc pas 2 d'ecart entre x1 et x2 par ex) // si longueur 2 -->
<!-- si lg = 3 : nb = 3 -->
<!-- si lg = 4 : nb = 4 ou 6 -->
<!-- si lg = 5 : nb = 5 ou 10 -->
<!-- si lg = 6 : nb = 6 ou 15 ou 20 -->

On fait $|x_1 - x_2|$ deplacements en abcisse et $|y_1 - y_2|$ en ordonnées.
Choisir un chemin de taille minimale c'est choisir $|x_1 - x_2|$ déplacements en abcisse et $|y_1 - y_2|$ deplacements en ordonnée dans l'ordre que l'on veut.
Cela revient à compter le nombre de mots de taille $|x_1 - x_2| + |y_1 - y_2|$ composés de $|x_1 - x_2|$ lettre $x$ et de $|y_1 - y_2|$ lettre $y$ différents. Ce qui se calcule à l'aide du coeficient binomial.

Le nombre de chemins de taille minimale possible est donc :  

$$|x_1 - x_2| + |y_1 - y_2|! \over |x_1 - x_2|! \times |y_1 - y_2|!$$

ou

$$\binom{|x_1 - x_2| + |y_1 - y_2|}{|y_1 - y_2|}$$
<!-- coef binomial  -->
ou
$$ \binom{|x_1 - x_2| + |y_1 - y_2|}{|x_1 - x_2|}$$
<!-- coef binomial equivalent -->

Car c'est le nombre de deplacement en abcisse possible parmis le nombre de deplacements possibles en gros.
Si t'as longueur 6 et 3 deplacements en abcisse à faire alors ça revient à faire un 3-uplets de 3 deplacements en abcisses sans repetitions, l'ordre compte. A comprendre mieux.

---

### 2. Borne maximale du nombre de chemins dans une grille

Dans une grille sans mur de longueur l et hauteur h, et lorsque l'on se deplace uniquement en se rapprochant du point d'arrivée, c'est à dire en diminuant $|x_1 - x_2| ou |y_1 - y_2|$ de 1 à chaque deplacement, la taille maximale d'un chemin est $l+h$

C'est le chemin minimal entre le point de depart de coordonnée $(0,0)$, et le point d'arrivée de coordonnée $(h,l)$ par exemple.
Le nombre de chemins de taille minimale entre ces 2 points est donc la borne maximale du nombre de chemin dans une grille.
En effet le nombre de chemins entre ces 2 points, d'après les conclusions de la partie précédente, est:

$$(l+h)! \over (h! + l!)$$

Ce qui est supérieur à un chemin qui aurait moins de deplacements en abcisses ou en ordonnées et il ne peut pas y avoir plus de deplacements en abcisse que l, et que h en ordonnée.

---

### 3. L'algorithme de parcours en largeur
#### Type
* (BFS) est un algorithme de recherche qui permet de parcourir un arbre ou un graphe.
#### Input
* Entrées : graphe $G = (V, E)$ et sommet $s ∈ V$, où $V$ represente les sommets du graphe $G et E$ represente les aretes $(u, v)$ de $G$.
#### Complexité
* $O(V + E)$ pour les listes d'adjacence
* $O(V^2)$ when matrice d''adjacence 
#### Pseudo code
```
début
    créer ﬁle(Q);
    marquer(départ);
    enﬁler(Q, départ);
    Tant que Q != ∅ faire
        u ← déﬁler(Q);
        Pour tous les u,v ∈ E faire
            si v non marqué alors
                marquer(v);
                enﬁler(Q, v);
```

#### Description

L'algorithme BFS (Breadth-first search) est un algorithme de recherche. Il réalise sa recherche à travers un parcours transversal d'un graphe, ce qui signifie qu'on visite tous les sommets d'un même niveau avant de passer à un autre. 

Afin de visiter tous les sommets d'une graphe. BFS catégorise chaque sommet en deux types - visité et non visité. À partir d'un noeud choisi, BFS visite tous les noeuds adjacents au noeud sélectionné et ainsi de suite. 

#### Déroulement de l'algorithme
___

**Étape 1:**
- Déclarer une **file d'attente** (*FIFO*) et insérer le sommet de départ.
- Initialiser un **tableau de marquage** (*tableau de booléan*) et marquer le sommet de départ comme visité.

**Étape 2**
Suivre le processus ci-dessous jusqu'à ce que la file d'attente soit vide :
- Supprimer le premier sommet de la file d'attente.
- Marquer ce sommet comme visité.
- Insérer tous les voisins non visités du sommet dans la file d'attente.

### 4. Programme calculant un des plus court chemin dans une grille

*Entrées* : Graphe orienté G = (S, A) et un sommet s ∈ S, la source, et un sommet t ∈ S, l'arrivée.
*Sorties* : Distance de s aux autres sommets
```

créer filePriorité(pq);
enfiler(pq, (s, 0));

Pour tous i de 0 à taille(distances) faire
    distances(i) ←  +∞;
    previous(i)  ←  null;

previous(s) ← None;
distances(s) ← 0;

Tant que pq != ∅ faire
   u ← defiler(pq);

   Si u = t:
      Fin;
      
   Pour tous les (u, v) ∈ E faire:
      dist ← distances(u) + 1
      Si dist < distances(v) alors
         distances(v) ← dist
         priorite ← dist + heuristic(t, v)
         enfiler(pq, (v, priorite))
         prev(v) ← u
```
Le chemin le plus court entre le sommet s et un sommet arrivée est donné par  

### 5. Complexité de l'algorithme calculant le plus court chemin
Soit un graphe $G = (V,E)$. Dans le pire des cas, l'algorithme va visiter tous les sommets, soit $O(V)$. A chaque tour de boucle, on récupére le sommet avec la priorité la plus faible, ce qui ici se fait en $O(log(V))$ avec une file de priorité. On compare la distance du sommet courant avec la distance de chacun de ses voisins dans le tableau distances. Chaque comparaison se fait en temps constant. Si la distance du voisin est la plus grande alors on va mettre à jour avec la distance du sommet courant plus un, et ajouter ce sommet à la file de priorité. Il n'y a pas de poids et avec la file de priorité on regarde les sommets dans l'ordre de leur distance à la source, donc on ne va jamais modifié la distance à la source d'un sommet dont la distance n'est pas +infinie et donc on ne va jamais ajouté 2 fois un sommet à la file de priorité. Dans le pire cas on a donc O(V) tours de boucles, donc O(V) ajout/suppression dans la file qui se font en O(log(V)), et 2*E soit O(E) operations constantes (comparaison avec voisins) car on regarde chaque voisins pour chaque sommets ce qui est egale au nombre d'arêtes *2.

val retour Q4 comme dans grille avec poids 1 y a pas O(E) mais O(V) vu qu'on va pas repasser par sommets deja mis dans file. Donc V * logV + E On se sert de grille pour calc naif, heuristic...

On visite chaque noeud une fois O(|V|) et ensuite "relax" chaque voisin O(|E|), à chaque fois il doit acceder à file de priorité O(log V)

Donc la complexité est de O(V + E * log(V))