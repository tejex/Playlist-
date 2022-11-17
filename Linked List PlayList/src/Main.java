import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static  Scanner scanner = new Scanner(System.in);
    private static  ArrayList<Album> albums = new ArrayList<Album>();

    public static void main(String[] args) {
        boolean quit  = false;
        int choice = 0;
        printInstructions();
        while(!quit) {
            System.out.println("Enter Your Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0 -> printInstructions();
                case 1 -> newAlbum();
                case 2->  search();
                case 3 -> visitAlbum();
                case 4 -> addSongInAlbum();
                case 7 -> quit = true;
            }
        }
    }
    //**********************************************************************************************//
    //Application functionality code
    public static void printInstructions(){
        System.out.println("\n Press ");
        System.out.println("\t 0- Print the choice of options");
        System.out.println("\t 1- Add new album");
        System.out.println("\t 2- Search for albums");
        System.out.println("\t 3- Visit album.");
        System.out.println("\t 4- Add song to album");
    }

    //**********************************************************************************************//
    public  static void printAlbums(){
        for(Album album : albums){
            System.out.println("-> " + album.getName());
        }
    }
    //**********************************************************************************************//
    public static void newAlbum(){
        System.out.println("Enter name for new album");
        String name = scanner.nextLine();
        LinkedList<Song> songs = new LinkedList<Song>();
        Album newAlbum = new Album(songs,name);
        albums.add(newAlbum);
    }
    //**********************************************************************************************//
    public static  void search(){
        System.out.println("Enter album name: ");
        String name = scanner.nextLine();
        Album album = findAlbum(name);

        if(album.getName()!=null){
            System.out.println(album.getName());
        }
        else{
            System.out.println("Album does not exist");
        }
    }
    //**********************************************************************************************//
    public static Album findAlbum(String name){
        for(int i=0;i<albums.size();i++){
            if(albums.get(i).getName().equals(name)){
                return albums.get(i);
            }
        }
        Album album = new Album();
        return album;
    }
    //**********************************************************************************************//
    public static void visitAlbum(){
        System.out.println("Which album do you want to visit");
        System.out.println("Available albums");
        printAlbums();
        String choice = scanner.nextLine();
        Album album = findAlbum(choice);
        if(album.getName()!=null){
            playSongs(album);
        }
    }
    //**********************************************************************************************//
    public static void addSongInAlbum(){
        System.out.println("Which album would you like to add a song to? ");
        String choice = scanner.nextLine();
        Album album = findAlbum(choice);
        addSongToAlbum(album);
    }
    //**********************************************************************************************//
    public static void addSongToAlbum(Album album){
        if(album.getName()!=null){
            System.out.println("What is the name of the song? ");
            String name = scanner.nextLine();
            System.out.println("How long is the song? ");
            double duration = scanner.nextDouble();
            album.addSong(name,duration);
        }
        else{
            System.out.println("Album does not exist");
        }
    }
    //**********************************************************************************************//
    public static void playSongs(Album album) {
        if (!album.getSongs().isEmpty()) {
            album.play();
        }
        else {
            System.out.println("Album is empty, add some songs.");
        }
    }
}