package com.music.streaming.platform.controller;

import com.music.streaming.platform.dto.PlayListRequest;
import com.music.streaming.platform.model.PlayList;
import com.music.streaming.platform.service.PlayListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music/platform/v1/playlists")
public class PlayListController {

    private final PlayListService playListService;

    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @PostMapping
    public ResponseEntity<PlayList> createPlayList(@RequestBody PlayListRequest playListRequest) {
        PlayList playList = playListService.createPlayList(playListRequest);
        return ResponseEntity.ok(playList);
    }

    @GetMapping("/{playlistId}")
    public ResponseEntity<PlayList> getPlayListById(@PathVariable Long playlistId) {
        PlayList playList = playListService.getPlayListById(playlistId);
        return ResponseEntity.ok(playList);
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> deletePlayList(@PathVariable Long playlistId) {
        playListService.deletePlayList(playlistId);
        return ResponseEntity.noContent().build();
    }
}
