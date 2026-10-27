pipeline {
    agent { label 'java17' }

    tools {
        jdk 'JDK-17'
        maven 'Maven-3.9'
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {
        stage('Preparacion') {
            steps {
                echo "Rama actual: ${env.BRANCH_NAME}"
                sh 'mvn -version'
                sh 'mvn -B -ntp clean'
            }
        }

        stage('Build') {
            steps {
                echo 'Compilando el proyecto...'
                sh 'mvn -B -ntp -DskipTests compile'
            }
        }

        stage('Preparar validaciones') {
            steps {
                sh '''
                    rm -rf .pipeline-work
                    mkdir -p .pipeline-work/tests .pipeline-work/quality

                    tar --exclude=.git \
                        --exclude=.pipeline-work \
                        --exclude=target \
                        -cf - . | tar -xf - -C .pipeline-work/tests

                    tar --exclude=.git \
                        --exclude=.pipeline-work \
                        --exclude=target \
                        -cf - . | tar -xf - -C .pipeline-work/quality
                '''
            }
        }

        stage('Validaciones paralelas') {
            parallel {
                stage('Pruebas y cobertura') {
                    steps {
                        dir('.pipeline-work/tests') {
                            echo 'Ejecutando pruebas JUnit y cobertura JaCoCo...'
                            sh 'mvn -B -ntp verify'
                        }
                    }
                }

                stage('Analisis de calidad') {
                    steps {
                        dir('.pipeline-work/quality') {
                            echo 'Ejecutando Checkstyle, PMD y CPD...'
                            sh 'mvn -B -ntp -DskipTests checkstyle:check pmd:check pmd:cpd-check'
                        }
                    }
                }
            }
        }

        stage('Empaquetado') {
            when {
                anyOf {
                    branch 'develop'
                    branch 'main'
                }
            }
            steps {
                echo 'Generando artefacto JAR...'
                sh 'mvn -B -ntp -DskipTests package'
            }
        }

        stage('Publicacion') {
            when {
                branch 'main'
            }
            steps {
                echo 'Archivando artefacto de la rama estable...'
                archiveArtifacts(
                    artifacts: 'target/*.jar',
                    allowEmptyArchive: false,
                    fingerprint: true
                )
            }
        }
    }

    post {
        always {
            echo 'El pipeline ha finalizado.'

            junit(
                testResults: '.pipeline-work/tests/target/surefire-reports/*.xml',
                allowEmptyResults: false
            )
        }

        success {
            echo 'Pipeline ejecutado correctamente.'
        }

        failure {
            echo 'Pipeline fallido. Revisar Console Output.'
        }
    }
}
