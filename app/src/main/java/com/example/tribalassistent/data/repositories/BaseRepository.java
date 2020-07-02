package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.data.comunication.request.SocketConnection;

import java.util.Observable;

abstract class BaseRepository extends Observable {
    SocketConnection socketConnection = SocketConnection.getInstance();
}
