#!groovy

node {
    // ----------------------------------------
    // -- ETAPA: Compilar
    // ----------------------------------------
    stage 'Compilar'
        echo 'Inicio Compilar...'

        // -- Configurar variables
        echo 'Configurando variables...'
        def mvnHome = tool 'M3'
        env.PATH = "${mvnHome}/bin:${env.PATH}"
        echo "var mvnHome='${mvnHome}'"
        echo "var env.PATH='${env.PATH}'"
        echo 'Fin configuración variables!!'

        // -- Descargar código desde SCM
        echo 'Descargando código desde SCM...'
        sh 'rm -rf *'
        checkout scm
        echo 'Fin descarga código desde SCM!!'

        // -- Compilando
        echo 'Compilando aplicación...'
        sh 'mvn clean compile'
        echo 'Aplicación compilada!!'

        echo 'Fin Compilar!!'
    // ----------------------------------------
    // -- ETAPA: Test
    // ----------------------------------------
    stage 'Test'
        echo 'Inicio Test...'
        echo 'Ejecutando Test...'
        try {
            sh 'mvn verify'
            step([$class:'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
        } catch(err) {
            step([$class:'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])

            if(currentBuild.result == 'UNSTABLE')
                currentBuild.result = 'FAILURE'

            throw err
        }
        echo 'Test ejecutados!!'
        echo 'Fin Test!!'
    // ----------------------------------------
    // -- ETAPA: Instalar
    // ----------------------------------------
    stage 'Instalar'
        echo 'Inicio Instalar...'
        echo 'Instalando el paquete generado en el repositorio maven...'
        sh 'mvn install -Dmaven.test.skip=true'
        echo "Paquete correctamente instalado!!"
        echo 'Fin Instalar!!'
    // ----------------------------------------
    // -- ETAPA: Archivar
    // ----------------------------------------
    stage 'Archivar'
        echo 'Inicio Archivar...'
        echo 'Archivando el paquete generado en Jenkins...'
        step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
        echo 'Fin Archivar!!'
}