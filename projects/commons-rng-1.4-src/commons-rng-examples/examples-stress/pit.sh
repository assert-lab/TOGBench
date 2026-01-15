#!/bin/bash
mvn clean test > mvn.log
mvn pitest:mutationCoverage > pit.log
