array = [3, 4, 1, 6, 34, 2, 9, 6, 1, 73, 98, 12, 11, 62, 18, 37, 8]

def BubbleSort(a):
    k = 0
    for i in range (0, len(a) - 1, 1): 
        swapped = False
        for j in range (0, len(a) - i - 1, 1):
            if a[j] > a[j + 1]:
                tmp = a[j]
                a[j] = a[j + 1]
                a[j + 1] = tmp
                swapped = True
            k += 1
        if not swapped: 
            break
    print(k)
    return a
print(array)
print(BubbleSort(array))