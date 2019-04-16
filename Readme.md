## 新版坦克大战

[![Build Status](https://travis-ci.org/JavaNeverSleep/tankwar.svg?branch=master)](https://travis-ci.org/JavaNeverSleep/tankwar)
[![codecov](https://codecov.io/gh/JavaNeverSleep/tankwar/branch/master/graph/badge.svg)](https://codecov.io/gh/JavaNeverSleep/tankwar)

* 基于JDK8，IDEA 2019.1，MAVEN
* 集成Travis
* 也可以使用exe4j或install4j构建Windows可执行程序或安装程序
```shell
mvn clean package
mvn dependency:copy-dependencies -DoutputDirectory=target/libs -DincludeScope=compile -DexcludeArtifactIds=lombok
set DIST_DIR=%USERPROFILE%\Downloads\TankWar\
copy target\tankwar-1.0-SNAPSHOT.jar %DIST_DIR%\libs\tankwar.jar /y
xcopy target\libs\*.jar %DIST_DIR%\libs\ /d /y /q
xcopy assets\*.* %DIST_DIR%\assets\ /d /y /e /q
exe4jc tankwar.exe4j
```

可参考模板文件[tankwar.exe4j](tankwar.exe4j)
