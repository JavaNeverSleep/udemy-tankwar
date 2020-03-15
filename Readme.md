# 放码过来！新版Java坦克大战

[![Build Status](https://travis-ci.org/JavaNeverSleep/udemy-tankwar.svg?branch=master)](https://travis-ci.org/JavaNeverSleep/udemy-tankwar)
[![codecov](https://codecov.io/gh/JavaNeverSleep/udemy-tankwar/branch/master/graph/badge.svg)](https://codecov.io/gh/JavaNeverSleep/udemy-tankwar)

[![](assets/images/udemy-course.jpg)](https://www.udemy.com/course/java-tank-war/?couponCode=JAVANEVERSLEEP-MAY)

此项目为Udemy在线课程[“放码过来！新版Java坦克大战”](https://www.udemy.com/course/java-tank-war/?couponCode=JAVANEVERSLEEP-MAY)现场编码结果，建议您与[参考实现](https://github.com/ny83427/tankwar)互相对照。如果您对Java编程语言不够熟悉，建议您先使用我录制的免费Java初阶课程[“放码过来！作个Java实战派”](https://www.udemy.com/java-warrior-part1)补足所需的知识与技能。


## 开发环境及相关备忘

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
install4jc tankwar.install4j
```

可参考模板文件[tankwar.exe4j](tankwar.exe4j)、[tankwar.install4j](tankwar.install4j)，同时自行修改其中与环境绑定依赖的内容

## 参考资源

- ⭐️ Java Never Sleep [YouTube Channel](https://bit.ly/2F2fMFP)
- ⭐️ Java Never Sleep [Facebook Page](https://www.facebook.com/javanotsleep)
- ⭐️ Java Never Sleep [GitHub Organization](https://github.com/JavaNeverSleep)

## License
See the [LICENSE](LICENSE.md) file for license rights and limitations (MIT).