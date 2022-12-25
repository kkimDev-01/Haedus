package com.work.jenkins.start.api

node {



    def ec2cmd = null
    def servers = null
    def tag = "Deploy"
    def value = "logistics-prd-azalea-web-new"
    def profile = "prod"



    stage('BUILD_ID') {
        def buildId = params.get("lastSuccessBuildID")
        echo "BUILD_ID > ${lastSuccessBuildID}"

        if(buildId == null || buildId == "") {
            currentBuild.result = 'FAILURE'
            error("BUILD_ID FAILED")
        }
    }



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




    stage ('Start') {



        servers.each{ server ->



            sh "ssh service@${server} java -jar -Dspring.profiles.active=production -Dservice_mode=real -server -Xms4096m -Xmx4096m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:G1HeapRegionSize=8m -XX:+ParallelRefProcEnabled -XX:-ResizePLAB -XX:+UseThreadPriorities -XX:ThreadPriorityPolicy=42 -Djasypt.encryptor.password=newlogistics /applications/build/${lastSuccessBuildID}/azalea-interfaces.jar 2>> /dev/null >> /dev/null &"



            for (i = 0; i <30; i++) {
                sleep(5)
                def response = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/wms-web/web-hello || true").trim()



                println('################ Status : '+response)



                if(response == '200'){

                    return
                }



                if(i == 29 && response != '200'){
                    currentBuild.result = 'FAILURE'
                    error("START FAILED")
                }
            }

        }
    }

}