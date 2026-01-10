#!/usr/bin/env bash

modules=(
  "commons-geometry-core"
  "commons-geometry-euclidean"
  "commons-geometry-spherical"
  "commons-geometry-io-core"
  "commons-geometry-io-euclidean"
)

for m in "${modules[@]}"; do
  echo "=== $m ==="
  mvn -pl "$m" -am "-Drat.skip=true" "-Dtest.dir=src" "-Dtarget.dir=target" \
    org.pitest:pitest-maven:1.9.8:mutationCoverage 2>&1 | tee "pit-${m}.log"
done
