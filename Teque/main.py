from Teque import Teque
A = Teque()

filename = input("Filename: ")
file = open(filename, "r")
N = int(file.readline())
filename = f"Outputs/myOutput_{N}"
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
    N -= 1
A.printQue()
while inp != -1:
    inp = int(input("What element do you wnat to get? (-1 for exit): "))
    print(A.get(inp))
file.close()   