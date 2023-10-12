class Movie extends Vertex{
    double rating;

    Movie(String id, String name, double rating) {
        super(id, name);
        this.rating = rating;
    }

    public Double getRating(){return rating;}
}