# Comandos para Windows PowerShell

Ubícate en la carpeta del proyecto:

```powershell
cd "C:\Users\dayav\OneDrive\Desktop\Séptimo Semestre\Calidad de Software\order-quality-project"
```

Verifica Java y Maven:

```powershell
java -version
mvn -version
```

Compila y ejecuta las pruebas:

```powershell
mvn clean test
```

Ejecuta todas las herramientas de calidad:

```powershell
mvn clean verify
```

Ejecuta el programa:

```powershell
mvn clean compile
java -cp target/classes com.dayana.calidad.orders.app.Main
```

Guarda evidencia de la ejecución:

```powershell
java -cp target/classes com.dayana.calidad.orders.app.Main 2>&1 | Tee-Object -FilePath .\docs\evidencias\ejecucion-programa.txt
```

Guarda evidencia de pruebas y calidad:

```powershell
mvn clean verify 2>&1 | Tee-Object -FilePath .\docs\evidencias\mvn-clean-verify.txt
```

Comandos para Git:

```powershell
git init
git branch -M main
git add .
git commit -m "feat: refactorizar sistema de gestion de ordenes"
git remote add origin URL_DEL_REPOSITORIO
git push -u origin main
```
