pipeline {

    agent any

    stages {
        stage('checking maven version') {
            steps {
                bat 'mvn -v'
            }
        }
        stage('run'){
            steps{
                 bat 'mvn clean test'
        }
        }
    }
}