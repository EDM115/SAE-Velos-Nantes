package hellofx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label label;

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");
        String userName = System.getProperty("user.name");
        String userHome = System.getProperty("user.home");
        String userDir = System.getProperty("user.dir");
        String javaHome = System.getProperty("java.home");
        String javaVendor = System.getProperty("java.vendor");
        String javaVendorUrl = System.getProperty("java.vendor.url");
        String javaVmName = System.getProperty("java.vm.name");
        String javaVmVersion = System.getProperty("java.vm.version");
        String javaVmVendor = System.getProperty("java.vm.vendor");
        String javaVmInfo = System.getProperty("java.vm.info");
        String javaVmSpecName = System.getProperty("java.vm.specification.name");
        String javaVmSpecVersion = System.getProperty("java.vm.specification.version");

        String javaClassVersion = System.getProperty("java.class.version");
        String javaClassPath = System.getProperty("java.class.path");
        String javaLibraryPath = System.getProperty("java.library.path");
        String javaIoTmpdir = System.getProperty("java.io.tmpdir");
        String javaCompiler = System.getProperty("java.compiler");
        String javaExtDirs = System.getProperty("java.ext.dirs");
        String fileSeparator = System.getProperty("file.separator");
        String pathSeparator = System.getProperty("path.separator");
        String lineSeparator = System.getProperty("line.separator");
        String sunBootClassPath = System.getProperty("sun.boot.class.path");
        String sunArchDataModel = System.getProperty("sun.arch.data.model");
        String sunCpuEndian = System.getProperty("sun.cpu.endian");
        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + "\n\nBosse Allan");
        label.setText(label.getText() + "\n\nos.name: " + osName + "\nos.version: " + osVersion + "\nos.arch: " + osArch + "\nuser.name: " + userName + "\nuser.home: " + userHome + "\nuser.dir: " + userDir + "\njava.home: " + javaHome + "\njava.vendor: " + javaVendor + "\njava.vendor.url: " + javaVendorUrl + "\njava.vm.name: " + javaVmName + "\njava.vm.version: " + javaVmVersion + "\njava.vm.vendor: " + javaVmVendor + "\njava.vm.info: " + javaVmInfo + "\njava.vm.specification.name: " + javaVmSpecName + "\njava.vm.specification.version: " + javaVmSpecVersion + "\njava.class.version: " + javaClassVersion + "\njava.class.path: " + javaClassPath + "\njava.library.path: " + javaLibraryPath + "\njava.io.tmpdir: " + javaIoTmpdir + "\njava.compiler: " + javaCompiler + "\njava.ext.dirs: " + javaExtDirs + "\nfile.separator: " + fileSeparator + "\npath.separator: " + pathSeparator + "\nline.separator: " + lineSeparator + "\nsun.boot.class.path: " + sunBootClassPath + "\nsun.arch.data.model: " + sunArchDataModel + "\nsun.cpu.endian: " + sunCpuEndian);
    }
}