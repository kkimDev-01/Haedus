node {



    def ec2cmd = null
    def servers = null
    def tag = "Deploy"
    def value = "logistics-prd-hornet-api"
    def profile = "prod"
    def alb_arn = "arn:aws:elasticloadbalancing:ap-northeast-2:509415571255:targetgroup/logistics-prd-hornet-api-tg/8ea9375f6ec1f1c5"

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
                ,url: 'http://10.52.221.227/newlogistics/hornet.git'
        )
    }



    stage('Build') {
        // Run the maven build
        if (isUnix()) {
            sh 'whoami'
            sh './gradlew clean hornet-interfaces:build -x test'
        } else {
            bat 'gradlew.bat clean hornet-interfaces:build -x test'
        }
    }




    stage ('Deployment') {



        servers.each{ server ->



            sh "ssh service@${server} mkdir -p /applications/build/${BUILD_ID}"
            sh "scp ${WORKSPACE}/hornet-interfaces/build/libs/hornet-interfaces*.RELEASE.jar  service@${server}:/applications/build/${BUILD_ID}/hornet-interfaces.jar"

            def stopKafka = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/kafka/stopKafka || true").trim()

            sh "aws elbv2 deregister-targets --profile ${profile} --target-group-arn ${alb_arn} --targets Id=${server}"



            for (i = 0; i <18; i++) {
                print "${server} Draning...."+i
                sleep(10)
            }

            sh 'echo "STOPING PROCESS"'
            sh "ssh service@${server} curl -X POST http://localhost:9090/actuator/custom-health || true"



            for (i = 0; i < 7; i++) {
                println('################ EUREKA DOWN WAITING : '+ i)
                sleep(10)
            }



            sh "ssh service@${server} /usr/sbin/fuser -k 8080/tcp || true"



            for (i = 0; i < 6; i++) {
                sleep(10)

                def response = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/api/api-hello || true").trim()



                println('################ SHUTDOWN WATING : '+ i)



                if(response != '200'){
                    break
                }



                if(i == 5 && response == '200'){
                    currentBuild.result = 'FAILURE'
                    error("SHUTDOWN FAILED")
                }
            }




            sh "ssh service@${server} java -jar -Dspring.application.name=hornet-api -Dspring.profiles.active=production -Dservice_mode=real -server -Xms2560m -Xmx2560m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:G1HeapRegionSize=8m -XX:+ParallelRefProcEnabled -XX:-ResizePLAB -XX:+UseThreadPriorities -XX:ThreadPriorityPolicy=42 -Djasypt.encryptor.password=newlogistics /applications/build/${BUILD_ID}/hornet-interfaces.jar 2>> /dev/null >> /dev/null &"



            for (i = 0; i <30; i++) {
                sleep(5)
                def response = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/api/api-hello || true").trim()



                println('################ Status : '+response)



                if(response == '200'){
                    sleep(5)
                    def startKafka = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/kafka/startKafka || true").trim()



                    if(startKafka != '200'){
                        error(server + " START KAFKA Failed")
                    }

                    sh "aws elbv2 register-targets --profile ${profile} --target-group-arn ${alb_arn} --targets Id=${server}"



                    currentBuild.result = 'SUCCESS'
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