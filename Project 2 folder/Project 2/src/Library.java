import java.util.*;
import java.io.*;

public class Library {
	//add instance variables
	/**
	 * The arraylist that holds all the info of the songs
	 */
	ArrayList<Song> librarySongs = new ArrayList<Song>();

	//write constructor
	/**
	 * the constructor
	 * @param fileName		the file name that has the songs in it
	 */
	public Library(String fileName){
		//open the file, read in the contents and store in an arraylist
		try{
			//read from the file
			Scanner read = new Scanner(new File("Songs.txt"));
			do{
				//add each song object into the arraylist of Song
				String line = read.nextLine();
				String[] separate = line.split(",");
				String title = separate[0];
				String artist = separate[1];
				String album = separate[2];
				String length = separate[3];
				librarySongs.add(new Song(title, artist, album, length));
			}while (read.hasNext());
			read.close();
		}catch(FileNotFoundException n){
			System.out.println("File not found");
		}
	}

	/**
	 * Search the song by title
	 * @param title			the title that is being search
	 * @return				the song object with all its information
	 */
	public Song searchByTitle( String title ) {
		Song song = null;
		boolean found = false;
		//using linear search to search through the list
		for(int i = 0; i < librarySongs.size() && !found; i++){
			//lower case the input and remove all the punctuation and space before searching
			if(title.equals(librarySongs.get(i).getTitle().replaceAll("[^a-zA-Z ]", "").toLowerCase().trim())){
				found = true;
				return song = librarySongs.get(i);
			}
		}
		return song;
	}

	/**
	 * Search the song by artist
	 * @param artist		the artist that is being searched
	 * @return				the arraylist that contains all the songs 
	 */
	public ArrayList<Song> searchByArtist( String artist ) {
		ArrayList<Song> songs = new ArrayList<Song>();
		//using linear search to search for the artist
		for(int i = 0; i < librarySongs.size(); i++){
			//lower case the input and remove all the punctuation and space before searching
			if(artist.equals(librarySongs.get(i).getArtist().replaceAll("[^a-zA-Z ]", "").toLowerCase().trim())){
				songs.add(librarySongs.get(i));//add the matching song to the arraylist
			}
		}
		return songs;
	}

	/**
	 * Search the song by Album
	 * @param album		the album that is being searched
	 * @return			the arraylist with the songs of that album
	 */
	public ArrayList<Song> searchByAlbum( String album ) {
		ArrayList<Song> songs = new ArrayList<Song>();
		//using linear search to search through the list for the matching album
		for(int i = 0; i < librarySongs.size(); i++){
			//lower case the input and remove all the punctuation and space before searching
			if(album.equals(librarySongs.get(i).getAlbum().replaceAll("[^a-zA-Z ]", "").toLowerCase().trim())){
				songs.add(librarySongs.get(i));
			}
		}
		return songs;
	}

	/**
	 * Add a new song to the arraylist
	 * @param t			the new song title
	 * @param art		the new song artist		
	 * @param alb		the new song's album	
	 * @param len		the length of the new song
	 */
	public void addSong( String t, String art, String alb, String len) {
		librarySongs.add(new Song(t, art, alb, len));
		//System.out.println("new songs:\n " + librarySongs);
	}

	/**
	 * Remove the song by the index of the Song ArrayList
	 * @param index
	 * @return
	 */
	public Song removeByIndex( int index ) {
		Song song = null;
		song = librarySongs.remove(index - 1);
		return song;
	}

	/**
	 * Remove the song by its title
	 */
	public Song removeByTitle( String title ) {
		Song song = null;
		boolean found = false;
		//search through the list to find the correct song for remove
		for(int i = 0; i < librarySongs.size() && !found; i++){
			//lower case the input and remove all the punctuation and space before searching
			if(librarySongs.get(i).getTitle().replaceAll("[^a-zA-Z ]", "").toLowerCase().trim().equals(title)){
				return song = librarySongs.remove(i);			
			}
		}
		return song;
	}

	/**
	 * Find the number of songs
	 * @return			an integer, the size of the arraylist
	 */
	public int getNumberOfSongs() {
		//number of songs is the size of the arraylist
		return librarySongs.size();
	}

	/**
	 * Displays all the songs in Library
	 */
	public void displaySongs() {
		int songNum = 1;//this numbers the songs
		System.out.println("Songs in Library:\n");
		//display each song with a number in front
		for (int i = 0; i < librarySongs.size(); i++){
			System.out.println(songNum + ". " + librarySongs.get(i));
			songNum ++;
		}
	}

	/**
	 * this method sort the arraylist of Songs into alphabetical order, first by artist, and then by title
	 * according to each artist.			
	 */
	public void sortSongs() {
		//sort by artist using linear serach
		for (int i = 0; i < librarySongs.size(); i++){
			int first = i;
			for (int j = i + 1; j < librarySongs.size(); j++){
				//compare the second element with the first, negative if the second should go before first
				if (librarySongs.get(j).compareTo(librarySongs.get(first)) < 0){
					//set first the the second element
					first = j;
				}
			}
			Song swap = librarySongs.get(i);
			librarySongs.set(i, librarySongs.get(first));
			librarySongs.set(first, swap);
		}
	}

	/**
	 * Write to the song file
	 * @param fileName		the file the songs need to be locate in.
	 */
	public void writeSongsToFile( String fileName ) {
		Scanner read = new Scanner (System.in);
		try{
			PrintWriter writer = new PrintWriter("Songs.txt");
			for (int i = 0; i < librarySongs.size(); i++){
				writer.print(librarySongs.get(i) + "\n");
			}
			writer.close();
		}catch (FileNotFoundException a){
			System.out.println("File was not found");
		}
	}
}
