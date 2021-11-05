/* SongList.java
 * Jasmin Reynoso 
 * Project 3
 * CMSC256 Fall 2019tay
 * This file allows for a user specified artist name to be chosen
 * and then prints out the song name, artist name, and album name
 * from the chosen artist using data from the Bridges song database.
 */
//package cmsc256;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.Song;

public class SongList {

	/* main method: creates object for songList
	 * checks if args has a command line by checking length, if so stores into instance variable artist
	 * if empty, calls static method getArtistName to get the artists name, then stores into instance variable artist
	 * both branches then call getSongsByArtist to print out ordered list of artist's songs
	 * */
	public static void main(String[] args) {
		SongList songList = new SongList();
		if(args.length != 0) {
		songList.artist = (args[0]);
		System.out.println(songList.getSongsByArtist(songList.artist));
		} else {
		songList.artist = getArtistName();
		System.out.print(songList.getSongsByArtist(songList.artist));
		}
	}
	// instance variable of artist
	String artist = "";
	
	/* param: none
	 * does: creates scanner object, prompts user for artist name, stores into local variable, returns local variable
	 * returns: String of artist name by user input
	 * */
	public static String getArtistName() {
		Scanner kbReader = new Scanner(System.in);
		System.out.print("Please enter an artist name:");
		String usersArtist = kbReader.nextLine();
		kbReader.close();
		return usersArtist;
	}
	
	/* param: String of artist name
	 * does: creates string, list of songs, bridges object, datasource object;
	 * tries to store data into list of songs, if not, outputs error to user, sorts list of songs,
	 * then traverses thru list of songs for songs by artist of users choice, concatenates them into string
	 * returns: String of list of songs by artists, grouped by album, alphabetized in album groups
	 * */
	public String getSongsByArtist(String artist) {
		String s = "";
		List<Song> songData = null;
		Bridges bridges = new Bridges(5, "reynosoj3", "341180180506");
		DataSource ds = bridges.getDataSource();
		try {
			songData = ds.getSongData();
			}
			catch (Exception e) {
			System.out.println("Unable to connect to Bridges.");
			}
		Collections.sort(songData, new SongComparator());
		for (Song a : songData) {
			if (a.getArtist().equalsIgnoreCase(artist)) {
				s += "Title: " + a.getSongTitle() + " Artist: " + 
			a.getArtist() + " Album: " + a.getAlbumTitle() + "\n";
			}
			}
		if(s.length() == 0) {
			s += "No artist by that name has any songs within this data set.";
		}
		
		return s;
	
	

}
}
