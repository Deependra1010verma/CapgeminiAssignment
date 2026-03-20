package com.music.streaming.platform.controller;

import com.music.streaming.platform.dto.TrackRequest;
import com.music.streaming.platform.model.Track;
import com.music.streaming.platform.service.TrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music/platform/v1/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping
    public ResponseEntity<Track> createTrack(@RequestBody TrackRequest trackRequest) {
        Track track = trackService.createTrack(trackRequest);
        return ResponseEntity.ok(track);
    }

    @GetMapping
    public ResponseEntity<List<Track>> getAllTracks() {
        List<Track> tracks = trackService.getAllTracks();
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("/{trackId}")
    public ResponseEntity<Track> getTrackById(@PathVariable Long trackId) {
        Track track = trackService.getTrackById(trackId);
        return ResponseEntity.ok(track);
    }

    @PutMapping("/{trackId}")
    public ResponseEntity<Track> updateTrack(@PathVariable Long trackId, @RequestBody TrackRequest trackRequest) {
        Track track = trackService.updateTrack(trackId, trackRequest);
        return ResponseEntity.ok(track);
    }

    @DeleteMapping("/{trackId}")
    public ResponseEntity<Void> deleteTrack(@PathVariable Long trackId) {
        trackService.deleteTrack(trackId);
        return ResponseEntity.noContent().build();
    }
}
