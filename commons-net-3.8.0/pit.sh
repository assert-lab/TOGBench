#!/bin/bash


mvn clean test -PtestID -Dtest.dir=src  -PtargetID -Dtarget.dir=target -Drat.skip=true
mvn -Dhttps.protocols=TLSv1.2 -PtestID -Dtest.dir=src  -PtargetID -Dtarget.dir=target -Drat.skip=true org.pitest:pitest-maven:mutationCoverage> toga_pit.txt


mvn clean test -PtestID -Dtest.dir=evosuite_tests  -PtargetID -Dtarget.dir=evosuite_tests/target -Drat.skip=true
mvn -Dhttps.protocols=TLSv1.2 -PtestID -Dtest.dir=evosuite_tests  -PtargetID -Dtarget.dir=evosuite_tests/target -Drat.skip=true org.pitest:pitest-maven:mutationCoverage>evosuite_pit.txt

mvn clean test -PtestID -Dtest.dir=tests_without_assertion  -PtargetID -Dtarget.dir=tests_without_assertion/target -Drat.skip=true
mvn -Dhttps.protocols=TLSv1.2 -PtestID -Dtest.dir=tests_without_assertion  -PtargetID -Dtarget.dir=tests_without_assertion/target org.pitest:pitest-maven:mutationCoverage>no_assert_pit.txt