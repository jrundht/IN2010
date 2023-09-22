#kjoretid blir O(N^2) dersom array starter i synkende rekkefølge
def sort(A):
    for i in range(1, len(A)):
        j = i
        while j > 0 and A[j] < A[j-1]:
            A.swap(j,j-1)
            j -= 1
    return A