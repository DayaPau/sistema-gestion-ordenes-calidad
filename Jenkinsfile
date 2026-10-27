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
                echo 'Preparando el proyecto...'
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
                            echo 'Ejecutando pruebas unitarias y cobertura...'
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
            steps {
                echo 'Generando el archivo JAR...'
                sh 'mvn -B -ntp -DskipTests package'
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

            archiveArtifacts(
                artifacts: 'target/*.jar',
                allowEmptyArchive: false,
                fingerprint: true
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
