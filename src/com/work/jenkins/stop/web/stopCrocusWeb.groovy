package com.work.jenkins.stop.web

node {



    def ec2cmd = null
    def servers = null
    def tag = "Deploy"
    def value = "logistics-prd-crocus-web"
    def profile = "prod"

    stage('EC2 IP') {

        script {
            ec2cmd = sh(returnStdout : true, script : "aws ec2 describe-instances --profile ${profile} --filters Name=tag:${tag},Values=${value} --query Reservations[*].Instances[*].[PrivateIpAddress] --output text") // | paste -sd,
        }

        servers = ec2cmd.split('\n')
        echo "server ip > ${servers}"

        if(servers != null && servers.size() > 0) {

            servers.each{ server ->
                echo "server ip > ${server}"
            }
        } else {
            currentBuild.result = 'FAILURE'
            error("EC2 IP FAILED")
        }
    }

    stage ('Stop') {


        servers.each { server ->
            for (i = 0; i < 3; i++) {
                sh "ssh service@${server} /usr/sbin/fuser -k -TERM 8080/tcp || true"
                sleep(3)
            }

            sh "ssh service@${server} /usr/sbin/fuser -k 8080/tcp || true"


            for (i = 0; i <30; i++) {
                sleep(5)
                def response = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/web/web-hello || true").trim()


                println('################ Status : ' + response)


                if (i == 29 && response != '200') {
                    currentBuild.result = 'SUCCESS'
                    error("WEB STOP SUCCESS")
                }
            }

        }//servers each

    }//stage
}