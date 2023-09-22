import matplotlib.pyplot as plt
import pandas as pd

def mergePlot(type, filename):
    # Read the CSV file into a DataFrame
    d = pd.read_csv("Results/"+type+filename, skipinitialspace=True)
    df = d.iloc[:18326]
    plt.title(type + f"_Merge_{len(df['n'])-1}")
    plt.plot( df['n'], df['merge_cmp'], label="mergeCmp", marker='o')
    plt.plot( df['n'], df['merge_time'], label="mergeTime", marker='o')
    plt.xlabel("time")
    plt.ylabel("Comparisons")
    plt.legend()
    plt.show()

def insertionPlot(type, filename):
    # Read the CSV file into a DataFrame
    df = pd.read_csv("Results/"+type+filename, skipinitialspace=True)
    plt.title(type + f"_Insertion_{len(df['n'])-1}")
    plt.plot(df['n'], df['insertion_cmp'], label="insertionCmp", marker='o')
    plt.plot(df['n'], df['insertion_swaps'], label="insertionSwap", marker='o')
    plt.plot(df['n'], df['insertion_time'], label="insertionTime", marker='o')
    plt.xlabel("n")
    plt.legend()
    plt.show()
    
def both(type, filename):
    # Read the CSV file into a DataFrame
    df = pd.read_csv("Results/"+type+filename, skipinitialspace=True)
    plt.title(type+f"_1000")
    plt.plot(df['n'], df['merge_cmp'],   label="mergeCmp", marker='o')
    plt.plot(df['n'], df['merge_time'],   label="mergeTime", marker='o')
    plt.plot(df['n'], df['insertion_cmp'],  label="insertionCmp", marker='o')
    plt.plot(df['n'], df['insertion_time'],  label="insertionTime", marker='o')
    plt.xlabel("n")
    plt.legend()
    plt.show()
    
def two(type, filename, type1, filename1):
    # Read the CSV file into a DataFrame
    df = pd.read_csv("Results/"+type+filename, skipinitialspace=True)
    dfs = pd.read_csv("Results/"+type1+filename1, skipinitialspace=True)
    plt.title(type+f"_{len(df['n'])-1}_"+type1)
    plt.plot(df['n'], df['insertion_cmp'],   label=type+"insertionCmp", marker='o')
    plt.plot(df['n'], df['insertion_time'],   label=type+"insertionTime", marker='o')
    plt.plot(dfs['n'], dfs['insertion_cmp'],   label=type1+"insertionCmp", marker='o')
    plt.plot(dfs['n'], dfs['insertion_time'],   label=type1+"insertionTime", marker='o')
    
    #merge
    # plt.plot(df['n'], df['merge_cmp'],   label=type+"mergeCmp", marker='o')
    # plt.plot(df['n'], df['merge_time'],   label=type+"mergeTime", marker='o')
    # plt.plot(dfs['n'], dfs['merge_cmp'],   label=type1+"mergeCmp", marker='o')
    # plt.plot(dfs['n'], dfs['merge_time'],   label=type1+"insertionTime", marker='o')

    # plt.plot(df['insertion_time'], df['insertion_swaps'], label="insertionSwap", marker='o')
    plt.ylabel("Comparisons")
    plt.xlabel("n")
    plt.legend()
    plt.show()


# insertionPlot('random', '_100_results.csv')
# mergePlot('random','_100_results.csv')
# insertionPlot('random', '_1000_results.csv')
# mergePlot('random','_1000_results.csv')

# insertionPlot('nearly','_sorted_10_results.csv')
# mergePlot('nearly','_sorted_10_results.csv')

# insertionPlot('nearly','_sorted_100_results.csv')
# mergePlot('nearly','_sorted_100_results.csv')

# insertionPlot('nearly','_sorted_100000_results.csv')
# mergePlot('nearly','_sorted_100000_results.csv')

# mergePlot('nearly_sorted_100000_results.csv')

# insertionPlot('nearly','_sorted_100000_results.csv')
# mergePlot('nearly','_sorted_100000_results.csv')
# two("random", "_10000_results.csv", "nearly",'_sorted_10000_results.csv')
# both("random",'_10000_results.csv')
# both("random",'_10_results.csv')
# both("nearly",'_sorted_1000_results.csv')
# both("nearly",'_sorted_100000_results.csv')
both("nearly",'_sorted_1000000_results.csv')