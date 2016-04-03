/**
 * This programs allows user to search songs by either title, artist,
 * or album through a list of songs in the library
 * CECS 274
 * @author Jiawen Zhong
 *
 */
import java.io.*;
import java.util.*;
public class MusicLibrary {

	public static void main(String[] args) {
		Scanner read = new Scanner (System.in);

		Library lib = new Library("Songs.txt");

		int menuInput = 0;
		//int userChoice = 0;
		boolean done = false;
		while(!done) {
			displayMenu();
			menuInput = CheckInput.checkInt(1, 9);
			switch(menuInput){
			case 1: //add song
				System.out.println("Please enter the song's title, artist, album, and length that you want to add.");
				String songLine = read.nextLine();
				try{//there might be an ArrayIndexOutOfBoundsException occurs when the user did not enter the correct number of elements
					//separate the line into 4 elements, title, artist, album, and length
					String[] separate = songLine.split(",");
					lib.addSong(separate[0], separate[1], separate[2], separate[3]);
				}catch (ArrayIndexOutOfBoundsException n){
					System.out.println("Please enter the correct information");
				}
				break;
			case 2: //search by title
				System.out.println("Please enter the title of the song that you want to search for.");
				String title = read.nextLine();
				//lower case the input and remove all the punctuation and space before searching
				System.out.println("The song that you have searched: " + lib.searchByTitle(title.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim()));
				break;
			case 3://search by Artist
				System.out.println("Please enter the artist that you want to search for.");
				String artist = read.nextLine();
				if (!lib.searchByArtist(artist.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim()).isEmpty()){
					//lower case the input and remove all the punctuation and space before searching
					System.out.println("The songs from " + artist + ": " + lib.searchByArtist(artist.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim()));
				}else{
					System.out.println("Please enter a valid name.");
				}
				break;
			case 4: //search by album
				System.out.println("Please enter the album that you want to search for.");
				String album = read.nextLine();
				if (!lib.searchByAlbum(album.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim()).isEmpty()){
					//lower case the input and remove all the punctuation and space before searching
					System.out.println("The songs from " + album + ": " + lib.searchByAlbum(album.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim()));
				}else {
					System.out.println("Please enter a valid name.");
				}
				break;
			case 5: //remove song by index
				lib.sortSongs();
				lib.displaySongs();
				System.out.println("");
				try{
					System.out.println("Please enter the song number that you want to remove.");
					int indexRemove = read.nextInt();//CheckInput.checkInt(0, lib.getNumberOfSongs());
					//lower case the input and remove all the punctuation and space before searching
					System.out.println("The song removed: " + lib.removeByIndex(indexRemove));
				}catch (IndexOutOfBoundsException n){
					System.out.println("Please find a number that is on the list.");
				}
				break;
			case 6://remove song by title
				lib.sortSongs();
				lib.displaySongs();
				System.out.println("");
				System.out.println("Please enter the name of the song that you want to remove.");
				String remove = read.nextLine();
				//lower case the input and remove all the punctuation and space before searching
				System.out.println("The song removed: " + lib.removeByTitle(remove.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim()));
				break;
			case 7: //display number of songs
				System.out.println("Number of Songs: " + lib.getNumberOfSongs());
				break;
			case 8://display songs sorted by title
				lib.sortSongs();
				lib.displaySongs();
				break;
			case 9: //Write the list back to the file and quit the program
				lib.sortSongs();
				lib.displaySongs();
				lib.writeSongsToFile("Songs.txt");
				System.out.println("Quiting Library");
				done = true;
				break;
			default:System.out.println("Please enter a number according to the menu.");
			break;
			}
		}


	}

	/**
	 * Displays the menu
	 */
	public static void displayMenu() {
		System.out.println("Welcome to the Library.");
		System.out.println("1. Add Song");
		System.out.println("2. Search by Title");
		System.out.println("3. Search by Artist");
		System.out.println("4. Search by Album");
		System.out.println("5. Remove Song by Index");
		System.out.println("6. Remove Song by Title");
		System.out.println("7. Display Number of Songs");
		System.out.println("8. Display Songs Sorted by Artist");
		System.out.println("9. Save and Quit");
	}
}
