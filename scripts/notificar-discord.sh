

#!/usr/bin/env bash
set -euo pipefail
set +x

# ==========================================
# DETECTAR PLATAFORMA CI/CD
# ==========================================

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

# ==========================================
# VALIDAR WEBHOOK
# ==========================================

if [ -z "${DISCORD_WEBHOOK:-}" ]; then
    echo "ERROR: Falta DISCORD_WEBHOOK"
    exit 1
fi

# ==========================================
# IDENTIFICAR ESTADO
# ==========================================

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

# ==========================================
# CONSTRUIR MENSAJE
# ==========================================

CONTENIDO="$MENSAJE
Plataforma: $PLATAFORMA
Proyecto: $PROYECTO
Rama: $RAMA
Build: $BUILD
Ejecucion: $URL_BUILD"

# ==========================================
# ENVIAR NOTIFICACION A DISCORD
# Sin Python ni dependencias adicionales
# ==========================================

JSON_CONTENIDO=$(printf '%s' "$CONTENIDO" |
    sed ':a;N;$!ba;s/\\/\\\\/g;s/"/\\"/g;s/\n/\\n/g')

curl --fail --silent --show-error \
    --retry 2 \
    --connect-timeout 10 \
    --max-time 30 \
    --data-urlencode "payload_json={\"content\":\"$JSON_CONTENIDO\"}" \
    "$DISCORD_WEBHOOK"

echo "Notificacion enviada a Discord."
