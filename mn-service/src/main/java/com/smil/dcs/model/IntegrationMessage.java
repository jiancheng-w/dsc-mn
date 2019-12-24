package com.smil.dcs.model;

import com.alibaba.fastjson.JSON;
import com.smil.dcs.mappers.IntegrationMessageMapper;
import com.smil.dcs.utils.SpringContextUtils;
import org.springframework.messaging.MessageHeaders;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Table(name = "integration_message")
public class IntegrationMessage {
    private static final String MESSAGE_CREATE_BY = "Integration Messages";
    IntegrationMessageMapper messagesMapper;

    public IntegrationMessage() {
        messagesMapper = SpringContextUtils.getApplicationContext().getBean(IntegrationMessageMapper.class);
    }

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 消息id
     */
    @Column(name = "message_id")
    private String messageId;

    /**
     * topic名称
     */
    private String topic;

    /**
     * 事件模型
     */
    @Column(name = "event_model")
    private String eventModel;

    /**
     * 重试次数
     */
    @Column(name = "retry_times")
    private Integer retryTimes;

    /**
     * 消费者分区id
     */
    @Column(name = "received_partition_id")
    private String receivedPartitionId;

    /**
     * 消息时间戳
     */
    @Column(name = "message_timestamp")
    private Date messageTimestamp;

    /**
     * 消息头部信息
     */
    private String header;

    /**
     * 负载信息
     */
    private String payload;

    /**
     * 0.saved 1.processed
     */
    private Integer status;

    /**
     * 创建人编号
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人编号
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取消息id
     *
     * @return message_id - 消息id
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * 设置消息id
     *
     * @param messageId 消息id
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取topic名称
     *
     * @return topic - topic名称
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 设置topic名称
     *
     * @param topic topic名称
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * 获取事件模型
     *
     * @return event_model - 事件模型
     */
    public String getEventModel() {
        return eventModel;
    }

    /**
     * 设置事件模型
     *
     * @param eventModel 事件模型
     */
    public void setEventModel(String eventModel) {
        this.eventModel = eventModel;
    }

    /**
     * 获取重试次数
     *
     * @return retry_times - 重试次数
     */
    public Integer getRetryTimes() {
        return retryTimes;
    }

    /**
     * 设置重试次数
     *
     * @param retryTimes 重试次数
     */
    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    /**
     * 获取消费者分区id
     *
     * @return received_partition_id - 消费者分区id
     */
    public String getReceivedPartitionId() {
        return receivedPartitionId;
    }

    /**
     * 设置消费者分区id
     *
     * @param receivedPartitionId 消费者分区id
     */
    public void setReceivedPartitionId(String receivedPartitionId) {
        this.receivedPartitionId = receivedPartitionId;
    }

    /**
     * 获取消息时间戳
     *
     * @return message_timestamp - 消息时间戳
     */
    public Date getMessageTimestamp() {
        return messageTimestamp;
    }

    /**
     * 设置消息时间戳
     *
     * @param messageTimestamp 消息时间戳
     */
    public void setMessageTimestamp(Date messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
    }

    /**
     * 获取消息头部信息
     *
     * @return header - 消息头部信息
     */
    public String getHeader() {
        return header;
    }

    /**
     * 设置消息头部信息
     *
     * @param header 消息头部信息
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * 获取负载信息
     *
     * @return payload - 负载信息
     */
    public String getPayload() {
        return payload;
    }

    /**
     * 设置负载信息
     *
     * @param payload 负载信息
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /**
     * 获取0.saved 1.processed
     *
     * @return status - 0.saved 1.processed
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0.saved 1.processed
     *
     * @param status 0.saved 1.processed
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建人编号
     *
     * @return created_by - 创建人编号
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人编号
     *
     * @param createdBy 创建人编号
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人编号
     *
     * @return updated_by - 更新人编号
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新人编号
     *
     * @param updatedBy 更新人编号
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public static IntegrationMessage createKafkaMessages(MessageHeaders headers, String payloadJson) {
        IntegrationMessage messages = new IntegrationMessage();

        messages.messageId = Objects.requireNonNull(headers.getId()).toString();
        messages.topic = headers.get("kafka_receivedTopic", String.class);
        messages.eventModel = Objects.requireNonNull(headers.get("eventModel", Class.class)).getName();
        messages.retryTimes = Objects.requireNonNull(headers.get("deliveryAttempt", AtomicInteger.class)).get();
        messages.receivedPartitionId = headers.get("kafka_receivedPartitionId", Integer.class).toString();
        messages.messageTimestamp = new Date(headers.getTimestamp());
        messages.header = JSON.toJSONString(headers);
        messages.payload = payloadJson;
        messages.status = 0;
        messages.createdBy = MESSAGE_CREATE_BY;
        messages.updatedBy = messages.createdBy;

        return messages;
    }

    public static IntegrationMessage createRabbitMessages(MessageHeaders headers, String payloadJson) {
        IntegrationMessage messages = new IntegrationMessage();

        messages.messageId = Objects.requireNonNull(headers.getId()).toString();
        messages.topic = headers.get("amqp_receivedExchange", String.class);
        messages.eventModel = headers.get("eventModel", String.class);
        messages.retryTimes = Objects.requireNonNull(headers.get("deliveryAttempt", AtomicInteger.class)).get();
        messages.receivedPartitionId = headers.get("amqp_receivedRoutingKey", String.class);
        messages.messageTimestamp = new Date(headers.getTimestamp());
        messages.header = JSON.toJSONString(headers);
        messages.payload = payloadJson;
        messages.status = 0;
        messages.createdBy = MESSAGE_CREATE_BY;
        messages.updatedBy = messages.createdBy;

        return messages;
    }

    public boolean save() {
        return messagesMapper.insert(this) > 0;
    }

    public boolean processed() {
        this.status = 1;

        return messagesMapper.updateByPrimaryKey(this) > 0;
    }
}