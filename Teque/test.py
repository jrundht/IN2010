from sys import stdin

class Node:
    def __init__(self, x):
        self.data = x
        self.next = None
        self.prev = None
    
class Deque:
    def __init__(self):
        self.head = None
        self.tail = None
        self.size = 0
        
    def getSize(self):
        return self.size
    
    def get(self, pos):
        if pos > self.size - 1 or pos < 0:
            return "index out of range"
        i = 0
        node = self.head
        
        while i < pos:
            node = node.next
            i += 1
        return node.data
    
    def push_back(self, x):
        newNode = Node(x)
        self.size += 1
        if not self.head:
            self.head = self.tail = newNode
            return
        self.tail.next = newNode
        newNode.prev = self.tail
        self.tail = newNode
    
    def pop_back(self):
        if self.tail.prev:
            self.tail = self.tail.prev
            self.tail.next = None
        else:
            self.head = self.tail = None
        self.size -= 1
        
    def push_front(self, x):
        newNode = Node(x)
        self.size += 1
        if not self.head:
            self.head = self.tail = newNode
            return
        self.head.prev = newNode
        newNode.next = self.head
        self.head = newNode
        
    def pop_front(self):
        if self.head.next:
            self.head = self.head.next
            self.head.prev = None
        else: 
            self.head = self.front = None    
        self.size -= 1

    def printQue(self):
        curr_node = self.head
        while curr_node:
            print(curr_node.data, end=' -> ')
            curr_node = curr_node.next
        print("None")

"""
A tripple ended que (teque) is a que where you can add elements at the 
front, middle and back.
This teque will take two deques and add the middle element either 
at the front of the first, or at the back of the second deque

if one deque suddenly is much larger than the other, elements will
be moved to ensure equlibrium
""" 

class Teque:
    def __init__(self):
        self.front = Deque()
        self.back = Deque()
    
    def getSize(self):
        return self.front.size + self.back.size

    #O(N/2)
    def get(self, pos):
        # self.printQue()
        if pos > self.getSize() - 1 or pos < 0:
            return "index out of range"
        
        if pos <= self.front.size - 1:
            return self.front.get(pos)
        else:
            pos = pos - (self.front.size)
            return self.back.get(pos)

    def checkEqulibrium(self):
        if self.back.size - self.front.size > 0: #front is always the shorter que if size=odd
            # move one element to front of back
            self.front.push_back(self.back.head.data)
            self.back.pop_front() #remove element from head of back
            
        elif self.front.size - self.back.size > 1:
            #move one element to head of self.back
            self.back.push_front(self.front.tail.data) 
            self.front.pop_back() #remove element from tail of front
            
    def push_front(self, x):
        self.front.push_front(x)
        self.checkEqulibrium()
        # self.printQue()
        
    def push_middle(self, x):
        # print(self.getSize())
        # assert self.front.getSize() == (self.getSize()+1)//2, f"middle skal settes inn på plass: {(self.getSize()+1)//2}, men self.front.size = {self.front.getSize()}"
        self.front.push_back(x)
        self.checkEqulibrium()
        # self.printQue()
        
    def push_back(self, x):
        self.back.push_back(x)
        self.checkEqulibrium()
        # self.printQue()
    
    def printQue(self):
        print("Front: ")
        self.front.printQue()
        print("Back: ")
        self.back.printQue()
        
A = Teque()

next(stdin) # toss
for inp in stdin:
    lst = inp.split()
    S = lst[0]
    x = int(lst[1])
    if S == "push_back":
        A.push_back(x)
    elif S == "push_front":
        A.push_front(x)
    elif S == "push_middle":
        A.push_middle(x)
    else:
        print(A.get(x))