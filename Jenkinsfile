
Jenkinsfile (Declarative Pipeline)

pipeline {
    agent { docker 'maven:3.6.3' }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('Test') {
            steps {
                sh 'java --version'
            }
        }
        stage('Deploy - Production') {
            steps {
                sh './deploy production'
                sh './run'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            junit 'build/reports/**/*.xml'
        }
    }
}

