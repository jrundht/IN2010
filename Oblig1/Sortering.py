import time
from functools import total_ordering

@total_ordering
class CountCompares:
    def __init__(self, elem):
        self.elem = elem
        self.compares = 0

    def reset(self):
        self.compares = 0

    def __eq__(self, other):
        return self.elem == other.elem

    def __lt__(self, other):
        self.compares += 1
        return self.elem < other.elem

    def __repr__(self):
        return self.elem.__repr__()
    
class CountSwaps(list):
    swaps = 0

    def swap(self, i, j):
        self.swaps += 1
        self[i], self[j] = self[j], self[i]

def sort(A): #insertion
    # Do insertion sort here. Use the Sorter's comparison- and swap
    # methods for automatically counting the swaps and comparisons.

    # Use A.swap(i, j) to swap the values at two indices i and j. The swap is
    # counted, when using this method. Comparisons are counted automatically.

    return A

def sort(A): #merge
    # Do merge sort here. Use the Sorter's comparison- and swap
    # methods for automatically counting the swaps and comparisons.

    # Use A.swap(i, j) to swap the values at two indices i and j. The swap is
    # counted, when using this method. Comparisons are counted automatically.

    return A

def newFile(name, lst):
    file = open(filename + "_" + name + ".csv", "w")
    for i in lst:
        file.write(lst[i] + "\n")
    file.close()

t = time.time_ns()
filename = input()


file = open(filename, "r")
alt = file.read()
lst = alt.split().strip("\n")
file.close()