package ca.sheridancollege.encinasv.controllers;

import ca.sheridancollege.encinasv.beans.Song;
import ca.sheridancollege.encinasv.beans.VideoGame;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class SongController {
  List<Song> songList = new CopyOnWriteArrayList<Song>();
  List<VideoGame> gameList = new CopyOnWriteArrayList<>();

  @GetMapping("/")
  public String index(Model model){
    model.addAttribute("song", new Song());
    model.addAttribute("songList", songList);
    model.addAttribute("VideoGame", new VideoGame());
    model.addAttribute("gameList", gameList);
    return "index";
  }

  @PostMapping("/addSong")
  public String addSong(Model model, @ModelAttribute Song song) {
    songList.add(song);

    model.addAttribute("song", new Song());
    model.addAttribute("songList", songList);
    model.addAttribute("VideoGame", new VideoGame());
    model.addAttribute("gameList", gameList);
    return "index";
  }
  @PostMapping("/addGame")
  public String addGame(Model model, @ModelAttribute VideoGame game) {
    gameList.add(game);
    model.addAttribute("song", new Song());
    model.addAttribute("songList", songList);
    model.addAttribute("VideoGame", new VideoGame());
    model.addAttribute("gameList", gameList);
    return "index";
  }

}
