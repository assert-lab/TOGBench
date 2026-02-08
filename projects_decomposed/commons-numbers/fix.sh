perl -pi.bak -e 's/\.(is(?:NaN|Infinite|Finite))0(\s*\()/.$1$2/g' \
  commons-numbers-complex/src/test/java/org/apache/commons/numbers/complex/ComplexTest_OE25Dev.java

perl -pi.bak -e '
  s/\.abs0(\s*\()/\.abs$1/g;
  s/\bMath\s*\.\s*abs0(\s*\()/Math.abs$1/g;
' commons-numbers-complex/src/test/java/org/apache/commons/numbers/complex/ComplexTest_OE25Dev.java
