public class AVLTree extends BinarySearchTree{
    
    
    public int height(Node v){
        if(v == null) return -1;
        return v.height;
    }

    public void setHeight(Node v){
        if(v != null){
            int vLeft = height(v.left) == -1 ? 0 : v.left.height;
            int vRight = height(v.right) == -1 ? 0 : v.right.height;
            v.height = Math.max(vLeft, vRight) + 1;
        }
    }
    
    @Override
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
        setHeight(v);
        Node balanced = balance(v);
        if(v == root){
            root = balanced;
        }
        return balanced;
    }

    public Node leftRotate(Node z){
        Node y = z.right;
        Node t = y.left;
        y.left = z;
        z.right = t;
        setHeight(z);
        setHeight(y);
        return y;
    }
    public Node rightRotate(Node z){
        Node y = z.left;
        Node t = y.right;
        y.right = z;
        z.left = t;
        setHeight(z);
        setHeight(y);
        return y;
    }

    public int balanceFactor(Node v){
        if(v == null) return 0;
        return height(v.left) - height(v.right);
    }

    public Node balance(Node v){

        if(balanceFactor(v) < -1){
            if(balanceFactor(v.right) > 0){
                v.right = rightRotate(v.right);
            }

            return leftRotate(v);
        }
        if (balanceFactor(v) > 1){
            if(balanceFactor(v.left) < 0){
                v.left = leftRotate(v.left);
            }

            return rightRotate(v);
        }

        return v;
    }

    private Node delete(Node v, int x){
        if (v == null) return null;
    
        if (x < v.element) {
            v.left = delete(v.left, x);
        } else if (x > v.element) {
            v.right = delete(v.right, x);
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
            v.right = delete(v.right, u.element);
        }
        setHeight(v);
        return balance(v);
    }

    //bug with removing the root when trying to do this with only one method
    @Override
    public Node remove(Node v, int x) {  
        root = delete(v, x);
        if(v == root){
            balance(root);
        }
        return root;
    }
}
