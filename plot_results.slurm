#!/bin/bash
#SBATCH --output=results.txt

cp -r ~/a3/Results/ $PFSDIR
cp plot_results.py $PFSDIR/.
cd $PFSDIR

module load intel
module load python

python plot_results.py

cp -u *.png $SLURM_SUBMIT_DIR/.