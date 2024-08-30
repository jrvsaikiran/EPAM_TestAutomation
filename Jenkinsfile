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
        stage('reports'){
            steps{
                   publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'target/Reports', reportFiles: 'HtmlReport.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
//                    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'reports', reportFiles: 'Automation.html', reportName: 'Declarative Extent Report', reportTitles: '', useWrapperFileDirectly: true])
            }
        }
    }
}