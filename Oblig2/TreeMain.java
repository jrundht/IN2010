import java.util.Scanner;
class TreeMain {
    
    /*
     * To run the AVL version all you need to do is comment out the BST
     * and "uncomment" AVLTree
     *
    */
    public static void main(String[] args){
        BinarySearchTree tree = new BinarySearchTree(); 
        // AVLTree tree = new AVLTree();

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int x = 0;
        while(N != -1){
            String inp = scanner.nextLine();
            String[] lst = inp.split(" ");
            String s = lst[0];
            
            if(s.equals("insert")){
                x = Integer.parseInt(lst[1]);
                tree.insert(tree.root, x);

            }else if(s.equals("contains")){
                x = Integer.parseInt(lst[1]);
                System.out.println(tree.contains(tree.root, x));

            }else if(s.equals("remove")){
                x = Integer.parseInt(lst[1]);
                tree.remove(tree.root, x);

            }else if(inp.equals("size")){ 
                System.out.println(tree.size());
            }
            N--;
        }
        // System.out.println("\nTree:");
        // tree.printTree();
        // tree.inOrderTraversal(tree.root);
    }
}
    
