
class nodeBinarySearchTree{
    private nodeBinarySearchTree left = null;
    private nodeBinarySearchTree right = null;

    private int value; // this will represent the root

    nodeBinarySearchTree(int x){
        value = x;
    }

    public void insert(int x){
        if(x < value){ //go left
            
            if(left != null){
                left.insert(x);
            }else{
                left = new nodeBinarySearchTree(x);
            }
        }else if(x >= value){
            if(right != null){
                right.insert(x);
            }else{
                right = new nodeBinarySearchTree(x);
            }
        } 
    }

    public nodeBinarySearchTree findMin(nodeBinarySearchTree v){
        if(v.left != null){
            return findMin(v.left);
        }else if (v.right != null){
            return findMin(v.right);
        }else{
            return v;
        }
    }

    /*
     * Wrong??
     */
    public nodeBinarySearchTree remove(nodeBinarySearchTree v, int x){
        if(v == null){
            return v;
        }
        if( x < v.value){
            v.left = remove(v.left, x);
            return v;
        }else if(x > v.value){
            v.right = remove(v.right, x);
        }else{
            if(v.left == null && v.right == null){
                return v;
            }else if(v.right != null){
                v.value = successor(v);
                v.right = remove(v.right, v.value);
            }else if(v.left != null){
                v.value = predecessor(v);
                v.left = remove(v.left, v.value);
            }
        }
        return v;
    }

    public int successor(nodeBinarySearchTree v){
        v = v.right;
        while(v.left != null){
            v = v.left;
        }
        return v.value;
    }
    public int predecessor(nodeBinarySearchTree v){
        v = v.left;
        while(v.right != null){
            v = v.right;
        }
        return v.value;
    }

    public Integer search(nodeBinarySearchTree v, int x){
        if(x < v.value){
            if(v.left == null){
                return null;
            }else{
                return search(v.left, x);
            }
        }else if(x > v.value){
            if(v.right == null){
                return null;
            }else{
                return search(v.right, x);
            }
        }else{
            return v.value;
        }
    }
    public int countLeaves(nodeBinarySearchTree v){
        if(v == null) return 0;
        if(v.left == null && v.right == null){
            return 1;
        }
        return countLeaves(v.left) + countLeaves(v.right);
    }
    
    public void printLeaves(nodeBinarySearchTree v){
        if(v.left == null && v.right == null){
            System.out.println(v.value);
        }else{
            if(v.left != null){
                printLeaves(v.left);
            }
            if(v.right != null){
                printLeaves(v.right);
            }
        }
    }

    public void inOrderTraversal(){
        if(left != null) left.inOrderTraversal();
        System.out.println(value);
        if(right != null) right.inOrderTraversal();
    }

    public void postOrderTraversal(){
        if(left != null) left.postOrderTraversal();
        if(right != null) right.postOrderTraversal();
        System.out.println(value);
    }
    public void preOrderTraversal(){
        System.out.println(value);
        if(left != null) left.preOrderTraversal();
        if(right != null) right.preOrderTraversal();
    }

    public static void main(String[] args){
        nodeBinarySearchTree bst = new nodeBinarySearchTree(6);

        // bst.insert(5);
        bst.insert(5);
        bst.insert(10);
        bst.insert(11);
        bst.insert(3);
        bst.insert(2);
        bst.insert(5);
        bst.insert(4);
        // System.out.println(bst.left.left.right.value);
        // bst.inOrderTraversal();
        bst.preOrderTraversal();
        // bst.postOrderTraversal();
        // System.out.println(bst.countLeaves(bst));
        // bst.printLeaves(bst);
    }

}