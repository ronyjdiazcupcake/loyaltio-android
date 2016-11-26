package com.slashmobility.loyaltio.services.callbacks;

/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */
public interface CallbackBase {
    void onError(int errorCode, String errorMessage);
}
