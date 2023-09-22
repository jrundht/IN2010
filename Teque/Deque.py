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