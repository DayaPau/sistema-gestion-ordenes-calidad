
#!/usr/bin/env bash
set -e

echo "Preparando directorios para validaciones paralelas..."

rm -rf .pipeline-work
mkdir -p .pipeline-work/tests .pipeline-work/quality

tar --exclude=.git --exclude=.pipeline-work --exclude=target -cf - . | tar -xf - -C .pipeline-work/tests

tar --exclude=.git --exclude=.pipeline-work --exclude=target -cf - . | tar -xf - -C .pipeline-work/quality

echo "Directorios preparados correctamente."
