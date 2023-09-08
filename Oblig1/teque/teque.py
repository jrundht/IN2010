import time
# # alternativ for forsøk på raskere kjøretid
# class Node:
#     def __init__(self, x):
#         self.data = x
#         self.next = None
#         self.prev = None
    
# class Linkedlist:
#     def __init__(self):
#         self.head = None
#         self.tail = None
#         self.middle = None
#         self.size = 0
    
#     def get(self, pos):
#         if pos > self.size - 1 or pos < 0:
#             # print("index out of range")
#             return "index out of range"
#         i = 0
#         node = self.head
#         while i < pos:
#             node = node.next
#             i += 1
#         return node.data
    
#     def updateMiddleAp(self):
#         if self.size % 2 == 0:
#             self.middle = self.middle.next
    
#     def updateMiddlePre(self):
#         if self.size % 2 == 1:
#             self.middle = self.middle.prev
    
#     def findMiddle(self):
#         x = (self.size) // 2
#         node = self.head
#         while x > 1:
#             node = node.next
#             x -= 1
#         return node
        
#     def append(self, x):
#         newNode = Node(x)
#         self.size += 1
#         if not self.head:
#             self.head = self.middle = self.tail = newNode
#             return
#         self.tail.next = newNode
#         newNode.prev = self.tail
#         self.tail = newNode
#         self.updateMiddleAp()
    
#     def prepend(self, x):
#         newNode = Node(x)
#         self.size += 1
#         if not self.head:
#             self.head = self.middle = self.tail = newNode
#             return
#         self.head.prev = newNode
#         newNode.next = self.head
#         self.head = newNode
#         self.updateMiddlePre()

#     def middlepend(self, x):
#         newNode = Node(x)
#         if self.size == 0 or self.size == 1:
#             self.append(x)
#             return

#         if self.size % 2 == 0: 
#             newNode.next = self.middle
#             newNode.prev = self.middle.prev
#             if self.middle.prev:
#                 self.middle.prev.next = newNode
#             else:
#                 self.head = newNode  
#             self.middle.prev = newNode
            
#             self.middle = newNode
#         else:  
#             newNode.next = self.middle.next
#             newNode.prev = self.middle
#             if self.middle.next:
#                 self.middle.next.prev = newNode
#             else:
#                 self.tail = newNode 
#             self.middle.next = newNode
#         self.size += 1

#     def print_list(self):
#         curr_node = self.head
#         while curr_node:
#             print(curr_node.data, end=' -> ')
#             curr_node = curr_node.next
#         print("None")

def push_back(x):
    A.append(x)

def push_front(x):
    # A.prepend(x)
    global A
    B = []
    B.append(x)
    for i in range(len(A)):
        B.append(A[i])
    A = B

def push_middle(x):
    # A.middlepend(x)
    global A
    i = (len(A)+1)//2
    B = A[:i]
    C = A[i:]
    B.append(x)
    for j in range (0, len(C)):
        B.append(C[j])
    A = B

def get(i):
    # return A.get(i)
    return A[i]

def print_list():
    for i in A:
        print(i, end=' -> ')
    print("None")

# A = Linkedlist()
t = time.time_ns()
filename = input()
A = []
file = open(filename, "r")
N = int(file.readline())
lst = filename.split("_")
filename = f"myOutput_{lst[1]}"
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
    else:
        file1.write(f"{A[x]}" + "\n")
        print(get(x))
        # print(get(x))
    N -= 1
file.close()   
print(f"Time used: {(time.time_ns() - t)/1000}")
# print_list()