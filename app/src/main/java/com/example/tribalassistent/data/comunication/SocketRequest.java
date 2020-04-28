package com.example.tribalassistent.data.comunication;

import android.os.AsyncTask;

import com.example.tribalassistent.data.model.system.Error;

import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;

public class SocketRequest<I, O> extends AsyncTask<EventMsg<I>, Void, EventMsg> {
    private static Map<Integer, SocketRequest> pendingMessages = new HashMap<>();
    private final Integer lock = 1;
    private EventMsg response;
    private OnResultListener<O> resultListener;

    public void doInBackground(I data, EventType eventType) {
        executeOnExecutor(THREAD_POOL_EXECUTOR, EventMsgFactory.getEvent(data, eventType));
    }

    @SneakyThrows
    @Override
    protected EventMsg doInBackground(EventMsg<I>... eventMsgs) {
        EventMsg<I> eventMsg = eventMsgs[0];
        pendingMessages.put(eventMsg.getId(), this);
        SocketConnection.sendDataToServer(eventMsg);
        synchronized (lock) {
            lock.wait(30000);
        }
        return response;
    }

    @Override
    protected void onPostExecute(EventMsg eventMsg) {
        if (eventMsg == null) return;
        Object event = eventMsg.getData();
        if (event instanceof Error) {
            resultListener.onResult(new Result<O>((Error) event));
        } else {
            resultListener.onResult(new Result<>(((O) event)));
        }
    }

    static void received(EventMsg eventMsg) {
        SocketRequest socketRequest = pendingMessages.get(eventMsg.getId());
        if (socketRequest != null) {
            socketRequest.response = eventMsg;
            socketRequest.releaseLock();
        }
    }

    private void releaseLock() {
        synchronized (lock) {
            lock.notify();
        }
    }

    public void setOnResultListener(OnResultListener<O> resultListener) {
        this.resultListener = resultListener;
    }
}
