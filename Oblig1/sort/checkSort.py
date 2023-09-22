#Check that the file is sorted

filename = input("Give me a file to check the order in: ")
file = open(filename, "r")
alt = file.read()
lst = alt.split()
index = -1
sorted = True
for i in lst:
    if index > 0:
        if int(lst[index]) > int(i):
            sorted = False
            print(f"List not sorted: {lst[index]} larger than {i}")
    index += 1
if sorted:
    print("The array is sorted from low to high")