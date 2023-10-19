public class Vertex {
    private String id;
    private String name;

    Vertex(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getID(){ return id;}

    public String getName(){ return name;}

    @Override
    public String toString(){
        return name;
    }
}
 