package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.client.service.connection.SocketConnection;

import java.util.Observable;

abstract class BaseRepository extends Observable {
    SocketConnection socketConnection = SocketConnection.getInstance();
}
