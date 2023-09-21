import time
# alternativ for raskere kjøretid
class Node:
    def __init__(self, x):
        self.data = x
        self.next = None
        self.prev = None
    
class Linkedlist:
    def __init__(self):
        self.head = None
        self.tail = None
        self.middle = None
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
    
    def findMiddle(self):
        x = (self.size) // 2
        node = self.head
        while x > 1:
            node = node.next
            x -= 1
        return node
        
    def append(self, x):
        newNode = Node(x)
        self.size += 1
        if not self.head:
            self.head = self.middle = self.tail = newNode
            return
        self.tail.next = newNode
        newNode.prev = self.tail
        self.tail = newNode
        if (self.size) % 2 != 0:
            self.middle = self.middle.next
        # self.print_list()
        # print(self.size, " added back")
        
    
    def prepend(self, x):
        newNode = Node(x)
        self.size += 1
        if not self.head:
            self.head = self.middle = self.tail = newNode
            return
        self.head.prev = newNode
        newNode.next = self.head
        self.head = newNode
        if (self.size) % 2 == 0:
            self.middle = self.middle.prev
        # self.print_list()
        # print(self.size, " added front")
        

    def middlepend(self, x):
        newNode = Node(x)
        
        if self.size == 0 or self.size == 1:
            self.append(x)
            return
        
        # if size is even before insertion, insert the new node after the middle
        if self.size % 2 == 0: 
            newNode.next = self.middle.next
            newNode.prev = self.middle
            if self.middle.next:
                self.middle.next.prev = newNode
            else:
                self.tail = newNode
            self.middle.next = newNode
            
            # Update the middle pointer
            self.middle = self.middle.next
        
        # if size is odd before insertion, insert the new node before the middle
        else:  
            newNode.next = self.middle
            newNode.prev = self.middle.prev
            if self.middle.prev:
                self.middle.prev.next = newNode
            else:
                self.head = newNode
            self.middle.prev = newNode
            
            # Update the middle pointer
            self.middle = newNode
        
        self.size += 1
        # self.print_list()
        # print(self.size, " added mid")

    def print_list(self):
        curr_node = self.head
        while curr_node:
            if curr_node == self.middle:
                print(f"[{curr_node.data}]", end=' -> ')
            else:
                print(curr_node.data, end=' -> ')
            curr_node = curr_node.next
        print("None")

def push_back(x):
    A.append(x)

def push_front(x):
    A.prepend(x)

def push_middle(x):
    A.middlepend(x)

def get(i):
    return A.get(i)

t = time.time_ns()
filename = input("Write the desired filename: ")
A = Linkedlist()
file = open(filename, "r")
N = int(file.readline())
filename = f"myOutput_{N}"
file1 = open(filename, "w")
while N != 0:
    inp = file.readline()
    lst = inp.split()
    S = lst[0]
    x = int(lst[1])
    if S == "push_back":
        push_back(x)
    elif S == "push_front":
        push_front(x)
    elif S == "push_middle":
        push_middle(x)
    elif S == "get":
        # file1.write(f"{get(x)}" + "\n")
        print(get(x))
        # print(get(x))
    N -= 1
# print(f"Time used: {(time.time_ns() - t)/1000}")
file.close() 