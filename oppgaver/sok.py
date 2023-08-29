import time
x = int(input("Hvilket tall skal sjekkes?: "))
start = time.time()

A = [1,2,3,4,5,9,11,15,18,20,25,28,30,34,37,39,41,46,47,49,52,56,59,70]
def check(A, x):
    for i in A:
        if i==x:
            return True
    return False

print(A)
res = check(A, x)
print(res)
end = time.time()
elapsed = end - start
print("\nThe search took: ", elapsed)
while res == False:
    x = int(input("Hvilket tall skal sjekkes?: "))
    res = check(A, x)
    print(res)