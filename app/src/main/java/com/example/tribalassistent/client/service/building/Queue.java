package com.example.tribalassistent.client.service.building;

import androidx.annotation.NonNull;

import com.example.tribalassistent.data.model.common.BuildingName;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Queue extends ArrayList<String> {
    private static final String TAG = "Queue";
    private static List<String> infiniteQueue = BuildingName.getNames();
    private boolean auto;

    @NonNull
    @Override
    public Iterator<String> iterator() {
        if (auto) {
            return new QueueIterator(this);
        }
        return super.iterator();
    }

    static class QueueIterator implements Iterator<String> {
        Iterator<String> current;
        boolean second;

        QueueIterator(List<String> current) {
            this.current = current.iterator();
        }

        @Override
        public boolean hasNext() {
            if (!second && !current.hasNext()) {
                second = true;
                current = infiniteQueue.iterator();
            }
            return current.hasNext();
        }

        @Override
        public String next() {
            return current.next();
        }
    }
}
