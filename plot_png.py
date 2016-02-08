# Python program that loads a text file with a 2D array and plots averages
# and standard deviation. It receives a filename as a command-line argument and
# creates a .jpg file containing the plot. The data file must be formatted such that
# the first column has x-axis values and the remaining columns have values
# for multiple measurements that are to be averaged. 

# Requires numpy and pylab.
import sys
import csv

from matplotlib import use
use('Agg')
from matplotlib import pyplot
from pylab import *
from numpy import *

## Read in data from results folder
for i in range(1,6):
	insert_rnd_file = 'Results/insertion_rand_test'+i+'.csv'
	insert_rev_file = 'Results/insertion_reverse_test'+i+'.csv'
	quick_rnd_file = 'Results/quick_rand_test'+i+'.csv'
	quick_rnd_file = 'Results/insertion_rand_test'+i+'.csv'

	f1 = open(insert_rnd_file,'r')
	reader = csv.reader(f1)


f.close()

x = data[0]
m = mean(data[1:,:]/1000, axis=0)
s = std(data[1:,:]/1000, axis=0)

pyplot.figure()
pyplot.errorbar(x, m, yerr=s,elinewidth=2) # Plot lines

plt.ticklabel_format(style='sci', axis='x', scilimits=(0,0))
xlabel('Array Size')
ylabel('Time (sec)')
title('test')
savefig('test' + '.png')
