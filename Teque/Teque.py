from Deque import Deque

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
    #O(1)
    def checkEqulibrium(self):
        if self.back.size - self.front.size > 0: 
            #move one element to tail of front
            self.front.push_back(self.back.head.data)
            self.back.pop_front() #remove element from head of back
            
        elif self.front.size - self.back.size > 1:
            self.back.push_front(self.front.tail.data) 
            self.front.pop_back() #remove element from tail of front
            #move one element to head of self.back
            
    #O(1)
    def push_front(self, x):
        self.front.push_front(x)
        self.checkEqulibrium()
        # self.printQue()
    
    #O(1)
    def push_middle(self, x):
        # print(self.getSize())
        # assert self.front.getSize() == (self.getSize()+1)//2, f"middle skal settes inn på plass: {(self.getSize()+1)//2}, men self.front.size = {self.front.getSize()}"
        self.front.push_back(x)
        self.checkEqulibrium()
        # self.printQue()
      
    #O(1)  
    def push_back(self, x):
        self.back.push_back(x)
        self.checkEqulibrium()
        # self.printQue()
    
    def printQue(self):
        print("Front: ")
        self.front.printQue()
        print("Back: ")
        self.back.printQue()