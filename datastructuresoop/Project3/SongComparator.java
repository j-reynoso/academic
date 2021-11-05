/* SongComparator.java
 * Jasmin Reynoso 
 * Project 3
 * CMSC256 Fall 2019
 * This file when used with SongList.java orders the songs by song title in 
 * the group of the album name.
 */
//package cmsc256;

import java.util.Comparator;
import bridges.data_src_dependent.Song;


public class SongComparator implements Comparator<Song> {

	@Override
	public int compare(Song s1, Song s2) {
		if(s1.getAlbumTitle() == null) {
			return -1;
		} else if(s2.getAlbumTitle() == null) {
			return 1; 
		} else if(s1.getAlbumTitle().compareTo(s2.getAlbumTitle()) == 0) {
			return s1.getSongTitle().compareTo(s2.getSongTitle());
		}
		return s1.getAlbumTitle().compareTo(s2.getAlbumTitle());
	}
}
