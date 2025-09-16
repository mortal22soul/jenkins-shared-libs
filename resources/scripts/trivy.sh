#!/bin/bash

echo imageNane - $1
echo severity - $2
echo exitCode - $3

trivy image $1 \
    --severity $2 \
    --exit-code $3 \
    --fornat json -o trivy-inage-$2-resu1ts.json