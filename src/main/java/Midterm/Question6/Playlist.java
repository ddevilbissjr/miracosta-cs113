package Midterm.Question6;

import java.util.LinkedList;
import java.util.Random;

public class Playlist {

    /*
    a. play the last selected song
    b. Overwrite the last playing song
    c. Include a set of selected songs to the playlist
    d. Add a song
    e. Remove a set of songs
    f. Remove a song
    g. Shuffle
    h. Loop last played song
    i. Loop all songs
    j. Loop selected song
     */

    private LinkedList<Song> songQueue = new LinkedList<>();
    private Song currentSong;
    private boolean loop = false;

    public void playSong (Song... optional) {
        if (optional.length > 0) {
            for (Song s : optional) {
                s.play();
            }
        } else {
            if (!songQueue.isEmpty()) {
                currentSong = songQueue.remove();
                currentSong.play();
            } else {
                System.out.println("No songs in queue!");
            }
        }
    }

    public void addSongs (LinkedList<Song> songs) {
        songQueue.addAll(songs);
    }

    public void removeSongs (LinkedList<Song> songs) {
        songQueue.removeAll(songs);
    }

    public void shuffle () {
        Random r = new Random();

        LinkedList<Song> temp = new LinkedList<>();
        temp.addAll(songQueue);

        songQueue = new LinkedList<>();

        while (!temp.isEmpty()) {
            Song head = temp.pop();
            songQueue.add(r.nextInt(songQueue.size()), head);
        }
    }

    public void loopPrevious () {
        loop = true;

        while (loop) {
            playSong(currentSong);
        }
    }

    public void loopAll () {
        loop = true;

        LinkedList<Song> temp = new LinkedList<>();
        temp.addAll(songQueue);

        while (loop) {
            for(Song s : songQueue) {
                playSong(s);
            }

            addSongs(temp);
        }
    }

    public void loopSelected (Song song) {
        loop = true;

        while (loop) {
            playSong(song);
        }
    }

    public String currentlyPlaying () {
        return "The current song playing is " + currentSong;
    }
}
