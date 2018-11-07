import service.FileHandler;
import service.ListCreator;
import service.music.MusicFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("You haven't entered any directories");
            return;
        }
        for (String path : args) {
            if (!(new File(path).isDirectory())) {
                System.out.println("Directory with such path doesn't exist - " + path);
            }
        }

        String sep = File.separator;
        String regex = null;

        if (sep.equals("/")) {
            regex = "\\w:/.*[*<>|:?\"]/?";
        } else if (sep.equals("\\")) {
            regex = "\\w:\\\\.*[/*<>|:?\"]\\\\?";
        }
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

        for (String basePath : args) {
            Matcher matcher = pattern.matcher(basePath);
            if (matcher.find()) {
                System.out.println("This directory path contains incorrect symbols in itself and will not be processed - " + basePath);
            }
        }
        try {
            FileHandler handler = new FileHandler();
            ArrayList<MusicFile> mp3FilesList = new ArrayList<>(handler.getFilesFromDirectories(args));

            ListCreator creator = new ListCreator();

            creator.createPartialDataMatchList(mp3FilesList);
            creator.createMp3DublicatesList(mp3FilesList);
            creator.createMp3InfoListInHtml(mp3FilesList);

        } catch (IOException ioe) {
            System.out.println("Unfortunately input/Output exception occurred: ");
            ioe.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5 algorithm is not available");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Don't enter root directories. Use the following example: D:" + sep + "SomeFolder" + sep);
        }
    }
}