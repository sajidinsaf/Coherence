#!/bin/sh

# This will start a console application
# demonstrating the functionality of the Coherence(tm) API

# specify the Coherence installation directory
SCRIPT_PATH="${BASH_SOURCE[0]}";
if([ -h "${SCRIPT_PATH}" ]) then
  while([ -h "${SCRIPT_PATH}" ]) do SCRIPT_PATH=`readlink "${SCRIPT_PATH}"`; done
fi
pushd . > /dev/null
cd `dirname ${SCRIPT_PATH}` > /dev/null
SCRIPT_PATH=`pwd`
COHERENCE_HOME=`dirname $SCRIPT_PATH`;
popd  > /dev/null

# specify if the console will also act as a server
STORAGE_ENABLED=false

# specify the JVM heap size
MEMORY=64m

if [ ! -f ${COHERENCE_HOME}/bin/coherence.sh ]; then
  echo "coherence.sh: must be run from the Coherence installation directory."
  exit
fi

if [ -f $JAVA_HOME/bin/java ]; then
  JAVAEXEC=$JAVA_HOME/bin/java
else
  JAVAEXEC=java
fi

if [ $STORAGE_ENABLED == "true" ]; then
	echo "** Starting storage enabled console **"
else
	echo "** Starting storage disabled console **"
fi

if [[ $1 == '-jmx' ]]; then
    JMXPROPERTIES="-Dtangosol.coherence.management=all -Dtangosol.coherence.management.remote=true"
    shift
fi

a_opts="-Xms$MEMORY -Xmx$MEMORY -Dtangosol.coherence.cluster=trade_cluster -Dtangosol.coherence.clusterport=34408"

COHERENCE_OPTS="-Dtangosol.coherence.cacheconfig=/Users/sajid/Documents/workspace/CoherenceGIT/coherence/bin/com/dijas/coherence/config/dijas-coherence-cache-config.xml -Dtangosol.pof.enabled=true"

#JAVA_OPTS="-Xms$MEMORY -Xmx$MEMORY -Dtangosol.coherence.distributed.localstorage=$STORAGE_ENABLED $JMXPROPERTIES"

JAVA_OPTS="$a_opts -Dtangosol.coherence.distributed.localstorage=$STORAGE_ENABLED $JMXPROPERTIES $COHERENCE_OPTS"

CLASSPATH="$COHERENCE_HOME/lib/coherence.jar:/Users/sajid/Documents/workspace/CoherenceGIT/coherence/bin"

$JAVAEXEC -server -showversion $JAVA_OPTS -cp $CLASSPATH com.tangosol.net.CacheFactory $1
