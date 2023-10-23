import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class View {
    private JFrame frame;
    private JTextField actor1Field;
    private JTextField actor2Field;
    private JTextArea resultArea;
    private JButton findPathButton;
    private JButton showComponentsButton;
    private GraphBuilder graph;


    public View(GraphBuilder graph) {
        this.graph = graph;

        // Set up main frame
        frame = new JFrame("IMDb - GRAPH");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        // Panel for input
        JPanel inputPanel = new JPanel();
        actor1Field = new JTextField(15);
        actor2Field = new JTextField(15);
        findPathButton = new JButton("Find Paths");
        showComponentsButton = new JButton("Show num Components");
        
        inputPanel.add(new JLabel("Actor 1:"));
        inputPanel.add(actor1Field);
        inputPanel.add(new JLabel("Actor 2:"));
        inputPanel.add(actor2Field);
        inputPanel.add(findPathButton);
        inputPanel.add(showComponentsButton);

        // Result area for output
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea); //to save prior searches 

        content.add(inputPanel, BorderLayout.SOUTH);
        content.add(scrollPane, BorderLayout.CENTER);

        findPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findAndDisplayPaths();
            }
        });

        showComponentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayComponents();
            }
        });

        frame.setVisible(true);
    }

    private void findAndDisplayPaths() {
        Actor actor1 = graph.findActor(actor1Field.getText().trim());
        Actor actor2 = graph.findActor(actor2Field.getText().trim());
        String existingText = resultArea.getText();

        if(actor1 == null){
            resultArea.setText(existingText + "\n" + actor1Field.getText().trim() + " is not in the dataset");
            return;
        }
        if(actor2 == null){
            resultArea.setText(existingText + "\n" + actor2Field.getText().trim() + " is not in the dataset");
            return;
        }

        ArrayList<Vertex> shortestPath = Paths.bfsShortestPath(graph.graph, actor1, actor2);
        ArrayList<Vertex> chillestPath = Paths.chillestPathFrom(graph.graph, actor1, actor2);

        StringBuilder output = new StringBuilder();

        output.append("Shortest Path:\n");
        output.append(actor1 + "\n");
        for(int i = 1; i < shortestPath.size()-1; i ++){
            output.append("===[ " + shortestPath.get(i) + " ] ===> " + shortestPath.get(++i) + "\n");
            
        }

        output.append("\nMost Enjoyable Path:\n");
        output.append(actor1 + "\n");
        for(int i = 1; i < chillestPath.size()-1; i ++){
            output.append("===[ " + chillestPath.get(i) + " ] ===> " + chillestPath.get(++i) + "\n");
            
        }
        actor1Field.setText("");
        actor2Field.setText("");
        resultArea.setText(existingText + "\n" + output.toString());
    }
    
    private void displayComponents() {
        HashMap<Integer, Integer> components = Paths.components(graph.graph);
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(components);

        StringBuilder output = new StringBuilder();
        for(int i : sortedMap.keySet()){
            output.append("There are " + sortedMap.get(i) + " components of size " + i + "\n");
        }
        String existingText = resultArea.getText();//Keep existing text
        resultArea.setText(existingText + "\n" + output.toString());
    }

    public static void main(String[] args) {
        GraphBuilder graph = new GraphBuilder();

        ArrayList<String> movies = FileReadr.readFile("movies.tsv"); //O(N)
        graph.addMovies(movies);

        ArrayList<String> actors = FileReadr.readFile("actors.tsv"); //O(N)
        graph.addActors(actors);

        // Start GUI
        new View(graph);
    }
}