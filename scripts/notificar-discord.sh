
#!/usr/bin/env bash
set -euo pipefail
set +x

ESTADO="${1:-UNKNOWN}"

if [ -n "${CIRCLECI:-}" ]; then
    PLATAFORMA="CircleCI"
    PROYECTO="${CIRCLE_PROJECT_REPONAME:-Proyecto}"
    RAMA="${CIRCLE_BRANCH:-desconocida}"
    BUILD="${CIRCLE_BUILD_NUM:-N/A}"
    URL_BUILD="${CIRCLE_BUILD_URL:-}"
else
    PLATAFORMA="Jenkins"
    PROYECTO="${JOB_NAME:-Pipeline Jenkins}"
    RAMA="${BRANCH_NAME:-desconocida}"
    BUILD="${BUILD_NUMBER:-N/A}"
    URL_BUILD="${BUILD_URL:-}"
fi

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

CONTENIDO="$MENSAJE
Plataforma: $PLATAFORMA
Proyecto: $PROYECTO
Rama: $RAMA
Build: $BUILD
Ejecucion: $URL_BUILD"

# Generar JSON de forma segura
JSON=$(python3 -c 'import json,sys; print(json.dumps({"content":sys.stdin.read()}))' <<< "$CONTENIDO")

curl --fail --silent --show-error \
    --retry 2 \
    --connect-timeout 10 \
    --max-time 30 \
    -H "Content-Type: application/json" \
    -d "$JSON" \
    "$DISCORD_WEBHOOK"

echo "Notificacion enviada a Discord."
