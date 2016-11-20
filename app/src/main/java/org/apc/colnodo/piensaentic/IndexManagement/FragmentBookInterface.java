package org.apc.colnodo.piensaentic.IndexManagement;

/**
 * Created by apple on 11/17/16.
 */

public interface FragmentBookInterface {

    public void finishedActivity(boolean is_finished);

    public void changeMenuItem(int r_id);

    public void isAllowedToContinue(boolean mAllowedToContinue);

    public void changePagerBackground(int color);

    public void registerUserAccepted();

}
