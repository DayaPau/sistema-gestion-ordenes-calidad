
#!/usr/bin/env bash
set -e

echo "Iniciando análisis de calidad..."

mvn -B -ntp -DskipTests \
  checkstyle:check \
  pmd:check \
  pmd:cpd-check

echo "Análisis de calidad completado."
