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
    
    # O(N)
    def get(self, pos):
        if pos > self.size - 1 or pos < 0:
            return "index out of range"
        i = 0
        node = self.head
        
        while i < pos:
            node = node.next
            i += 1
        return node.data

    # O(1)
    def push_back(self, x):
        newNode = Node(x)
        self.size += 1
        if not self.head:
            self.head = self.tail = newNode
            return
        self.tail.next = newNode
        newNode.prev = self.tail
        self.tail = newNode
    
    # O(1)
    def pop_back(self):
        if self.tail.prev:
            self.tail = self.tail.prev
            self.tail.next = None
        else:
            self.head = self.tail = None
        self.size -= 1
        
    #O(1)
    def push_front(self, x):
        newNode = Node(x)
        self.size += 1
        if not self.head:
            self.head = self.tail = newNode
            return
        self.head.prev = newNode
        newNode.next = self.head
        self.head = newNode
    
    #O(1)
    def pop_front(self):
        if self.head.next:
            self.head = self.head.next
            self.head.prev = None
        else: 
            self.head = self.front = None    
        self.size -= 1

    # O(N)
    def printQue(self):
        curr_node = self.head
        while curr_node:
            print(curr_node.data, end=' -> ')
            curr_node = curr_node.next
        print("None")