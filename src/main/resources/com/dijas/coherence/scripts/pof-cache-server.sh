#!/bin/sh

# This will start a cache server

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

# specify the JVM heap size
MEMORY=512m

// added by Sajid
COHERENCE_OPTS="-Dtangosol.coherence.cacheconfig=/Users/sajid/Documents/workspace/CoherenceGIT/Coherence/src/main/resources/com/dijas/coherence/config/dijas-coherence-pof-config.xml -Dtangosol.pof.enabled=true -Dtangosol.coherence.distributed.localstore=true -Dtangosol.coherence.log.level=6 "

if [ ! -f ${COHERENCE_HOME}/bin/cache-server.sh ]; then
  echo "coherence.sh: must be run from the Coherence installation directory."
  exit
fi

if [ -f $JAVA_HOME/bin/java ]; then
  JAVAEXEC=$JAVA_HOME/bin/java
else
  JAVAEXEC=java
fi

if [[ $1 == '-jmx' ]]; then
    JMXPROPERTIES="-Dtangosol.coherence.management=all -Dtangosol.coherence.management.remote=true"
    shift
fi

a_opts="-Xms$MEMORY -Xmx$MEMORY -Dtangosol.coherence.cluster=trade_cluster -Dtangosol.coherence.clusterport=34408"


#JAVA_OPTS="-Xms$MEMORY -Xmx$MEMORY $JMXPROPERTIES"

JAVA_OPTS="$a_opts  $JMXPROPERTIES"

CLASS_PATH=$COHERENCE_HOME/lib/coherence.jar:/Users/sajid/Documents/workspace/CoherenceGIT/coherence/bin

$JAVAEXEC -server -showversion $JAVA_OPTS $COHERENCE_OPTS -cp "$CLASS_PATH" com.tangosol.net.DefaultCacheServer $1
