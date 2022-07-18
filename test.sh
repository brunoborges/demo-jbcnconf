#!/bin/bash

function pause(){
 read -s -n 1 -p "Press enter to run..."
 echo ""
}

echo "## Run with 1 core and 1 GB of RAM"
pause && docker run -ti --rm --cpus 1 --memory 1g demo

echo 
echo "## Run with 1 core and 2 GB of RAM"
pause && docker run -ti --rm --cpus 1 --memory 2g demo

echo 
echo "## Run with 2 cores and 1 GB of RAM"
pause && docker run -ti --rm --cpus 2 --memory 1g demo

echo 
echo "## Run with 2 cores and 2 GB of RAM"
pause && docker run -ti --rm --cpus 2 --memory 2g demo