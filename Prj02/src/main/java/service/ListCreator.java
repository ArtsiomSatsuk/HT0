package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.music.MusicFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ListCreator {

    Logger logger = LogManager.getRootLogger();

    public void createMp3DublicatesList(ArrayList<MusicFile> mp3FilesList) throws IOException, NoSuchAlgorithmException {
        logger.info("\n\t\t\tMp3 Dublicates List");
        ArrayList<MusicFile> musicFiles = new ArrayList<>(new HashSet<>(mp3FilesList));
        String str = null;
        int n = 0;
        for (int i = 0; i < musicFiles.size(); i++) {
            MusicFile file1 = musicFiles.get(i);
            for (int j = i + 1; j < musicFiles.size(); j++) {
                MusicFile file2 = musicFiles.get(j);
                if ((file1.getCheckSum().equals(file2.getCheckSum()))) {
                    n++;
                    str = file1.getCheckSum();
                }
            }
            if (n == 1) {
                logger.info("Dublicates:");
                for (MusicFile mFile : musicFiles) {
                    if (mFile.getCheckSum().equals(str)) {
                        logger.info("+" + mFile.getAbsolutePath());
                    }
                }
            }
            n = 0;
        }
    }

    public void createPartialDataMatchList(ArrayList<MusicFile> musicFiles) {
        logger.info("\n\t\tList of mp3-files where artist, album and title of song matches with each other");
        String str = null;
        int n = 0;
        for (int i = 0; i < musicFiles.size(); i++) {
            MusicFile file1 = musicFiles.get(i);
            for (int j = i + 1; j < musicFiles.size(); j++) {
                MusicFile file2 = musicFiles.get(j);
                if ((file1.getArtist().equalsIgnoreCase(file2.getArtist())) && (file1.getAlbum().equalsIgnoreCase(file2.getAlbum()))
                        && (file1.getTitleOfSong().equalsIgnoreCase(file2.getTitleOfSong()))) {
                    n++;
                    str = file1.getArtist() + ", " + file1.getAlbum() + ", " + file1.getTitleOfSong();
                }
            }
            if (n == 1) {
                logger.trace("\n" + str);
                for (MusicFile mfile : musicFiles) {
                    if ((mfile.getArtist() + ", " + mfile.getAlbum() + ", " + mfile.getTitleOfSong()).equalsIgnoreCase(str)) {
                        logger.trace("+" + mfile.getAbsolutePath());
                    }
                }
            }
            n = 0;
        }
    }


    public void createMp3InfoListInHtml(ArrayList<MusicFile> mp3FilesList) throws IOException {
        Set<MusicFile> set = new HashSet<>(mp3FilesList);
        if (!set.isEmpty()) {
            File result = new File("Table.html");
            result.createNewFile();
            if (result.exists()) {

                BufferedWriter bw = new BufferedWriter(new FileWriter("Table.html"));
                bw.write("<html><head><meta charset=\"UTF-8\"><link href=\"CSS.css\" rel=\"stylesheet\"><title>New Page</title></head><body><div class=\"container flex-container\"> <div class=\"flex-element\" id=\"test\" >");
                bw.write("</div><div class=\"article flex-element\" style=\"flex-grow: 1\"><table id=\"Table\" border=\"1\">");

                Set<String> setArtists = new HashSet<>();

                for (MusicFile s : mp3FilesList) {
                    if ((s.getArtist() != null)) {
                        setArtists.add(s.getArtist().toUpperCase());
                    }
                }
                for (String artistname : setArtists) {
                    bw.write("<tr><td><p>Artist: </p></td><td><p>" + artistname + "</p></td><td></td><td></td><td></td></tr>");
                    Set<String> setOfAlbums = new LinkedHashSet<>();
                    for (MusicFile mp3file : mp3FilesList) {
                        if (mp3file.getArtist().toUpperCase().equals(artistname)) {
                            setOfAlbums.add(mp3file.getAlbum());
                        }
                    }
                    for (String albumName : setOfAlbums) {
                        bw.write("<tr><td></td><td><p>Album:<p></td><td><p>" + albumName + "</p></td><td></td><td></td></tr>");

                        for (MusicFile mp3File : set) {
                            if (mp3File.getArtist().toUpperCase().equals(artistname) && (mp3File.getAlbum().equals(albumName))) {
                                bw.write("<tr><td></td><td></td><td><p>" + mp3File.getTitleOfSong() + "</p></td><td><p>" + mp3File.getDuration() + "sec" + "</p></td><td><p>" + mp3File.getAbsolutePath() + "</p></td></tr>");
                            }
                        }
                    }
                }
                bw.write("</table></div><div class=\\\"flex-element\\\"> </div></div></body></html>");
                bw.close();
                System.out.println("Html file with mp3 list was successfully created here - " + result.getAbsolutePath());
            } else {
                System.out.println("Could not create html file");
            }
        }
        if (set.isEmpty()) {
            System.out.println("No mp3 files were found");
        }
    }
}