package se.keppil.demo.hero;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.lang3.StringUtils;

@ApplicationScoped
public class HeroRepository {

	private static final List<String> HERO_NAMES = asList("Captain Awesome", "Storm", "Magneto", "Dr Xavier",
			"Nightcrawler", "Phoenix", "Rogue", "Wolverine");

	private static final AtomicLong ID_GENERATOR = new AtomicLong(HERO_NAMES.size());

	private SortedMap<Long, Hero> heroes = IntStream.range(0, HERO_NAMES.size())
			.mapToObj(id -> new Hero(id + 1, HERO_NAMES.get(id)))
			.collect(toMap(Hero::getId, h -> h, (a, b) -> a, TreeMap::new));

	public List<Hero> getAll() {
		return new ArrayList<>(heroes.values());
	}

	public void put(Hero hero) {
		heroes.put(hero.getId(), hero);
	}
	
	public Hero addNew(String name) {
		long newId = ID_GENERATOR.incrementAndGet();
		Hero hero = new Hero(newId, name);
		heroes.put(newId, hero);
		return hero;
	}

	public Hero get(long id) {
		return heroes.getOrDefault(id, new Hero(id, "Unknown"));
	}

	public void delete(long id) {
		heroes.remove(id);
	}

	public List<Hero> getMatching(String term) {
		return heroes.values().stream().filter(h -> StringUtils.containsIgnoreCase(h.getName(), term))
				.collect(toList());
	}

}
