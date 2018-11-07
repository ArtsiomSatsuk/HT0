package service;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import service.music.MusicFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {

    private ArrayList<String> listOfMp3Paths = new ArrayList<>();
    private ArrayList<MusicFile> mp3FilesList = new ArrayList<>();

    public ArrayList<MusicFile> getFilesFromDirectories(String[] args) throws IOException, NullPointerException {
        for (String baseDirectory : args) {
            File dir = new File(baseDirectory);
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                for (File fileName : files) {
                    if ((!fileName.isDirectory() && (fileName.isFile()) && ((fileName.getName().endsWith(".mp3"))))) {
                        listOfMp3Paths.add(fileName.getAbsolutePath());
                    }
                    if (fileName.isDirectory()) {
                        getFilesFromDirectory(fileName);
                    }
                }
            }
        }
        return getInfoFromMp3(listOfMp3Paths);
    }

    private void getFilesFromDirectory(File dir) throws NullPointerException {
        File[] files = dir.listFiles();
        for (File file : files) {
            if ((file.isFile()) && (file.getName().endsWith(".mp3"))) {
                listOfMp3Paths.add(file.getAbsolutePath());
            }
            if (file.isDirectory()) {
                getFilesFromDirectory(file);
            }
        }
    }

    private ArrayList<MusicFile> getInfoFromMp3(ArrayList<String> listOfMp3Paths) throws IOException {
        for (String path : listOfMp3Paths) {
            Mp3File mp3file = null;
            try {
                mp3file = new Mp3File(path);
            } catch (UnsupportedTagException e) {
                System.out.println("Tag in this mp3 file is not supported - " + path);
                continue;
            } catch (InvalidDataException e) {
                System.out.println("Invalid data exception - " + path);
                continue;
            }

            String artist;
            String album;
            String title;

            if ((mp3file.hasId3v2Tag()) && (mp3file.getId3v2Tag().getAlbum() != null)) {
                album = mp3file.getId3v2Tag().getAlbum();

            } else {
                album = mp3file.getId3v1Tag().getAlbum();
            }
            if ((mp3file.hasId3v2Tag()) && (mp3file.getId3v2Tag().getTitle() != null)) {
                title = mp3file.getId3v2Tag().getTitle();
            } else {
                title = mp3file.getId3v1Tag().getTitle();
            }
            if ((mp3file.hasId3v2Tag()) && (mp3file.getId3v2Tag().getComposer() != null)) {
                artist = mp3file.getId3v2Tag().getArtist();
            } else {
                artist = mp3file.getId3v1Tag().getArtist();
            }
            if (album.matches("\\s+") || album.matches("")) {
                album = "Unknown album";
            }
            if (artist.matches("\\s+") || artist.matches("")) {
                artist = "UNKNOWN ARTIST";
            }
            if (title.matches("\\s+") || title.matches("")) {
                title = "Unknown title";
            }

            MusicFile musicFile = new MusicFile(mp3file.getLengthInSeconds(), album, artist, title, path);
            mp3FilesList.add(musicFile);
        }
        return mp3FilesList;
    }
}