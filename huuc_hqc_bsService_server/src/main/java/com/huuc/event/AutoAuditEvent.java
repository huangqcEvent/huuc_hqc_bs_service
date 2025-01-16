package com.huuc.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 自动机器审核事件
 */
@Getter
public class AutoAuditEvent extends ApplicationEvent {
    private final String id;

    public AutoAuditEvent(String id) {
        super(id);
        this.id = id;
    }

}
