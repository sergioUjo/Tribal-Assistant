package com.example.tribalassistent.data.comunication;

import com.example.tribalassistent.data.model.system.Error;

import java.util.HashMap;
import java.util.Map;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;

public class SocketRequest<I, O> implements Runnable {
    private static Map<Integer, SocketRequest> pendingMessages = new HashMap<>();
    private EventMsg<I> request;
    private OnResultListener<O> resultListener;


    @Override
    public void run() {
        pendingMessages.put(request.getId(), this);
        SocketConnection.sendDataToServer(request);
    }

    public SocketRequest(OnResultListener<O> resultListener) {
        this.resultListener = resultListener;
    }

    public void doInBackground(I data, EventType eventType) {
        request = EventMsgFactory.getEvent(data, eventType);
        THREAD_POOL_EXECUTOR.execute(this);
    }


    private void onPostExecute(EventMsg eventMsg) {
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
            socketRequest.onPostExecute(eventMsg);
        }
    }

}
