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
    
    def get(self, pos):
        if pos > self.size - 1 or pos < 0:
            return "index out of range"
        i = 0
        node = self.head
        while i < pos:
            node = node.next
            i += 1
        return node.data
    
    def append(self, x):
        newNode = Node(x)
        self.size += 1
        if not self.head:
            self.head = self.tail = newNode
            return
        self.tail.next = newNode
        newNode.prev = self.tail
        self.tail = newNode

    def prepend(self, x):
        newNode = Node(x)
        self.size += 1
        if not self.head:
            self.head = self.tail = newNode
            return
        self.head.prev = newNode
        newNode.next = self.head
        self.head = newNode

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
        print("pos", pos, "front", self.front.size)
        self.printQue()
        if pos > self.getSize() - 1 or pos < 0:
            return "index out of range"
        
        if pos <= self.front.size - 1:
            return self.front.get(pos)
        else:
            pos = pos - (self.front.size)
            return self.back.get(pos)

    def checkEqulibrium(self):
        if self.front.size < ((self.getSize())//2):
            # move one element to front of back
            if(self.front.size < 2): #basecase
                self.front.append(self.back.head.data)
                
                self.back.head = self.back.head.next
                self.back.head.prev = None
                self.back.size -= 1
            else:
                self.moveForward()
        elif self.back.size < ((self.getSize())//2):
            #move one element to head of self.back
            if(self.back.size < 2): #basecase
                self.back.prepend(self.front.tail.data) 
                
                self.front.tail = self.front.tail.prev
                self.front.tail.next = None
                self.front.size -= 1
            else:
                self.moveBackward()
            
    def moveBackward(self):
        #move the tail element of self.front to the head of self.back

        # Add the element to the head of self.back
        self.back.prepend(self.front.tail.data)
        
        #remove the element from the tail of self.front
        self.front.tail = self.front.tail.prev
        if self.front.tail:
            self.front.tail.next = None
        self.front.size -= 1
        
    def moveForward(self):
        #move the head of self.back to the tail of self.front
       
        # Add the element to the tail of self.front
        self.front.append(self.back.head.data)

        # Remove the element from the head of self.back
        self.back.head = self.back.head.next
        if self.back.head:
            self.back.head.prev = None
        self.back.size -= 1
        
    def push_front(self, x):
        self.front.prepend(x)
        self.checkEqulibrium()
        # self.printQue()
        
    def push_middle(self, x):
        #add element at (k+1)//2 where k is number of elements
        self.front.append(x)
        self.checkEqulibrium()
        # self.printQue()
        
    def push_back(self, x):
        self.back.append(x)
        self.checkEqulibrium()
        # self.printQue()
    
        
    def printQue(self):
        print("Front: ")
        self.front.printQue()
        print("Back: ")
        self.back.printQue()
        
A = Teque()

N = int(input())
while N != 0:
    inp = input()
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
    N -= 1