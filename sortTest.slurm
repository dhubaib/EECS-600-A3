#!/bin/bash
#SBATCH --output=test.txt
#SBATCH --open-mode=append
#SBATCH --mem=4g

cp SortTest.java $PFSDIR/.
cp Sort.java $PFSDIR/.
cd $PFSDIR

javac SortTest.java
java SortTest ${1} ${2} ${3} ${4}

cp -u *.csv $SLURM_SUBMIT_DIR/Results/.