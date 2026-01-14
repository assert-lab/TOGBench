#!/bin/bash



es_tests_path="/media/FSE_USER/extradrive1/toga-eval-FSE_USER-2023/backup_FSE_USER_paper/actual_toga_decomposed_test/evosuite-artifacts/commons-numbers-1.0-src"
dest_pah="/media/FSE_USER/extradrive1/toga-eval-FSE_USER-2023/oct172022/toga-eval-FSE_USER-2023/evosuite-artifacts/commons-numbers-1.0-src"


for i in $(ls -d */);
do
  dir=$i;
  echo $dir

  if [[ $dir == commons-numbers* ]]
  then
    echo $dir
    #mkdir $dest_pah/$dir/evosuite_tests
    #cp -r $es_tests_path/"$dir"src/test $dest_pah/"$dir"evosuite_tests/test/
    #cp /media/FSE_USER/extradrive1/toga-eval-FSE_USER-2023/oct172022/toga-eval-FSE_USER-2023/evosuite-artifacts/commons-pool2-2.11.1-src/pit.sh $dir
    #mkdir -p $dest_pah/$dir/tests_without_assertion
    #cp -r $dest_pah/"$dir"evosuite_tests/test $dest_pah/"$dir"tests_without_assertion/test/
    #python /media/FSE_USER/extradrive1/toga-eval-FSE_USER-2023/oct172022/toga-eval-FSE_USER-2023/scripts/remove_assertions.py  $dest_pah/"$dir"tests_without_assertion
  fi
done


