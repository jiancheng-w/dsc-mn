package com.smil.dcs.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.smil.dcs.common.UserUtils;

import java.util.Date;

public abstract class AbstractEvent<T extends EventParam> {
    private T args;

    private String createdBy;

    private Date createTime;

    @JSONField(serialize = false, deserialize = false)
    private String eventPayload;

    public AbstractEvent(T args) {
        this.args = args;
        this.createdBy = UserUtils.getUserCode();
        this.createTime = new Date();
    }

    public T getArgs() {
        return args;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public abstract String getEventName();

    @JSONField(serialize = false)
    public String getEventPayload() {
        return JSONObject.toJSONString(this);
    }
}