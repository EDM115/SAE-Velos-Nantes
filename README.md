# SAE Vélos Nantes
Final Project of CS year 1 (Database, graphs, stats, and JavaFX app that uses everything)

## Notion : https://darkened-message-974.notion.site/SAE-V-los-de-Nantes-bc61fe047f6b487cb222b0a5f01e18cd?pvs=4

-----

## How to run the app ?

1) Download and install MySQL (8.0.33 as we write this). Server, Workbench, Shell and Connector/J are needed for this app, other products aren't mandatory
2) Go in the MySQL Shell and run the following :
```bash
\connect root@localhost
\sql
CREATE DATABASE bd_velos_nantes;
\use bd_velos_nantes
\source SAE_creation.sql
\source SAE_remplissage_Quartier_Compteur_Date.sql
\source SAE_remplissage_Comptage.sql
ALTER TABLE Quartier CHANGE longeurPisteVelo longueurPisteVelo FLOAT;
CREATE USER admin@localhost IDENTIFIED BY 'mdp_admin';
CREATE USER user@localhost IDENTIFIED BY 'mdp_user';
GRANT ALL PRIVILEGES ON bd_velos_nantes.* TO admin@localhost WITH GRANT OPTION;
GRANT SELECT ON bd_velos_nantes.* TO user@localhost;
```
SQL files :  
- [SAE_creation.sql](https://raw.githubusercontent.com/EDM115/school-codes-v2/master/BUT1/Codes/R2.06/SAE_creation.sql)  
- [SAE_remplissage_Quartier_Compteur_Date.sql](https://raw.githubusercontent.com/EDM115/school-codes-v2/master/BUT1/Codes/R2.06/SAE_remplissage_Quartier_Compteur_Date.sql)  
- [SAE_remplissage_Comptage.sql](https://raw.githubusercontent.com/EDM115/school-codes-v2/master/BUT1/Codes/R2.06/SAE_remplissage_Comptage.sql)  

3) Go in [2 - Java/2 - Code/Final](./2%20-%20Java/2%20-%20Code/Final) and open a terminal there. Java JDK 20 have to be installed !! Not tested on prior versions
  1) Linux : `chmod +x run.sh && ./run.sh` (it seems that the class can't be found...)
  2) Windows :  
    - CMD : `run.bat`  
    - PowerShell : `.\run.bat`  
Alternatively, open that folder in VS Code (Ctrl + K, Ctrl + O), get Java JDK 20 installed, and edit the module path in `.vscode/launch.json` to the /lib folder  
Then go on Run and Debug tab, and F5

## How to repack the app after code edits ?

```bash
cd ws && jar cfm CycloNantais.jar ../manifest/Manifest.txt -C ../ .
```
(remove any file directly on ./ before packing)  
Then, move the folders back and put the newly created JAR from /ws to the root directory

## How to start ?
```bash
java  --module-path "./lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.web,com.google.gson,com.jfoenix --enable-preview --add-exports javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED --illegal-access=permit --add-opens javafx.graphics/com.sun.javafx.scene=com.jfoenix -cp "./CycloNantais.jar;class" frontend.Accueil
```

#### Command breakdown :
+ `java` : Uses java executable to start it. Java 20 needed
+ `--module-path` : Specify where the `.jar` modules are
+ `"./lib"` : They are in the `lib` folder relative to the app's root directory
+ `--add-modules` : Specify which modules we want to add dufing the app launch
+ `javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.web,com.google.gson,com.jfoenix` : Adds some `JavaFX` modules (`FXML` is probably not needed), `JFoenix` for Material Design and `GSON` by Google to handle JSON files
+ `--enable-preview` : Allows Java preview features to be enabled. Is also needed for weird reasons to launch it through the command line
+ `--add-exports` : Allows exporting of a package from a module to another module
+ `javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED` : `com.sun.javafx.scene` can be exported from `javafx.graphics` to any other module without restrictions
+ `--illegal-access=permit` : Permits illegal access to internal/non-public Java APIs. Deprecated starting from Java 17, this is why we have the next option
+ `--add-opens` : Allows opening a package from a module to another module for reflective access
+ `javafx.graphics/com.sun.javafx.scene=com.jfoenix` : `com.sun.javafx.scene` is opened from `javafx.graphics` to `javafx.graphic`
+ `-cp` : Specifies the classpath for the application
+ `"./CycloNantais.jar;class"` : Takes as input the `class` directory from the `CycloNantais` JAR file located in the app's root directory
+ `frontend.Accueil` : The entry point of that Java application is the `Accueil` class from the `frontend` package
