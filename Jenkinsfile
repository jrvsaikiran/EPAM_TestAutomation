pipeline {

    agent any

    stages {
        stage('checking maven version') {
            steps {
                bat 'mvn -v'
            }
        }
        stage('git') {
            steps {
                 git 'https://github.com/jrvsaikiran/EPAM_TestAutomation.git'
            }
        }
        stage('run'){
            steps{
                 bat 'mvn clean test'
        }
        }
    }
}