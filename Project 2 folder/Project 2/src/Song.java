/**
 * the class that create the song object for each song
 *
 */
public class Song {
	//add instance variables
	/**
	 * the song title
	 */
	private String title;
	/**
	 * song's artist
	 */
	private String artist;
	/**
	 * album of the song
	 */
	private String album;
	/**
	 * the length of the song
	 */
	private String length;


	//write constructor
	/**
	 * the constructor of song object
	 * @param t			the title of the song
	 * @param art		the artist of the song
	 * @param alb		the album the song belongs to	
	 * @param len		the length of the song
	 */
	public Song( String t, String art, String alb, String len ) {
		title = t;
		artist = art;
		album = alb;
		length = len;
	}


	//write get/set methods
	/**
	 * This method retrieve the song title
	 * @return			the song title
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * This method retrieve the song artist
	 * @return			the artist
	 */
	public String getArtist(){
		return artist;
	}

	/**
	 * This method retrieve the song's album
	 * @return			the album
	 */
	public String getAlbum(){
		return album;
	}

	/**
	 * This method retrieve the song's lengh
	 * @return			the length of the song
	 */
	public String getLength(){
		return length;
	}

	/**
	 * method that returns a string representation of a Song object
	 */
	@Override
	public String toString() {
		String s = title +","+ artist +"," + album +","+ length;

		return s;
	}
	/**
	 * check if the two Song objects are the same
	 */
	@Override
	public boolean equals( Object o ) {

		if (!(o instanceof Song)){
			return false;
		}
		Song that = (Song) o;
		if (this.title.equals(that.title)){
			if (this.artist.equals(that.artist)){
				if (this.album.equals(that.album)){
					if (this.length.equals(that.length)){
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	/**
	 * Compare the implicit variable to the explicit variable
	 * @param s			The other Song object that is being compared
	 * @return			zero if equal, negative # if in order, and positive # if they are out of out of order
	 */
	public int compareTo( Song s ) {
		Song that = (Song) s;
		int result = this.artist.compareToIgnoreCase(that.artist);
		if (result == 0){
			return this.title.compareToIgnoreCase(that.title);
		}
		return result;
	}
}
