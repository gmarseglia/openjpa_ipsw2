#!/bin/bash

if [[ $# -lt 2 ]]
then
	echo "Incorrect usage. Correct usage: runAndSave.sh <version_name> <--rc or --all> [-p]"
	exit 1
fi

PITEST=false
STRING_UTIL=false
ALL=false
ACTIVE_PROFILE=""

for arg in "$@"
do
	if [ "$arg" == "-p" ] ; then
		PITEST=true
	fi
	if [ "$arg" == "--su" ] ; then
    STRING_UTIL=true
    ACTIVE_PROFILE="myStringUtilSuite"
  fi
  if [ "$arg" == "--all" ] ; then
    echo "NOT IMPLEMENTED YET"
    exit 1
#    TODO: implement
#    ALL=true
#    ACTIVE_PROFILE="MyAllSuite"
  fi
done

if [ $STRING_UTIL == false ] && [ $ALL == false ] ; then
  echo "Incorrect usage. Correct usage: runAndSave.sh <version_name> <--su> [-p]"
  exit 1
fi

cd ..
mvn clean
mvn test -Pjacoco,ignoreTestFailure,customTest,$ACTIVE_PROFILE
mvn test -Pbadua,ignoreTestFailure,customTest,$ACTIVE_PROFILE
if $PITEST
then
	mvn test -Ppitest,ignoreTestFailure,customTest,$ACTIVE_PROFILE
fi

cd results/ || exit
./saveCurrentResult.sh "$1" -f
