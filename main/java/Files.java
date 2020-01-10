import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Files {
    private String  pathToDir, pathToFile;


    public void setPathToDir(String pathToDir) {
        this.pathToDir = pathToDir;
        System.out.println("pathToDir = " + pathToDir);
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
        System.out.println("pathToFile = " + pathToFile);
    }

    private File dir, file;
    private boolean append;

    public boolean prepareFileDir() {
        if (!dir.isDirectory()) {
            System.out.println("There is no path to directory");
            return false;
        }
        if (file.exists()) {
            append = true;
        } else {
            append = false;
            try {
                if (!file.createNewFile()) {
                    System.out.println("There is error in process of creating of file");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Error occured in process of creating of file");
            }
        }
        return true;
    }

    public void writeInFile() {
        String text = "\n\r\n\r";
        StringBuilder stringBuilder = new StringBuilder();
        String[] contents = dir.list();
        if (contents != null) {
            for (int i = 0; i < contents.length; ++i) {
                stringBuilder.append(contents[i]).append("\n\r\n\r");
                text = text + contents[i] + "\n\r\n\r";
            }
        }
        try (FileWriter nFile = new FileWriter(pathToFile, append)) {
            nFile.write(stringBuilder.toString());
        } catch (Exception e) {
            System.out.print("Error occured in process of writing in file");
        }
    }

    public void initializeFileDir() {
        dir = new File(pathToDir);
        file = new File(pathToFile);
    }

    public static void main(String[] args) {
        Files converter = new Files();
        if (args[0] == null || args[1] == null) {
            System.out.println("One of paths is not empty");
            return;
        }
        converter.setPathToDir(args[0]);
        try {
            converter.setPathToFile(args[1]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Enter yes if you accept absolute path to file");
        String input = in.nextLine();
        if (!input.equals("yes")) {
            System.out.println("Program didn't do anything");
            return;
        }
        converter.initializeFileDir();
        if (!converter.prepareFileDir()) {
            return;
        }
        converter.writeInFile();
    }
}