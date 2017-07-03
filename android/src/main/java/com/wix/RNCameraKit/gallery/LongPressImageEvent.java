package com.wix.RNCameraKit.gallery;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class LongPressImageEvent extends Event<LongPressImageEvent> {

    private final int targetTag;
    private WritableMap event;

    LongPressImageEvent(int targetTag, String uri, Integer width, Integer height, Long date, Double latitude, Double longitude, boolean touchEnd) {
        this.targetTag = targetTag;
        event = Arguments.createMap();
        event.putString("selected", uri);
        event.putString("id", "onLongPress");
        event.putInt("width", width);
        event.putInt("height", height);
        WritableMap location = new WritableNativeMap();
        if (longitude > 0 || latitude > 0) {
            location.putDouble("longitude", longitude);
            location.putDouble("latitude", latitude);
        }
        event.putDouble("timestamp", date / 1000d);
        event.putMap("location", location);
        event.putBoolean("touchEnd", touchEnd);
        init(0);
    }

    @Override
    public String getEventName() {
        return "onLongPress";
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(targetTag, "onLongPress", event);
    }
}

