/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.mao;

import com.snapdeal.lunchbox.mongo.entity.Account;

/**
 * @version 1.0, 04-Sep-2015
 * @author himani
 */
public interface AccountMao {

    Account getAccountByPhoneNo(String mobileNo);

    Account saveAccount(Account account);

    Account updateAccountGroup(Long groupId, String mobileNo);

    Account updateAccountDeviceId(Long id, String deviceId);

}
