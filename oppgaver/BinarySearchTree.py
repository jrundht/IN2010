class Node:
    def __init__(self, x):
        self.left = None
        self.right = None
        self.element = x
        
class BinaryTree:
    def __init__(self):
        self.root = None
    
    #O(h) where h is the height of the tree
    #O(n) worst case for n elements. if balanced O(log(n))
    def Insert(self,v , x): 
        newNode = Node(x)
        if self.root == None:
            self.root = newNode
        if v == None:
            v = newNode
        elif x < v.element:
            v.left = self.Insert(v.left, x)
        else:
            v.right = self.Insert(v.right, x)
        return v
    
    #same big O as Insert
    def Search(self, v, x): 
        if v == None:
            return None
        elif v.element == x:
            return v
        elif x < v.element:
            return self.Search(v.left, x)
        elif x > v.element:
            return self.Search(v.right, x)
    
    def FindMin(self, v):
        if v.left != None:
            return self.FindMin(v.left)
        elif v.right != None:
            return self.FindMin(v.right)
        else:
            return v
    
    def Remove(self, v, x):
        if v == None:
            return None
        elif x < v.element:
            v.left = self.Remove(v.left, x)
            return v
        elif x > v.element:
            v.right = self.Remove(v.right, x)
            return v
        elif v.left == None:
            return v.right
        elif v.right == None:
            return v.left
        u = self.FindMin(v.right)
        v.element = u.element
        v.right = self.Remove(v.right, u.element)
        return v

def InRange(B, a, b):
    v = B.root
    values = []
    
    def RangeHelper(v, a, b):
        if v == None:
            return
        if v.element > a:
            RangeHelper(v.left, a, b)
        if a <= v.element <= b:
            values.append(v.element)
        if v.element < b:
            RangeHelper(v.right, a, b)
    RangeHelper(v, a, b)
    
    for i in range(len(values)):
        print(min(values))
        values.remove(min(values))
    
        
B = BinaryTree()
v = B.Insert(None, 10)
B.Insert(v, 15)
B.Insert(v, 12)
B.Insert(v, 17)

B.Insert(v, 5)
B.Insert(v, 2)
B.Insert(v, 7)

InRange(B, 2, 17)
