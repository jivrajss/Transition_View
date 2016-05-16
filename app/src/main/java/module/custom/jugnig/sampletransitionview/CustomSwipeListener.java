package module.custom.jugnig.sampletransitionview;

import android.util.Log;

/**
 * Created by JugniG on 16-05-2016.
 */
public class CustomSwipeListener extends OnSwipeListener {

    private static final String TAG = CustomSwipeListener.class.getSimpleName();

    @Override
    public boolean onSwipe(Direction direction) {
        Log.d(TAG,direction.name());
        return super.onSwipe(direction);
    }
}
