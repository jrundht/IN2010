#Et ordnet array, og et element som skal finnes 
#deler array i to og ser om elementet er i denne halvdelen
import time

x = int(input("Hvilket vil du finne: "))
start = time.time()
A = [1,2,3,4,5,9,11,15,18,20,25,28,30,34,37,39,41,46,47,49,52,56,59,70]

def check(A, x):
    low = 0
    high = len(A)-1
    while low <= high:
        i = (low+high)//2 # // blir int
        if A[i] == x:
            return True
        elif A[i] < x:
            low = i + 1
        elif A[i] > x:
            high = i -1
    return False
            

print(A)
res = check(A, x)
end = time.time()
elapsed = end - start
print(res)
print("\nThe search took: ", elapsed)
while res == False:
    x = int(input("Hvilket tall skal sjekkes?: "))
    res = check(A, x)
    print(res)