#!/bin/bash
"./l-jdk-20/bin/java" --module-path "./lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.web,com.google.gson,com.jfoenix --enable-preview -cp "./CycloNantais.jar;class" frontend.Accueil
