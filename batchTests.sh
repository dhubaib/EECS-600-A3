#!/bin/bash
# This script calls sortTest.slurm to submit 20 unique jobs on the HPCC
rm test.txt # Clear out previous results

# Quicksort, reverse ordered
for i in {1..5}
do
   sbatch sortTest.slurm quick 10000000 reverse $i
done

# Quicksort, random ordered
for i in {1..5}
do
   sbatch sortTest.slurm quick 10000000 random $i
done

# Insertion sort, reverse ordered
for i in {1..5}
do
   sbatch sortTest.slurm insert 10000 reverse $i
done

# Insertion sort, random ordered
for i in {1..5}
do
   sbatch sortTest.slurm insert 10000 random $i
done