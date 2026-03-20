package com.music.streaming.platform.controller;

import com.music.streaming.platform.dto.ArtistRequest;
import com.music.streaming.platform.model.Artist;
import com.music.streaming.platform.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music/platform/v1/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody ArtistRequest artistRequest) {
        Artist artist = artistService.createArtist(artistRequest);
        return ResponseEntity.ok(artist);
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        List<Artist> artists = artistService.getAllArtists();
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long artistId) {
        Artist artist = artistService.getArtistById(artistId);
        return ResponseEntity.ok(artist);
    }

    @PutMapping("/{artistId}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long artistId, @RequestBody ArtistRequest artistRequest) {
        Artist artist = artistService.updateArtist(artistId, artistRequest);
        return ResponseEntity.ok(artist);
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long artistId) {
        artistService.deleteArtist(artistId);
        return ResponseEntity.noContent().build();
    }
}
