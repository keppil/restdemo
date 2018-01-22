package se.keppil.demo.hero;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HeroRepository {

	private static final List<String> HERO_NAMES = asList("Captain Awesome", "Storm", "Magneto", "Dr Xavier",
			"Nightcrawler", "Phoenix", "Rogue", "Wolverine");

	private SortedMap<Long, Hero> heroes = IntStream.range(0, HERO_NAMES.size())
			.mapToObj(id -> new Hero(id + 1, HERO_NAMES.get(id)))
			.collect(toMap(Hero::getId, h -> h, (a, b) -> a, TreeMap::new));

	public List<Hero> getAll() {
		return new ArrayList<>(heroes.values());
	}

	public void put(Hero hero) {
		heroes.put(hero.getId(), hero);
	}

	public Hero get(long id) {
		return heroes.getOrDefault(id, new Hero(id, "Unknown"));
	}

	public void delete(long id) {
		heroes.remove(id);
	}

}
