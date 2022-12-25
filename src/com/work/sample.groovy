package com.work

node {



    def ec2cmd = null
    def servers = null
    def tag = "Deploy"
    def value = "logistics-prd-hornet-api"
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



    stage ('Queue stop') {



        servers.each{ server ->

            def stopKafka = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/kafka/stopKafka || true").trim()



        }
    }

}