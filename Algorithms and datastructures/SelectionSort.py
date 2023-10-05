array = [3, 4, 1, 6, 34, 2, 9, 6, 1, 73, 98, 12, 11, 62, 18, 37, 8]

def selectionSort(a):
    for i in range (0, len(a), 1):
        k = i
        for j in range (i + 1, len(a), 1):
            if a[j] < a[k]:
                k = j
        if i != k:
            tmp = a[i]
            a[i] = a[k]
            a[k] = tmp
    return a
print(array)
print(selectionSort(array))