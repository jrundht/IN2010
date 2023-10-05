
class Node{
    Node left = null;
    Node right = null;
    int element = 0;
    int height = 1;
    
    Node(int x){
        element = x;
    }
}
public class BinarySearchTree {
    protected Node root = null;
    protected int size = -1;

    public int size(){
        return size;
    }
    
    public Node insert(Node v, int x){ //dont allow duplicates
        if(root == null){
            root = new Node(x);
            size = 1;
            return root;
        }
        if(x == v.element) return v;
        if(x < v.element){ //left
            if(v.left != null){
                v.left = insert(v.left, x);
            }else{
                v.left = new Node(x);
                size++;
            }
            
        }else if (x > v.element){  //if x == v.element -> dont insert
    
            if(v.right != null){
                v.right = insert(v.right, x);
            }else{
                v.right = new Node(x);
                size++;
            }
        }
        return v;
    }

    public boolean  contains(Node v, int x){
        if(x < v.element){
            if(v.left == null){
                return false;
            }else{
                return contains(v.left, x);
            }
        }else if(x > v.element){
            if(v.right == null){
                return false;
            }else{
                return contains(v.right, x);
            }
        }else{
            return true;
        }
    }

    public Node findMin(Node v){
        if(v.left != null){
            return findMin(v.left);
        }
        return v;
    }
    
    private Node delete(Node v, int x){
        if (v == null) return null;
    
        if (x < v.element) {
            v.left = remove(v.left, x);
        } else if (x > v.element) {
            v.right = remove(v.right, x);
        } else { // Node to be deleted is found

            // Node with only one child or no child
            if (v.left == null) {
                size--;
                return v.right;
            } else if (v.right == null) {
                size--;
                return v.left;
            }
            // Node with two children
            Node u = findMin(v.right);
            v.element = u.element;
            
            // Delete the successor
            v.right = remove(v.right, u.element);
        }
        return v;
    }

    //bug with removing the root when trying to do this with only one method
    public Node remove(Node v, int x) {  
        root = delete(v, x);
        return root;
    }

    public void printTree(Node node, String prefix, String childrenPrefix) {
        if (node != null) {
            System.out.println(prefix + node.element);
            printTree(node.left, childrenPrefix + "├── ", childrenPrefix + "│   ");
            printTree(node.right, childrenPrefix + "└── ", childrenPrefix + "    ");
        }
    }

    public void printTree() {
        printTree(root, "", "");
    }
    public void inOrderTraversal(Node v){
        if(v.left != null) inOrderTraversal(v.left);
        System.out.println(v.element);
        if(v.right != null) inOrderTraversal(v.right);
    }
}
