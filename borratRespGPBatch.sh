#!/bin/sh

#========================================================================================
# Nombre					${USER_HOME}/batch/gper/borratRespGPBatch.sh
#
#	Descripción			Script para borrar aquellas respuestas de Grandes Periodos que han superado los 5 dias de disponibilidad a partir de la fecha
#
#				PASO 1)		Calcular la fecha de disponibilidad más grande que no supere los 5 dias de disponibilidad
#				PASO 2)		Borrar las peticiones anteriores a la fecha calculada
#
#======================================================================================================================

#----------------------------------------------------------------------------------------------------------------------
# Funciones
#----------------------------------------------------------------------------------------------------------------------
# Escribe el argumento $1
function traza() {

	PID=$$	
	TIMESTAMP=$(date +"%Y%m%d%H%M%S")

	CABECERA=$PID"."$TIMESTAMP"| "
	echo "${CABECERA}${1}" 
	
}
#----------------------------------------------------------------------------------------------------------------------
# Obtención de las variables de configuración
#----------------------------------------------------------------------------------------------------------------------
#Obtenemos el directorio home del usuario
USER_HOME=$HOME
# ruta y nombre del fichero de configuración
CONFIG_FILE=${USER_HOME}/config/batch.conf

# script de tratamiento de peticiones fichero
PATH_BATCH=${USER_HOME}/batch

# ruta del directorio de logs
LOG_DIR=$(grep LOG_DIR ${CONFIG_FILE} | awk -F= '{print($2)}')

# cadena de conexión a la base de datos (en formato usuario/password@instancia)
CONEXION_ORACLE=$(grep CONEXION_ORACLE ${CONFIG_FILE} | awk -F= '{print($2)}')

#PID
#PID=$$

# ruta del directorio donde se guardan los ficheros de grandes periodos BPO
#GPER_DIR=$(grep GPER_DIR ${CONFIG_FILE} | awk -F= '{print($2)}')
DATA_DIR=$(grep GPER_DIR ${CONFIG_FILE} | awk -F= '{print($2)}')
BPO_DIR=$DATA_DIR/respostes

# Pattern of BPO file
PATTERN_BPO=$(grep ACA_NAME_BPO ${CONFIG_FILE} | awk -F= '{print($2)}')

# Es defineix els tipus de BD
listFields='BPO ALTRETIPUS'

# log
if [[ ! -d $LOG_DIR ]]; then
	traza ERROR: El directorio de logs "'$LOG_DIR'" es incorrecto.
	exit 5
fi

LOG_FILE=$LOG_DIR/borratRespGPBatch.log

exec 1>>$LOG_FILE 2>&1
traza
traza
traza
traza "Deleting Grandes Periodos requests 5 days before to "$(date +"%d/%m/%Y %H:%M:%S")

traza "Executed at $(date)"

echo $BPO_DIR
TMP_LOG_FILE=$LOG_DIR/sqlplus.$PID.log

#----------------------------------------------------------------------------------------------------------------------
# Cálculo de fecha a tratar
#----------------------------------------------------------------------------------------------------------------------
if [ $# -ge 1 ]; then
	VALIDATOR="false"
	for TOKEN in $listFields; do
        	echo $TOKEN
        	if [ "$TOKEN" = $1 ];then
                	CRIT_SEC_TYPE="$TOKEN"
                	VALIDATOR="true"
        	fi
	done

	if [ $VALIDATOR = "true" ]; then
		DATE=$(date +"%d/%m/%Y")

		traza 'step 1: Calculating the available date (5 dias before to' $DATE')'
		DATA_DISPONIBILITAT_MAX=$(date +%d/%m/%Y -d '-5 days')

		traza 'Current date: " ${DATE} " ----- 5 days before: "${DATA_DISPONIBILITAT_MAX}"'
		
		#Following sql sentence get files that should been removed

		ALL_PATTERN=$PATTERN_BPO".FTP.S"

		sqlplus -s $CONEXION_ORACLE > $TMP_LOG_FILE << EOF
		SELECT concat('DEC.DEC00134.FTP.S', SUBSTR( CRIT_SEC_VAL,1,6)) as FICHEROS from CON_GRPER 
	where CRIT_SEC_TIP='${CRIT_SEC_TYPE}' AND FECHA_DISPONIBLE <= to_timestamp('${DATA_DISPONIBILITAT_MAX} 23:59:59','DD/MM/YYYY HH24:MI:SS');
	commit;
	/
	exit
EOF

		sed '/^$/d' $TMP_LOG_FILE # igual que un 'cat' pero sin líneas en blanco
		if [[ $(grep -c ORA- $TMP_LOG_FILE) -ne 0 || $(grep -c ERR $TMP_LOG_FILE) -ne 0 ]]; then
			traza "ERROR: Se ha producido algún error durante la ejecución de sqlplus."
			exit 158
		fi

		if [[ $(grep -c FICHEROS $TMP_LOG_FILE) -ne 0 ]]; then
			traza "Se ha encontrado solicitudes a borrar."
			
			while read line
			do 
				if [[ "$line" == *$ALL_PATTERN* ]]; then
					if [ -f $BPO_DIR/*$line* ];then
						  rm -f $BPO_DIR/*$line*
						  traza "Fichero "$BPO_DIR/$line" borrado."
					else
						traza "El fichero "$BPO_DIR/$line" no se puede borrar. No existe. "
					fi
				fi

			done < $TMP_LOG_FILE
		else
			traza "No se ha encontrado ninguna solicitud a borrar."

		fi
		rm $TMP_LOG_FILE
	else
		echo " This type does not exist. So validator is $VALIDATOR"
		exit 1
	fi
else
	echo "Lack of parameters"
	exit 1
fi
echo "END script $CRIT_SEC_TYPE"
exit 0
