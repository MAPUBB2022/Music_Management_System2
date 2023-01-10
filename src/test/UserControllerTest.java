package test;

import model.label.MusicLabel;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import repository.inmemory.*;

class UserControllerTest {
    ArtistsInMemoryRepository artists;
    AlbumsInMemoryRepository albums;
    UserInMemoryRepository users;
    BandsInMemoryRepository bands;
    MusicLabelsInMemoryRepository labels;
    SongsInMemoryRepository songs;
    ConcertsInMemoryRepository cnocerts;

    @BeforeEach
    void setUP(){

    }

    @org.junit.jupiter.api.Test
    void addFavourite() {
    }

    @org.junit.jupiter.api.Test
    void removeFavourite() {
    }

    @org.junit.jupiter.api.Test
    void buyTicket() {
    }

}