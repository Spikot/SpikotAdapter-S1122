pipeline{
    agent any

    stages{
        stage('test'){
            steps{
                sh './gradlew clean test'
            }
        }
        stage('create plugin'){
            steps{
                sh './gradlew createPlugin'
            }
        }
    }
    post{
        always{
            archiveArtifacts artifacts: 'build/libs/Spikot-S1122-Plugin.jar'
        }
    }
}