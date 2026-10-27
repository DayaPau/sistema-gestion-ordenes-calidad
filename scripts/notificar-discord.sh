
#!/usr/bin/env bash
set -euo pipefail
set +x

ESTADO="${1:-UNKNOWN}"
PROYECTO="${JOB_NAME:-Pipeline Jenkins}"
RAMA="${BRANCH_NAME:-desconocida}"
BUILD="${BUILD_NUMBER:-N/A}"
URL_BUILD="${BUILD_URL:-}"

if [ -z "${DISCORD_WEBHOOK:-}" ]; then
    echo "ERROR: No se configuró DISCORD_WEBHOOK"
    exit 1
fi

case "$ESTADO" in
    SUCCESS)
        ICONO="✅"
        MENSAJE="Pipeline ejecutado correctamente"
        ;;
    FAILURE)
        ICONO="❌"
        MENSAJE="El pipeline ha fallado"
        ;;
    *)
        ICONO="ℹ️"
        MENSAJE="Pipeline finalizado: $ESTADO"
        ;;
esac

export ICONO MENSAJE PROYECTO RAMA BUILD URL_BUILD

PAYLOAD=$(python3 - <<'PY'
import json
import os

contenido = (
    f"{os.environ['ICONO']} **{os.environ['MENSAJE']}**\n"
    f"**Proyecto:** {os.environ['PROYECTO']}\n"
    f"**Rama:** {os.environ['RAMA']}\n"
    f"**Build:** #{os.environ['BUILD']}\n"
    f"**Jenkins:** {os.environ['URL_BUILD']}"
)

print(json.dumps({"content": contenido}))
PY
)

curl --fail --silent --show-error \
    --retry 2 \
    --connect-timeout 10 \
    --max-time 30 \
    -H "Content-Type: application/json" \
    --data "$PAYLOAD" \
    "$DISCORD_WEBHOOK"

echo "Notificación enviada a Discord."
