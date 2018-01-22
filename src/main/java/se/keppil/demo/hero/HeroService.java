package se.keppil.demo.hero;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.math.NumberUtils;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class HeroService {

	private HeroRepository heroRepository;

	public HeroService() {
	}

	@Inject
	public HeroService(HeroRepository heroRepository) {
		this.heroRepository = heroRepository;
	}

	@Path("/hero/{id}")
	@GET
	public Hero getHero(@PathParam("id") String id) {
		return heroRepository.get(NumberUtils.toLong(id, 0));
	}
	
	@Path("/heroes/")
	@GET
	public List<Hero> getAllHeroes() {
		return heroRepository.getAll();
	}
	
	@Path("/hero/")
	@POST
	public void addHero(Hero hero) {
		heroRepository.put(hero);
	}
	
	@Path("/hero/{id}")
	@DELETE
	public void deleteHero(@PathParam("id") String id) {
		heroRepository.delete(NumberUtils.toLong(id, 0));
	}
	
}