array = [3, 4, 1, 6, 34, 2, 9, 6, 1, 73, 98, 12, 11, 62, 18, 37, 8]

def merge(arr1, arr2):
    i = 0
    j = 0
    arr = []
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

def mergeSort(arr):
    if len(arr) <= 1:
        return arr
    i = int(len(arr)/2)
    arr1 = mergeSort(arr[: i])
    arr2 = mergeSort(arr[i : len(arr)])
    return merge(arr1, arr2)

print(array)
print(mergeSort(array))