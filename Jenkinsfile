
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

        stage('Test y calidad') {
            steps {
                echo 'Ejecutando pruebas y calidad...'

                sh 'mvn -B -ntp verify'
            }
        }
    }

    post {
        always {
            echo 'El pipeline ha finalizado.'

            junit(
                testResults: 'target/surefire-reports/*.xml',
                allowEmptyResults: true
            )

            archiveArtifacts(
                artifacts: 'target/*.jar',
                allowEmptyArchive: true,
                fingerprint: true
            )
        }

        success {
            echo 'Pipeline ejecutado correctamente.'
        }

        failure {
            echo 'El pipeline fallo. Revisar Console Output.'
        }
    }
}

