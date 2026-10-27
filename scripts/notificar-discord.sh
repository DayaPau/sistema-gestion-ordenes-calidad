
#!/usr/bin/env bash
set -euo pipefail
set +x

ESTADO="${1:-UNKNOWN}"
PROYECTO="${JOB_NAME:-Pipeline Jenkins}"
RAMA="${BRANCH_NAME:-desconocida}"
BUILD="${BUILD_NUMBER:-N/A}"
URL_BUILD="${BUILD_URL:-}"

if [ -z "${DISCORD_WEBHOOK:-}" ]; then
    echo "ERROR: Falta DISCORD_WEBHOOK"
    exit 1
fi

case "$ESTADO" in
    SUCCESS)
        MENSAJE="Pipeline ejecutado correctamente"
        ;;
    FAILURE)
        MENSAJE="El pipeline ha fallado"
        ;;
    *)
        MENSAJE="Pipeline finalizado"
        ;;
esac

# Generar el contenido del mensaje
CONTENIDO="$MENSAJE
Proyecto: $PROYECTO
Rama: $RAMA
Build: $BUILD
Jenkins: $URL_BUILD"

# curl codifica el contenido como campo de formulario;
# Discord recibe un mensaje mediante payload_json.
curl --fail --silent --show-error \
    --retry 2 \
    --connect-timeout 10 \
    --max-time 30 \
    --data-urlencode "payload_json={\"content\":\"$(printf '%s' "$CONTENIDO" | sed ':a;N;$!ba;s/\\/\\\\/g;s/"/\\"/g;s/\n/\\n/g')\"}" \
    "$DISCORD_WEBHOOK"

echo "Notificación enviada a Discord."
