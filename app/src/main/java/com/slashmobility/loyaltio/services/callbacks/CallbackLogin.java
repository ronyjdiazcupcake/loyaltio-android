package com.slashmobility.loyaltio.services.callbacks;

import com.slashmobility.loyaltio.services.responses.RespLogin;

/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */
public interface CallbackLogin extends CallbackBase {
    void onSuccess(RespLogin auth);


}
