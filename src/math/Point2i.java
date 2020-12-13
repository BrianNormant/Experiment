package math;

public class Point2i {
    private int x;
    private int y;

    /**
     * Constructeur sans paramètre.
     * Initialise x et y à 0.
     * Est appelé au moment de la création de l'objet (opérateur new)
     */
    public Point2i(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructeur avec paramètres qui initialise le point à (abs, ord).
     * @param abs la valeur à donner à x
     * @param ord la valeur à donner à y
     */
    public Point2i(int abs, int ord) {
        this.x = abs;
        this.y = ord;
    }

    /**
     * Méthode d'altération pour x
     * @param abs la valeur à donner à x
     */
    public void setX(int abs) {
        this.x = abs;
    }

    /**
     * Méthode d'altération de y
     * @param ord la valeur à donner à y
     */
    public void setY(int ord) {
        this.y = ord;
    }

    /**
     * Méthode d'accès à x
     * @return la valeur de x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Méthode d'accès à y
     * @return la valeur de y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Affiche le point sous la forme "(X,Y)"
     */
    public void afficher() {
        System.out.println("(" + this.x + "," + this.y + ")");
    }

    /**
     * Renvoie la distance a l'origine du point
     * Utilise la formule du théorème de pythagore
     * @return distance à l'origine
     */
    public double disOrigin() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Deplace le point dans le quadrant approprié, la numérotation des quadrants utilisés est:<br/>
     * 2 | 1 <br/>
     * __|__ <br/>
     * 3 | 4
     */
    public void quadrant1() {
        if (this.x < 0) this.x = -this.x;
        if (this.y < 0) this.y = -this.y;
    }
    public void quadrant2() {
        if (this.x >= 0) this.x = -this.x;
        if (this.y < 0) this.y = -this.y;
    }
    public void quadrant3() {
        if (this.x >= 0) this.x = -this.x;
        if (this.y >= 0) this.y = -this.y;
    }
    public void quadrant4() {
        if (this.x < 0) this.x = -this.x;
        if (this.y >= 0) this.y = -this.y;
    }

    /**
     * Méthode utilisée pour le débogage.
     * @return une chaine représentant le point sous la forme "(X,Y)"
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    /**
     * Permet la comparaison d'objets de classe Point.
     * @param p le point avec lequel faire la comparaison
     * @return true si l'objet courant est égal à p.
     */
    public boolean equals(Point2i p) {
        return this.x == p.getX() && this.y == p.getY();
    }
}
