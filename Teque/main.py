from Teque import Teque

A = Teque()

# For kattis:
# N = int(input())
# filename = f"../Outputs/myOutput_{N}"
# file1 = open(filename, "w")
# while N != 0:
#     inp = input()
#     lst = inp.split()
#     S = lst[0]
#     x = int(lst[1])
#     if S == "push_back":
#         A.push_back(x)
#     elif S == "push_front":
#         A.push_front(x)
#     elif S == "push_middle":
#         A.push_middle(x)
#     else:
#         print(A.get(x))
#     N -= 1
# A.printQue()

filename = input()
file = open(filename, "r")
N = int(file.readline())
filename = f"../Outputs/myOutput_{N}"
file1 = open(filename, "w")
while N != 0:
    inp = file.readline()
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
        file1.write(f"{A.get(x)}" + "\n")
        # print(A.get(x))
    N -= 1
file.close()   
A.printQue()