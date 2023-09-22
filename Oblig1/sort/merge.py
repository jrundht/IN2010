def merge(arr, arr1, arr2):
    i = 0
    j = 0
    while i < len(arr1) and j < len(arr2):
        if arr1[i] <= arr2[j]:
            arr[i + j] = arr1[i]
            i = i + 1
        else:
            arr[i + j] = arr2[j]
            j = j + 1
    while i < len(arr1):
        arr[i + j] = arr1[i]
        i = i + 1
    while j < len(arr2):
        arr[i + j] = arr2[j]
        j = j + 1
    return arr

#har testet algoritmen mot inputfilene, og sjekket at de gir riktig output
#O(n*log(n))
def mergeSort(A): #merge
    if len(A) <= 1:
        return A 
    i = (len(A)) // 2 
    arr1 = mergeSort(A[ : i])
    arr2 = mergeSort(A[i : len(A)])
    return merge(A, arr1, arr2)

def sort(A):
    return mergeSort(A)