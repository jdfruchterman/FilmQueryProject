package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() {
		Film film = db.getFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		int selection;
		System.out.println("Welcome to Jimmy's Online Film Warehouse!");
		do {
			System.out.println();
			System.out.println("Please pick one of the following options:");
			System.out.println("1. Look up a film by it's ID Number");
			System.out.println("2. Look up a film by a search keyword");
			System.out.println("3. Quit");

			selection = input.nextInt();

			switch (selection) {
			case 1:
				System.out.println("Please enter the film ID Number.");
				int filmId = input.nextInt();
				Film film = db.getFilmById(filmId);
				System.out.println(film.getTitle() 
						+ " \n" + film.getReleaseYear() 
						+ " \n" + film.getRating() 
						+ " \n" + film.getDescription());
//				System.out.println(db.getFilmById(filmId));
				continue;
			case 2:
				input.nextLine();
				System.out.println("Please enter the search keyword.");
				String keyword = input.nextLine();
//				input.nextLine();
				List<Film> films = db.getFilmByKeyword(keyword);
				if (films.size() > 0) {
					for (Film film2 : films) {
						System.out.println(film2.getTitle());
					}
				}
				else  { System.out.println("No matching film found."); }
				continue;
			case 3:
				System.out.println("Have a nice day!");
				break;
			default: continue;

			}
		} while (selection != 3);
	}

}
