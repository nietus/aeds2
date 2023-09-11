class Ponto {
    private double x;
    private double y;
    private int id;
    private static int nextID = 0;

    public Ponto() {
        this.x = 0.0;
        this.y = 0.0;
        this.id = nextID;
        nextID++;
    }

    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
        this.id = nextID;
        nextID++;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getID() {
        return id;
    }
    
    public static int getNextID() {
        return nextID;
    }

    public double dist(Ponto p) {
        double dx = this.x - p.getX();
        double dy = this.y - p.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double dist(double x, double y) {
        double dx = this.x - x;
        double dy = this.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double dist(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static boolean isTriangulo(Ponto p1, Ponto p2, Ponto p3) {
        double distP1P2 = p1.dist(p2);
        double distP2P3 = p2.dist(p3);
        double distP3P1 = p3.dist(p1);
        return (distP1P2 + distP2P3 > distP3P1) && (distP2P3 + distP3P1 > distP1P2) && (distP3P1 + distP1P2 > distP2P3);
    }

    public double getAreaRetangulo(Ponto p) {
        double width = Math.abs(this.x - p.getX());
        double height = Math.abs(this.y - p.getY());
        return width * height;
    }
}