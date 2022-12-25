package com.work.jenkins.build.web


node {



    def ec2cmd = null
    def servers = null
    def tag = "Deploy"
    def value = "logistics-prd-crocus-web"
    def profile = "prod"
    def alb_arn = "arn:aws:elasticloadbalancing:ap-northeast-2:509415571255:targetgroup/logistics-prd-crocus-web-tg/e7e418cbdb0e0009"

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



    stage('Init') {
        sh 'rm -rf ${WORKSPACE}/*'
    }



    stage('Checkout') {
        git (branch: '${targetRepo}'
                ,credentialsId: 'kang.tw'
                ,url: 'http://10.52.221.227/newlogistics/crocus.git'
        )
    }



    stage('Build') {
        // Run the maven build
        if (isUnix()) {
            sh 'whoami'
            sh './gradlew clean crocus-interfaces:build -x test'
        } else {
            bat 'gradlew.bat clean crocus-interfaces:build -x test'
        }
    }



    stage ('Deployment') {



        servers.each{ server ->



            sh "ssh service@${server} mkdir -p /applications/build/${BUILD_ID}"
            sh "scp ${WORKSPACE}/crocus-interfaces/build/libs/* service@${server}:/applications/build/${BUILD_ID}/crocus-interfaces.jar"

            sh "aws elbv2 deregister-targets --profile ${profile} --target-group-arn ${alb_arn} --targets Id=${server}"

            for (i = 0; i < 8; i++) {
                println('################ DRANNING WAITING : '+ i)
                sleep(10)
            }



            sh "ssh service@${server} /usr/sbin/fuser -k 8080/tcp || true"



            sh "ssh service@${server} java -jar -Dspring.application.name=crocus-web -Dspring.profiles.active=production -Dservice_mode=real -server -Xms2560m -Xmx2560m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:G1HeapRegionSize=8m -XX:+ParallelRefProcEnabled -XX:-ResizePLAB -XX:+UseThreadPriorities -XX:ThreadPriorityPolicy=42 -Djasypt.encryptor.password=newlogistics /applications/build/${BUILD_ID}/crocus-interfaces.jar 2>> /dev/null >> /dev/null &"



            for (i = 0; i <30; i++) {
                sleep(5)
                def response = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/web/web-hello || true").trim()



                println('################ STATUS : '+response)



                if(response == '200'){
                    sh "aws elbv2 register-targets --profile ${profile} --target-group-arn ${alb_arn} --targets Id=${server}"
                    sleep(10)
                    return
                }



                if(i == 29 && response != '200'){
                    currentBuild.result = 'FAILURE'
                    error("DEPLOYMENT FAILED")
                }
            }
        }
    }
}