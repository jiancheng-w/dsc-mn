package com.smil.mn.api.commandhandler;

import com.smil.mn.api.command.AccountCreatedCommand;
import com.smil.mn.api.command.AccountUpdateCommand;

public interface AccountCommandHandler {
    /**
     * 新增账户
     *
     * @param createdCommand
     */
    void createdAccount(AccountCreatedCommand createdCommand);

    /**
     * 修改密码
     *
     * @param updateCommand
     */
    void updateAccount(AccountUpdateCommand updateCommand);
}
