
pipeline {
    agent { label 'java17' }

    tools {
        jdk 'JDK-17'
        maven 'Maven-3.9'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/DayaPau/sistema-gestion-ordenes-calidad.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B -ntp -DskipTests compile'
            }
        }

        stage('Test y calidad') {
            steps {
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
            echo 'El pipeline falló. Revisar Console Output.'
        }
    }
}
