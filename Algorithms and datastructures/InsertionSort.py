array = [3, 4, 1, 6, 34, 2, 9, 6, 1, 73, 98, 12, 11, 62, 18, 37, 8]
for i in range(1, len(array), 1):
    j = i
    while j > 0 and array[j - 1] > array[j]:
        tmp = array[j]
        array[j] = array[j - 1]
        array[j - 1] = tmp
        j = j - 1
print(array)