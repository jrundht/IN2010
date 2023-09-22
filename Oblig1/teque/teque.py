import time

# O(1)
def push_back(x):
    A.append(x)

# O(N)
def push_front(x):
    global A
    A.insert(0, x)

# O(N/2)
def push_middle(x):
    global A
    A.insert((len(A)+1)//2, x)

# O(1)
def get(i):
    return A[i]

def print_list():
    for i in A:
        print(i, end=' -> ')
    print("None")

t = time.time_ns()
filename = input()
A = []
file = open(filename, "r")
N = int(file.readline())
filename = f"myOutput1_{N}"
file1 = open("Outputs/"+filename, "w")
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
        # print(get(x))
    N -= 1
file.close()   
print(f"Time used: {(time.time_ns() - t)/1000}")
print_list()
# print_list()