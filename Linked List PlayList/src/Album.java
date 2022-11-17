import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Album {
    private LinkedList<Song> songs;
    private String name;

    public Album(){} //Empty constructor
    //**********************************************************************************************//
    public Album(LinkedList<Song> songs, String name) {
        this.songs = songs;
        this.name = name;
    }
    //**********************************************************************************************//
    public LinkedList<Song> getSongs() {
        return songs;
    }
    //**********************************************************************************************//
    public void setSongs(LinkedList<Song> songs) {
        this.songs = songs;
    }
    //**********************************************************************************************//
    public String getName() {
        return name;
    }
    //**********************************************************************************************//
    public void setName(String name) {
        this.name = name;
    }
    //**********************************************************************************************//
    //Additional methods for the album
    public void printSongs(){
        if(songs.isEmpty()){
            System.out.println("No songs here please add some first ");
        }
        else{
            System.out.println("Songs: ");
            for (Song song : songs) {
                System.out.println("* " + song.getTitle());
            }
        }
    }
    //**********************************************************************************************//
    //Adding new song to the album
    public void addSong(String name , double duration){
        if(findSong(name)==null){
            addNewSong(name,duration);
        }
        else{
            System.out.println("Song with same name already exists in this album");
        }
    }
    //**********************************************************************************************//
    //Private method to keep inner workings safe
    private void addNewSong(String name, double duration){
        Song newSong = new Song(name,duration);
        songs.add(newSong);
    }
    //**********************************************************************************************//
    public Song findSong(String name){
        return searchSong(name);
    }
    //**********************************************************************************************//
    //Private method to keep inner workings safe
    private Song searchSong(String name){
        for(Song song : songs){
            if(song.getTitle().equals(name)){
                return song;
            }
        }
        return null;
    }
    //**********************************************************************************************//
    //method to remove a song.
    public void removeSong(String name){
        if(searchSong(name)!=null){
            for(int i=0;i<songs.size();i++){
                if(songs.get(i).getTitle().equals(name)){
                    deleteSong(i);
                }
            }
        }
        else{
            System.out.println("Song not found");
        }
    }
    //**********************************************************************************************//
    //private method to keep inner working safe
    private void deleteSong(int index){
        songs.remove(index);
    }
    //**********************************************************************************************//
    //playing, skipping and replaying songs
    public void play() {
        boolean forward = true;
        boolean quit = false;
        ListIterator<Song> songListIterator = getSongs().listIterator();
        printSongs();
        System.out.println("Now playing " + songListIterator.next().getTitle());
        songOptions();
        int choice = 0;
        while (!quit) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose to play next song, replay current song or go back to previous song: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0-> {
                    System.out.println("Playlist complete");
                    quit =true;
                }
                case 1-> {
                    if(!forward){
                        if(songListIterator.hasNext()){
                            songListIterator.next();
                        }
                        forward =true;
                    }
                    if(songListIterator.hasNext()){
                        System.out.println("Now playing " + songListIterator.next().getTitle());
                    }
                    else{
                        System.out.println("End of playlist");
                        forward = false;
                    }
                }
                case 2-> {
                    if(forward){
                        if(songListIterator.hasPrevious()){
                            songListIterator.previous();
                        }
                        forward = false;
                    }
                    if(songListIterator.hasPrevious()){
                        System.out.println("Now playing " + songListIterator.previous().getTitle());
                    }
                    else{
                        System.out.println("We are at the beginning of the playlist");
                        forward = true;
                    }
                }
                case 3 -> {
                    if(forward){
                        if(songListIterator.hasPrevious()){
                            System.out.println("Now replaying " + songListIterator.previous().getTitle());
                            forward =false;
                        }
                        else{
                            System.out.println("We are at the start of the playlist");
                        }
                    }
                    else{
                        if(songListIterator.hasNext()){
                            System.out.println("Now replaying " + songListIterator.next().getTitle());
                        }
                        else{
                            System.out.println("We are at the end of the playlist");
                        }
                    }

                }
            }


        }
    }
    //**********************************************************************************************//
    public void songOptions(){
        System.out.println("To quit: 0");
        System.out.println("To play next song: 1");
        System.out.println("To play previous song: 2");
        System.out.println("To replay this song: 3");
    }
    //**********************************************************************************************//


}
