Battleships (Group 6 Emir Cajlakovic, Dennis Addo, Lulzim Bulica)
----------

[![Build Status](https://travis-ci.com/devopsusr-tech/travis-battleship.svg?token=XtFwzcynvnyLtG7BsR4i&branch=main)](https://travis-ci.com/devopsusr-tech/travis-battleship)

[![DockerBuild](https://img.shields.io/docker/cloud/build/lulzimbulica/battleship)](https://cloud.docker.com/u/lulzimbulica/repository/docker/lulzimbulica/battleship)
                
[![codecov](https://codecov.io/gh/devopsusr-tech/travis-battleship/branch/main/graph/badge.svg?token=5IZ74QSVE2)](https://codecov.io/gh/devopsusr-tech/travis-battleship)

[![QualityGate](https://sonarcloud.io/api/project_badges/measure?project=devopsusr-tech_travis-battleship&metric=alert_status)](https://sonarcloud.io/dashboard?id=devopsusr-tech_travis-battleship)

[![License: MIT](https://img.shields.io/badge/License-MITyellow.svg)](https://opensource.org/licenses/MIT)

This repo contains the project requirements of ASD Course Exercise-2.

How to setup and Run the Game [IntelliJ IDE Guide]
------------------------------
This guide explains how to run this project in IntelliJ IDEA.

1. Clone the repo locally `git clone https://github.com/cajlakem/battleships-master.git`
2. Open the project in intelliJ
3. Project structure > Project > Project SDK > <ins> You have to set your java path here (Java version >11)</ins>
4. Project structure > Project > Project compiler output > <ins>Select the out file on the current project </ins>
5. Project structure > Modules > Click Sources > <ins> Set src folder as Source </ins>
6. Download JavaFX [from here](https://gluonhq.com/products/javafx/) and set on Project structure > Libraries > New (+) > <ins> Lib folder from Javafx </ins> > Add > Apply
7.  Run > Edit configurations:
- Set main class
- Set JRE path
- Select the VM option from modify list
- VM Options:
`--module-path "" --add-modules javafx.controls,javafx.fxml --add-exports javafx.graphics/com.sun.javafx.prism=ALL-UNNAMED` where at `--module-path` you have to set the lib folder of downloaded JavaFx

Now you can run the application and enjoy the application features :) 

