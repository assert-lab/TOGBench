FILE="projects_decomposed/JSON-java/src/test/java/org/json/junit/JSONMLTest_OE25Dev.java"

sed -i '434,509d' "$FILE"
sed -i -e :a -e '$d;N;2,2ba' -e 'P;D' "$FILE"
