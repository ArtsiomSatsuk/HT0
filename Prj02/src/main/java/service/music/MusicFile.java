package service.music;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MusicFile extends File {
    private long duration;
    private String album;
    private String artist;
    private String titleOfSong;

    public MusicFile(long duration, String album, String artist, String titleOfSong, String path) {
        super(path);
        this.duration = duration;
        this.album = album;
        this.artist = artist;
        this.titleOfSong = titleOfSong;
    }

    public long getDuration() {
        return duration;
    }

    public String getCheckSum() throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(getAbsolutePath())));
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitleOfSong() {
        return titleOfSong;
    }

    @Override
    public String toString() {
        return titleOfSong + " " + duration + "sec " + "(" + getAbsolutePath() + ")";
    }
}