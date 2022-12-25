def changeNginxConf(enabled_file) {
    def webserver = ['1.232.50.241', '1.232.50.242'];

    webserver.eachWithIndex { server, index ->
        sh "ssh service@${server} ln -sf /applications/nginx/conf/sites-available/${enabled_file} /applications/nginx/conf/sites-enabled"
        sh "ssh service@${server} sudo /applications/nginx/sbin/nginx -s reload"
    }
}


node {



    def servers = ['10.52.124.42', '10.52.124.43'];

    stage('Init') {
        sh 'rm -rf ${WORKSPACE}/*'
    }



    stage('Checkout') {
        git (branch: 'master'
                ,credentialsId: 'kang.tw'
                ,url: 'http://10.52.221.227/newlogistics/endive.git'
        )
    }



    stage('Build') {
        // Run the maven build
        if (isUnix()) {
            sh 'whoami'
            sh './gradlew clean endive-interfaces:build -x test'
        } else {
            bat 'gradlew.bat clean endive-interfaces:build -x test'
        }
    }



    stage ('Deployment') {



        servers.eachWithIndex { server, index ->

            changeNginxConf("enabled-${index}");

            for (i = 0; i < 36; i++) {
                print "${server} Draning...."+i
                sleep(10)
            }



            sh "ssh service@${server} mkdir -p /applications/build/${BUILD_ID}"
            sh "scp ${WORKSPACE}/endive-interfaces/build/libs/* service@${server}:/applications/build/${BUILD_ID}/endive-interfaces.jar"

            sh "ssh service@${server} /usr/sbin/fuser -k 8080/tcp || true"



            for (i = 0; i < 6; i++) {
                sleep(10)

                def response = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/web/web-hello || true").trim()



                println('################ SHUTDOWN WATING : '+ i)



                if(response != '200'){
                    break
                }



                if(i == 5 && response == '200'){
                    currentBuild.result = 'FAILURE'
                    error("SHUTDOWN FAILED")
                }
            }

            sh "ssh service@${server} java -jar -Dspring.application.name=endive-web -Dspring.profiles.active=production -Dservice_mode=real -server -Xms3072m -Xmx3072m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:G1HeapRegionSize=8m -XX:+ParallelRefProcEnabled -XX:-ResizePLAB -XX:+UseThreadPriorities -XX:ThreadPriorityPolicy=42 -Djasypt.encryptor.password=newlogistics /applications/build/${BUILD_ID}/endive-interfaces.jar 2>> /dev/null >> /dev/null &"



            for (i = 0; i <30; i++) {
                sleep(5)
                def response = sh(returnStdout: true, script: "ssh service@${server} curl -s -o /dev/null -I -w '%{http_code}' http://localhost:8080/web/web-hello || true").trim()



                println('################ Status : '+response)



                if(response == '200'){
                    return
                }



                if(i == 29 && response != '200'){
                    currentBuild.result = 'FAILURE'
                    error("DEPLOYMENT FAILED")
                }
            }
        }

        changeNginxConf("enabled-default");
    }
}